package com.atguigu.app

import java.text.SimpleDateFormat
import java.util.Date

import com.alibaba.fastjson.JSON
import com.atguigu.bean.StartUpLog
import com.atguigu.constant.GmallConstants
import com.atguigu.handler.DauHandler
import com.atguigu.utils.com.atguigu.utils.MyKafkaUtil
import org.apache.hadoop.hbase.HBaseConfiguration
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.spark.streaming.dstream.{DStream, InputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.SparkConf
import org.apache.phoenix.spark._

object DauApp {
  def main(args: Array[String]): Unit = {
    //创建spark的conf，创建SparkStreaming连接
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("DauApp")
    val streamingContext = new StreamingContext(sparkConf,Seconds(5))
    //SparkStreaming 链接Kafka (在until包里设置连接Kafka的方法)
    val StreamingToKafka: InputDStream[ConsumerRecord[String, String]] =
      MyKafkaUtil.getKafkaStream(GmallConstants.KAFKA_TOPIC_STARTUP,streamingContext)

        //读取到kafka的数据，将json格式的数据转换成样例类（StartUpLog）
        val dateFormat = new SimpleDateFormat("yyyy-MM-dd HH")
        val startUpLog: DStream[StartUpLog] = StreamingToKafka.mapPartitions(partitions => {
          partitions.map(
            data => {
              val startUpLog: StartUpLog = JSON.parseObject(data.value(), classOf[StartUpLog])
              val str: String = dateFormat.format(new Date(startUpLog.ts))
              startUpLog.logDate = str.split(" ")(0)
              startUpLog.logHour = str.split(" ")(1)
              startUpLog
            }
          )
        })

    startUpLog.cache()
    //批次间去重
    val filterByRedisDStream: DStream[StartUpLog] = DauHandler.filterByRedis(startUpLog,streamingContext.sparkContext)
//    filterByRedisDStream.cache()
//    startUpLog.count().print()
//    filterByRedisDStream.count().print()
    //批次内去重
    val filterByMidsDStream: DStream[StartUpLog] = DauHandler.filterByMids(filterByRedisDStream)
//    filterByMidsDStream.cache()
//    filterByMidsDStream.count().print()

    //将数据存到Redis中
   // print("将数据写到Redis")
    DauHandler.saveToRedis(filterByMidsDStream)

    //将去重后的数据存到hbash


/*    print("开始消费kafka数据")
    StreamingToKafka.foreachRDD(rdd=>{
      rdd.foreach(log=>{
        println(log.value())
      })
    })*/

    //print("开始写入Phoenix")
    filterByMidsDStream.foreachRDD(rdd=>{
      rdd.saveToPhoenix(
        "GMALL1116_DAU",
        Seq("MID", "UID", "APPID", "AREA", "OS", "CH", "TYPE", "VS", "LOGDATE", "LOGHOUR", "TS"),
        HBaseConfiguration.create,
        Some("hadoop102,hadoop103,hadoop104:2181"))
    })
    filterByMidsDStream.print()

    streamingContext.start()
    streamingContext.awaitTermination()

  }
}
