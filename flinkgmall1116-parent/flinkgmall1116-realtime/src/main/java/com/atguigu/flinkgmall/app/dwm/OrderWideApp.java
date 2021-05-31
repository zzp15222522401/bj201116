package com.atguigu.flinkgmall.app.dwm;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.atguigu.flinkgmall.bean.OrderDetail;
import com.atguigu.flinkgmall.bean.OrderInfo;
import com.atguigu.flinkgmall.bean.OrderWide;
import com.atguigu.flinkgmall.funs.DimAsyncFunction;
import com.atguigu.flinkgmall.utils.DimUtil;
import com.atguigu.flinkgmall.utils.MyKafkaUtil;
import org.apache.flink.api.common.eventtime.SerializableTimestampAssigner;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.api.common.restartstrategy.RestartStrategies;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.runtime.state.filesystem.FsStateBackend;
import org.apache.flink.streaming.api.CheckpointingMode;
import org.apache.flink.streaming.api.datastream.AsyncDataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.CheckpointConfig;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.async.AsyncFunction;
import org.apache.flink.streaming.api.functions.async.ResultFuture;
import org.apache.flink.streaming.api.functions.co.ProcessJoinFunction;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.util.Collector;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * dwm订单宽表的数据
 */
public class OrderWideApp {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        env.setParallelism(4);

        //设置检查点
        /*env.enableCheckpointing(5000L, CheckpointingMode.EXACTLY_ONCE);
        env.getCheckpointConfig().setCheckpointTimeout(60000L);
        env.getCheckpointConfig().enableExternalizedCheckpoints(CheckpointConfig.ExternalizedCheckpointCleanup.RETAIN_ON_CANCELLATION);
        env.setRestartStrategy(RestartStrategies.fixedDelayRestart(3,5000L));
        env.setStateBackend(new FsStateBackend("hdfs://hadoop102:8020/dwm/ordercheck"));
        System.setProperty("HADOOP_USER_NAEM","atguigu");*/

        //获取数据流，从dwd层的订单和订单详情的topic中
        String orderinfotopic="dwd_order_info";
        String orderdetailtopic="dwd_order_detail";
        String groupid="orderwideapp";
        String sinktopic="dwm_order_wide";

        //分别获取订单和订单详情的数据，将数据转成数据流，对数据流的格式进行转换

        FlinkKafkaConsumer<String> orderinfo = MyKafkaUtil.getkafkalog(orderinfotopic, groupid);
        FlinkKafkaConsumer<String> orderdetail = MyKafkaUtil.getkafkalog(orderdetailtopic, groupid);
        //包装数据流
        DataStreamSource<String> orderinfoDS = env.addSource(orderinfo);
        DataStreamSource<String> orderdetailDS = env.addSource(orderdetail);

        //对流数据进行格式的转换，分别对应相应的实体类

        SingleOutputStreamOperator<OrderInfo> orderinfomapDS = orderinfoDS.map(new RichMapFunction<String, OrderInfo>() {
            private SimpleDateFormat sdf;
            @Override
            public void open(Configuration parameters) throws Exception {
                super.open(parameters);
                  sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            }

            @Override
            public OrderInfo map(String jsonstr) throws Exception {
                //将数据装转换成orderinfo实体类
                OrderInfo orderInfo = JSON.parseObject(jsonstr, OrderInfo.class);
                //实体类中的数据信息补全
                //2021-05-14 20:07:10,获取事件事件，补全ts,为下面设置水位线
                long createtime = sdf.parse(orderInfo.getCreate_time()).getTime();
                orderInfo.setCreate_ts(createtime);
                return orderInfo;
            }
        });

        SingleOutputStreamOperator<OrderDetail> orderdetailmapDS = orderdetailDS.map(new RichMapFunction<String, OrderDetail>() {
            private SimpleDateFormat sdf;

            @Override
            public void open(Configuration parameters) throws Exception {
                super.open(parameters);
                sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            }

            @Override
            public OrderDetail map(String jsonstr) throws Exception {
                //装订单详情数据转换成实体类
                OrderDetail orderDetail = JSON.parseObject(jsonstr, OrderDetail.class);
                long time = sdf.parse(orderDetail.getCreate_time()).getTime();//获取时间，将时间格式转换
                orderDetail.setCreate_ts(time);
                return orderDetail;
            }
        });

        //分别对两条数据流输出
        //orderinfomapDS.print();
        //orderdetailmapDS.print();

        //将两个数据流进行join，join前需要添加事件水位线，join的key，需要keyby
        //指定两个数据流的水位线
        SingleOutputStreamOperator<OrderInfo> orderInfowatermark = orderinfomapDS.assignTimestampsAndWatermarks(
                WatermarkStrategy.<OrderInfo>forMonotonousTimestamps()
                        .withTimestampAssigner(new SerializableTimestampAssigner<OrderInfo>() {
                            @Override
                            public long extractTimestamp(OrderInfo element, long recordTimestamp) {
                                return element.getCreate_ts();
                            }
                        })
        );
        SingleOutputStreamOperator<OrderDetail> orderDetailwatermark = orderdetailmapDS.assignTimestampsAndWatermarks(
                WatermarkStrategy.<OrderDetail>forMonotonousTimestamps()
                        .withTimestampAssigner(new SerializableTimestampAssigner<OrderDetail>() {
                            @Override
                            public long extractTimestamp(OrderDetail element, long recordTimestamp) {
                                return element.getCreate_ts();
                            }
                        })
        );
        KeyedStream<OrderInfo, Long> orderInfoeyDS = orderInfowatermark.keyBy(r -> r.getId());
        KeyedStream<OrderDetail, Long> orderDetailkeyDS = orderDetailwatermark.keyBy(r -> r.getOrder_id());

