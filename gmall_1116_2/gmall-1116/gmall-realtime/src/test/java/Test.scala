import com.alibaba.fastjson.JSON

object Test {
  def main(args: Array[String]): Unit = {
//    val orderDetail: OrderDetail = new OrderDetail("101","111")
//    JSON.toJSONString(orderDetail)

  }

  case class OrderDetail(id:String,
                         order_id: String
                         )

}
