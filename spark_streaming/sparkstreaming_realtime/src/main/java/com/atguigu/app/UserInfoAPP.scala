package com.atguigu.app

import java.text.SimpleDateFormat

import com.alibaba.fastjson.JSON
import com.atguigu.bean.UserInfo
import com.atguigu.constant.GmallConstants
import com.atguigu.utils.RedisUtil
import com.atguigu.utils.com.atguigu.utils.MyKafkaUtil
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, InputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}
import redis.clients.jedis.Jedis

object UserInfoAPP {
  //将用户信息导入redis中
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("SaleDetailApp")
    val ssc = new StreamingContext(sparkConf,Seconds(5))
    //消费kafka中数据
    val dateFormat = new SimpleDateFormat("yyyy-MM-dd HH")
    val userInfoTopicDStream: InputDStream[ConsumerRecord[String, String]] = MyKafkaUtil.getKafkaStream(GmallConstants.KAFKA_TOPIC_USER_INFO,ssc)
    //将数据写入到redis
    val userinfoDStream: DStream[String] = userInfoTopicDStream.map(redcord => {
      redcord.value()
    })
    userinfoDStream.foreachRDD(rdd=>{
      rdd.foreachPartition(
        partition=>{
          val jedis: Jedis = new Jedis("hadoop102",6379)
          partition.foreach(str=>{
            val userInfo: UserInfo = JSON.parseObject(str,classOf[UserInfo])
            val userinfokey: String = "userInfo"+userInfo.id

            jedis.set(userinfokey,str)
          })
          jedis.close()
        }
      )
    })
    userinfoDStream.print()

    ssc.start()
    ssc.awaitTermination()
  }
}
