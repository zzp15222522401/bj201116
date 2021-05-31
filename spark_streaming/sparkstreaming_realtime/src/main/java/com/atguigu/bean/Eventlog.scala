package com.atguigu.bean

case class Eventlog(
//事件日志的样例类
                    mid:String,
                    uid:String,
                    appid:String,
                    area:String,
                    os:String,
                    `type`:String,
                    evid:String,
                    pgid:String,
                    npgid:String,
                    itemid:String,
                    var logDate:String,
                    var logHour:String,
                    var ts:Long

)
