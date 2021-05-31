package com.atguigu.handler

import java.{lang, util}
import java.text.SimpleDateFormat
import java.util.Date

import com.atguigu.bean.StartUpLog
import com.atguigu.utils.RedisUtil
import org.apache.spark.SparkContext
import org.apache.spark.broadcast.Broadcast
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.dstream.DStream
import redis.clients.jedis.Jedis

object DauHandler {
  /**
   * 批次内进行去重操作
   * @param filterByRedisDStream
   * @return
   */
  def filterByMids(filterByRedisDStream: DStream[StartUpLog]) = {
    val midAndDate: DStream[((String, String), StartUpLog)] = filterByRedisDStream.map(log => {
      ((log.mid, log.logDate), log)
    })
    val midAndDateIter: DStream[((String, String), Iterable[StartUpLog])] = midAndDate.groupByKey()
    val sortWithMidAndDateIter: DStream[((String, String), List[StartUpLog])] = midAndDateIter.mapValues(iter => {
      iter.toList.sortWith(_.ts < _.ts).take(1)
    })
    val flatMapWithMidAndDate: DStream[StartUpLog] = sortWithMidAndDateIter.flatMap(_._2)
    flatMapWithMidAndDate
  }

  /**
   * 批次间进行去重操作
   * @param startUpLog
   * @param sc
   * @return
   */
  def filterByRedis(startUpLog: DStream[StartUpLog],sc:SparkContext) = {
    //方法1：每一批次进行一次redis的链接操作，效率很低
//    startUpLog.filter(logs=>{
//      val jedisClient: Jedis = RedisUtil.getJedisClient
//      val rediskey: String = "Dau"+logs.logDate
//      //redis中的判断key是否存在的方法
//      val flat: lang.Boolean = jedisClient.sismember(rediskey,logs.mid)
//      jedisClient.close()
//      !flat //将key值已存在的数据筛出掉，去重操作
//    })


    //方法2：在分区下获取链接，减少链接次数
//    val partitionRedisDStream: DStream[StartUpLog] = startUpLog.mapPartitions(partitions => {
//      val jedisClient: Jedis = RedisUtil.getJedisClient
//      val iter: Iterator[StartUpLog] = partitions.filter(
//        logs => {
//          val rediskey: String = "Dau" + logs.logDate
//          //redis中的判断key是否存在的方法
//          val flat: lang.Boolean = jedisClient.sismember(rediskey, logs.mid)
//          !flat //将key值已存在的数据筛出掉，去重操作
//        })
//
//      jedisClient.close()
//      iter
//    })
//    partitionRedisDStream

    //方法3：在每个批次获取链接(最优方法)
    val dateFormat = new SimpleDateFormat("yyyy-MM-dd")
    val value: DStream[StartUpLog] = startUpLog.transform(rdd => {
      //链接redis
      val jedisClient: Jedis = RedisUtil.getJedisClient
      //拿出数据，根据日活（key时间戳）
      val rediskey: String = "Dau" + dateFormat.format(new Date(System.currentTimeMillis()))
      val mids: util.Set[String] = jedisClient.smembers(rediskey)
      //根据key把redis中的数据取出来,这个数据在Driver端，我们需要将此数据广播到executor端(只有SparkContext可以进行广播)
      //防止序列化问题
      val midsBC: Broadcast[util.Set[String]] = sc.broadcast(mids)
      val valueBC: RDD[StartUpLog] = rdd.filter(logs => {
        val bool: Boolean = midsBC.value.contains(logs.mid)
        !bool
      })
      jedisClient.close()
      valueBC
    })
    value
  }

  /**
   * 将数据存到redis中
   * @param startUpLog
   */
  def saveToRedis(startUpLog: DStream[StartUpLog])= {
    startUpLog.foreachRDD(rdd=>{
      rdd.foreachPartition(partitions=>{
        val jedisClient: Jedis = RedisUtil.getJedisClient
        partitions.foreach(
          logs=>{
            jedisClient.sadd("Dau:"+logs.logDate,logs.mid)
          })
        jedisClient.close()
      })
    })
  }


}
