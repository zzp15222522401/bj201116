import java.util

import com.alibaba.fastjson.JSON
import redis.clients.jedis.Jedis
import collection.JavaConverters._

object RedisTest {
  def main(args: Array[String]): Unit = {

    val jedis: Jedis = new Jedis("hadoop102",6379)

    val stringToString: util.Map[String, String] = jedis.hgetAll("userInfo:1001")


//    for (elem <- stringToString.keySet().asScala) {
//      println(elem+":"+stringToString.get(elem))
//    }

//    println(JSON.toJSONString(stringToString))



    jedis.close()
  }

}
