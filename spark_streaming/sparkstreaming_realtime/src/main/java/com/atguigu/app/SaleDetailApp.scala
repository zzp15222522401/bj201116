package com.atguigu.app

import java.text.SimpleDateFormat
import java.util

import com.alibaba.fastjson.{JSON, JSONObject}
import com.atguigu.bean.{OrderDetail, OrderInfo, SaleDetail, UserInfo}
import com.atguigu.constant.GmallConstants
import com.atguigu.utils.MyEsUtil.MyEsUtil
import com.atguigu.utils.com.atguigu.utils.MyKafkaUtil
import io.searchbox.client.JestClient
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, InputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}
import redis.clients.jedis.Jedis
import org.json4s.native.Serialization

import collection.JavaConverters._
import scala.collection.mutable.ListBuffer

object SaleDetailApp {
  /**
   * 订单有关的信息关联在一起后进行操作，用户信息直接全部存到redis中
   * @param args
   */
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("SaleDetailApp")
    val ssc = new StreamingContext(sparkConf,Seconds(5))
    //消费kafka中数据
    val dateFormat = new SimpleDateFormat("yyyy-MM-dd HH")
    val ordertopicDStream: InputDStream[ConsumerRecord[String, String]] = MyKafkaUtil.getKafkaStream(GmallConstants.KAFKA_TOPIC_ORDER,ssc)
    val orderDStream: DStream[(String, OrderInfo)] = ordertopicDStream.mapPartitions(partition => {
      partition.map(record => {
        //转换样例类，补全数据结构
        val orderInfo: OrderInfo = JSON.parseObject(record.value(), classOf[OrderInfo])
        orderInfo.create_date=orderInfo.create_time.split(" ")(0)
        orderInfo.create_date=orderInfo.create_time.split(" ")(1).split(":")(1)
        //因为需要joinorderdetail,所以转换成map格式（orderid,orderinfo）
        (orderInfo.id, orderInfo)
      })
    })
   val orderDetailTopicDStream: InputDStream[ConsumerRecord[String, String]] = MyKafkaUtil.getKafkaStream(GmallConstants.KAFKA_TOPIC_ORDER_DETAIL,ssc)
    val orderDetailDStream: DStream[(String, OrderDetail)] = orderDetailTopicDStream.mapPartitions(partition => {
      partition.map(record => {
        val orderDetail: OrderDetail = JSON.parseObject(record.value(), classOf[OrderDetail])
        (orderDetail.order_id, orderDetail)
      })
    })
    //将orderinfo的数据和orderdetail的数据进行关联，得到我们需要的数据
    //TODO 此处采用的数fullouterjoin  因为innerjoin会因网络延时导致数据丢失
    val fullJoinDStream: DStream[(String, (Option[OrderInfo], Option[OrderDetail]))] = orderDStream.fullOuterJoin(orderDetailDStream)
    //orderinfo和orderdetail进行关联的操作
    val noUserdetail: DStream[SaleDetail] = fullJoinDStream.mapPartitions(iter => {

       val list:ListBuffer[SaleDetail] = new ListBuffer[SaleDetail]
      val jedis = new Jedis("hadoop102",6379)
      iter.foreach {
        case (orderid, (orderinfo, orderdetail)) => {
          val infokey: String = "orderInfo" + orderid
          val detailkey: String = "orderDetail" + orderid
          //判断info和detail都存在关联成功的结果
          if (orderinfo.isDefined) {
            val info: OrderInfo = orderinfo.get
            if (orderdetail.isDefined) {
              val detail: OrderDetail = orderdetail.get
              val saleDetail = new SaleDetail(info, detail)
              list+=saleDetail
            }


            //无论如何都需要把orderinfo存到redis中
            //val str: String = JSON.toJSONString(info)这样将info转成字符串是错误的
            implicit val formats = org.json4s.DefaultFormats
            val infotojson: String = Serialization.write(info)
            //将数据存到redis中 =>  订单的rowkey是一个key对应一条订单，所以采用String类型的存储格式
            jedis.set(infokey, infotojson)
            //设置数据的过期时间  => 订单的信息量很大，不可能把所有的数据全部存到redis中放置，所以设置过期时间，最少为集群的最高延迟时间
            jedis.expire(infokey, 100)


            //查询detailinfo缓存中是否有和info关联的数据
            if (jedis.exists(detailkey)) {
              val detailset: util.Set[String] = jedis.smembers(detailkey)
              for (elem <- detailset.asScala) {
                val detail1: OrderDetail = JSON.parseObject(elem, classOf[OrderDetail])
                list+=new SaleDetail(info, detail1)
              }
            }
          }
          //orderinfo不存在
          else {
            val detail: OrderDetail = orderdetail.get
            //检查缓存中是否有对应的infokey(存在相关连的数据时)，关联存到SaleDetail中
            if (jedis.exists(infokey)) {
              val info: String = jedis.get(infokey)
              val info1: OrderInfo = JSON.parseObject(info, classOf[OrderInfo])
              val saleDetail = new SaleDetail(info1, detail)
              list+=saleDetail

            }
            else { //不存在orderinfo的数据时，将detail自己转换成json存到redis缓存中
              implicit val formats = org.json4s.DefaultFormats
              val detailtojson: String = Serialization.write(detail)
              jedis.sadd(detailkey, detailtojson)
              jedis.expire(detailkey, 100) //设置存留时间
            }
          }
        }
      }
      jedis.close()
      list.toIterator //转换成scala中的可迭代集合
    })
    //noUserdetail.print()

    //用户信息的操作,关联order的信息，直接存到redis中
    val result: DStream[SaleDetail] = noUserdetail.mapPartitions(iter => {
      val jedis = new Jedis("hadoop102",6379)
      val details: Iterator[SaleDetail] = iter.map(saledetail => {
        //查看缓存中是否有关联的userinfo
        val userinfokey: String = "userInfo" + saledetail.user_id
        //获取关联的userinfo的信息
        val userinfo: String = jedis.get(userinfokey)
        //转换成相应的样例类保存到redis
        val Info: UserInfo = JSON.parseObject(userinfo, classOf[UserInfo])
        saledetail.mergeUserInfo(Info)
        saledetail
      })
      jedis.close()
      details
    })
    result.print()

    //将数据写到ES上 (写库操作)
    result.foreachRDD(rdd=>{
      rdd.foreachPartition(iter=>{
        val list: List[(String, SaleDetail)] = iter.toList.map(
          saledetail => {
            (saledetail.order_detail_id, saledetail)
          }
        )
        MyEsUtil.insertBulk(GmallConstants.ES_DETAIL_INDEX+System.currentTimeMillis()/1000/60/60/24,list)
      })
    })

    ssc.start()
    ssc.awaitTermination()


  }
}
