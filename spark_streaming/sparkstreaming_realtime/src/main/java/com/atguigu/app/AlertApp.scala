package com.atguigu.app

import java.text.SimpleDateFormat
import java.util

import com.alibaba.fastjson.JSON
import com.atguigu.bean.{CouponAlertInfo, Eventlog}
import com.atguigu.constant.GmallConstants
import com.atguigu.utils.MyEsUtil.MyEsUtil
import com.atguigu.utils.com.atguigu.utils.MyKafkaUtil
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, InputDStream}
import org.apache.spark.streaming.{Minutes, Seconds, StreamingContext}

import scala.util.control.Breaks._


object AlertApp {
  //预警日志的APP
  def main(args: Array[String]): Unit = {
    //创建sparkconf，连接StreamingContext
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("AlterApp")
    val ssc = new StreamingContext(sparkConf, Seconds(5))

    val KafkaDStream: InputDStream[ConsumerRecord[String, String]] = MyKafkaUtil.getKafkaStream(GmallConstants.KAFKA_TOPIC_EVENT, ssc)
    val dateFormat = {
      new SimpleDateFormat("yyyy-MM-dd HH")
    }
    //将kafka中的数据转换成所需样例类
    //因为需要根据不同设备id分组，所以取map（key-mid  value-eventlog）
    val mideventlogDStream: DStream[(String, Eventlog)] = KafkaDStream.map(record => {
      val eventlog: Eventlog = JSON.parseObject(record.value(), classOf[Eventlog])
      eventlog.logDate = dateFormat.format(eventlog.ts).split(" ")(0)
      eventlog.logHour = dateFormat.format(eventlog.ts).split(" ")(1)
      (eventlog.mid, eventlog)

    })
    //开窗，取5分钟内的数据
    val winlogDS: DStream[(String, Eventlog)] = mideventlogDStream.window(Minutes(5))
    //根据mid分组  得到同一个设备的数据
    val winGroupBy: DStream[(String, Iterable[Eventlog])] = winlogDS.groupByKey()
    //筛选生成疑似预警数据
    val couponAlertInfo: DStream[(Boolean, CouponAlertInfo)] = winGroupBy.mapPartitions(
      partitions => {
        partitions.map {
          case (mid, iter) => {
            //用于存放用户id
            val uid = new util.HashSet[String]()
            //用于存放领取优惠券涉及商品的id
            val itemid = new util.HashSet[String]()
            //用于存放用户行为的集合
            val evens = new util.ArrayList[String]()

            //定义一个标志位，判断用户是否有浏览商品的行为
            var bool = true
            breakable {
              iter.toList.foreach(log => {
                evens.add(log.evid)
                if ("clickItem".equals(log.evid)) {
                  bool = false
                  break()
                } else if ("coupon".equals(log.evid)) {
                  uid.add(log.uid)
                  itemid.add(log.itemid)
                }
              })
            }
            //生成的意思预警数据 => CouponAlertInfo样例类
            ((uid.size() >= 3 && bool), CouponAlertInfo(mid, uid, itemid, evens, System.currentTimeMillis()))
          }
        }
      })
    //筛选出预警数据
    val escoupon: DStream[CouponAlertInfo] = couponAlertInfo.filter(_._1).map(_._2)
    escoupon.print()
    //连接ES，将数据写到es上，写库操作foreachRDD
    escoupon.foreachRDD(rdd=>{
      rdd.foreachPartition(partition=>{
        val iter: Iterator[(String, CouponAlertInfo)] = partition.map(coupon => {
          (coupon.mid + coupon.ts / 1000 / 60, coupon)
        })
        val list: List[(String, CouponAlertInfo)] = iter.toList
        //按照每天添加预警日志
        MyEsUtil.insertBulk(GmallConstants.ES_INDEX_ALERT+dateFormat.format(System.currentTimeMillis()).split(" ")(0),list)
      })
    })


    ssc.start()
    ssc.awaitTermination()
  }
}