        //两个数据流关联，根据订单id
        SingleOutputStreamOperator<OrderWide> orderwideDS = orderInfoeyDS.intervalJoin(orderDetailkeyDS)
                .between(Time.seconds(-5), Time.seconds(5))
                .process(new ProcessJoinFunction<OrderInfo, OrderDetail, OrderWide>() {
                    @Override
                    public void processElement(OrderInfo orderinfo, OrderDetail orderdetail, Context ctx, Collector<OrderWide> out) throws Exception {
                        out.collect(new OrderWide(orderinfo, orderdetail));
                    }
                });
        //orderwideDS.print("双流JOIN");

        //实现和用户表数据关联->不使用map是因为map是同步的，效率低
        SingleOutputStreamOperator<OrderWide> orderWideWithUserInfo = AsyncDataStream.unorderedWait(
                orderwideDS,
                new DimAsyncFunction<OrderWide>("DIM_USER_INFO") {
                    @Override
                    public void join(OrderWide orderWide, JSONObject jsonobj) {
                        try {
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            String gender = jsonobj.getString("GENDER");
                            String birthday = jsonobj.getString("BIRTHDAY");
                            Date birthdayDate = sdf.parse(birthday);
                            long birthdayDateTime = birthdayDate.getTime();
                            long daytime = System.currentTimeMillis();
                            long agetime = daytime - birthdayDateTime;
                            Long age = agetime / 1000 / 60 / 60 / 24 / 365;
                            orderWide.setUser_gender(gender);
                            orderWide.setUser_age(age.intValue());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public String getkey(OrderWide orderWide) {
                        return orderWide.getUser_id().toString();
                    }
                },
                60,
                TimeUnit.SECONDS
        );
        //orderWideWithUserInfo.print("用户表关联");

        //和sku_info商品维度表关联
        SingleOutputStreamOperator<OrderWide> orderWideWithSkuDS = AsyncDataStream.unorderedWait(
                orderWideWithUserInfo,
                new DimAsyncFunction<OrderWide>("DIM_SKU_INFO") {
                    @Override
                    public void join(OrderWide orderWide, JSONObject jsonobj) {
                        orderWide.setSku_name(jsonobj.getString("SKU_NAME"));
                        orderWide.setCategory3_id(jsonobj.getLong("CATEGORY3_ID"));
                        orderWide.setSpu_id(jsonobj.getLong("SPU_ID"));
                        orderWide.setTm_id(jsonobj.getLong("TM_ID"));

                    }

                    @Override
                    public String getkey(OrderWide orderWide) {
                        return orderWide.getSku_id().toString();
                    }
                },
                60,
                TimeUnit.SECONDS
        );
        //和base_province省份维度表关联
        SingleOutputStreamOperator<OrderWide> orderWideWithPro = AsyncDataStream.unorderedWait(
                orderWideWithSkuDS,
                new DimAsyncFunction<OrderWide>("DIM_BASE_PROVINCE") {
                    @Override
                    public void join(OrderWide orderWide, JSONObject jsonobj) {
                        orderWide.setProvince_name(jsonobj.getString("NAME"));
                        orderWide.setProvince_3166_2_code(jsonobj.getString("ISO_3166_2"));
                        orderWide.setProvince_iso_code(jsonobj.getString("ISO_CODE"));
                        orderWide.setProvince_area_code(jsonobj.getString("AREA_CODE"));

                    }

                    @Override
                    public String getkey(OrderWide orderWide) {
                        return orderWide.getProvince_id().toString();
                    }
                },
                60,
                TimeUnit.SECONDS
        );
        //和spu_info表关联
        SingleOutputStreamOperator<OrderWide> orderWideWithSpu = AsyncDataStream.unorderedWait(
                orderWideWithPro,
                new DimAsyncFunction<OrderWide>("DIM_SPU_INFO") {
                    @Override
                    public void join(OrderWide orderWide, JSONObject jsonobj) {
                        orderWide.setSpu_name(jsonobj.getString("SPU_NAME"));
                    }

                    @Override
                    public String getkey(OrderWide orderWide) {
                        return orderWide.getSpu_id().toString();
                    }
                },
                60,
                TimeUnit.SECONDS
        );
        //和base_category3表关联
        SingleOutputStreamOperator<OrderWide> orderWideWithCate = AsyncDataStream.unorderedWait(
                orderWideWithSpu,
                new DimAsyncFunction<OrderWide>("DIM_BASE_CATEGORY3") {
                    @Override
                    public void join(OrderWide orderWide, JSONObject jsonobj) {
                        orderWide.setCategory3_name(jsonobj.getString("NAME"));
                    }

                    @Override
                    public String getkey(OrderWide orderWide) {
                        return orderWide.getCategory3_id().toString();
                    }
                },
                60, TimeUnit.SECONDS
        );
        //和base_trademark表关联
        SingleOutputStreamOperator<OrderWide> orderWideWithTMDS = AsyncDataStream.unorderedWait(
                orderWideWithCate,
                new DimAsyncFunction<OrderWide>("DIM_BASE_TRADEMARK") {
                    @Override
                    public void join(OrderWide orderWide, JSONObject jsonobj) {
                        orderWide.setTm_name(jsonobj.getString("TM_NAME"));
                    }

                    @Override
                    public String getkey(OrderWide orderWide) {
                        return orderWide.getTm_id().toString();
                    }
                },
                60,
                TimeUnit.SECONDS
        );
        //打印输出看数据是否正确，是否全部关联上
        orderWideWithTMDS.print(">>>>");

        //写入到kafka对应主题
        orderWideWithTMDS
                .map(orderwide->JSON.toJSONString(orderwide))
                .addSink(MyKafkaUtil.sinktokafka(sinktopic));


        env.execute();
    }
}
