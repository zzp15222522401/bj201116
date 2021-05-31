package com.atguigu.bean


  //预警日志的样例类
/**
 *
 * @param mid //保存用户id的集合
 * @param uids
 * @param itemIds
 * @param events
 * @param ts
 */
  case class CouponAlertInfo(mid:String,
                             uids:java.util.HashSet[String],
                             itemIds:java.util.HashSet[String],
                             events:java.util.List[String],
                             ts:Long)


