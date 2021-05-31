package com.atguigu.app


import com.alibaba.fastjson.{JSON, JSONObject}
import com.atguigu.bean.OrderInfo
import com.atguigu.constant.GmallConstants
import com.atguigu.utils.com.atguigu.utils.MyKafkaUtil
import org.apache.hadoop.hbase.HBaseConfiguration
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.spark.streaming.dstream.{DStream, InputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.phoenix.spark._


object GmvApp {
  def main(args: Array[String]): Unit = {
    //1.创建sparkstreaming的连接
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("GmvApp")
    val ssc = new StreamingContext(sparkConf,Seconds(5))

    //2.获取kafka中的数据
    val GmvDStream: InputDStream[ConsumerRecord[String, String]] = MyKafkaUtil.getKafkaStream(GmallConstants.KAFKA_TOPIC_ORDER,ssc)
    //3.将数据转换成样例类
    val orderInfoDStream: DStream[OrderInfo] = GmvDStream.mapPartitions(partitions => {
      partitions.map(record => {
        //转成对应的样例类
        val orderInfo: OrderInfo = JSON.parseObject(record.value(), classOf[OrderInfo])
        //补全数据
        orderInfo.create_date = orderInfo.create_time.split(" ")(0)
        orderInfo.create_hour = orderInfo.create_time.split(" ")(1).split(":")(0)
        orderInfo
      })
    })
      //将数据存到hbase=>(写库操作使用foreachRDD)
    orderInfoDStream.foreachRDD(rdd=>{
      rdd.saveToPhoenix("GMALL1116_ORDER_INFO",
        Seq("ID", "PROVINCE_ID", "CONSIGNEE", "ORDER_COMMENT", "CONSIGNEE_TEL", "ORDER_STATUS", "PAYMENT_WAY", "USER_ID", "IMG_URL", "TOTAL_AMOUNT", "EXPIRE_TIME", "DELIVERY_ADDRESS", "CREATE_TIME", "OPERATE_TIME", "TRACKING_NO", "PARENT_ORDER_ID", "OUT_TRADE_NO", "TRADE_BODY", "CREATE_DATE", "CREATE_HOUR"),
        HBaseConfiguration.create(),
        Some("hadoop102,hadoop103,hadoop104:2181"))
    })
    orderInfoDStream.print()

    ssc.start()
    ssc.awaitTermination()

  }

}
