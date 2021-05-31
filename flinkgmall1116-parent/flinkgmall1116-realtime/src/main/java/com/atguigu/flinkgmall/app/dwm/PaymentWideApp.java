package com.atguigu.flinkgmall.app.dwm;

import com.alibaba.fastjson.JSON;
import com.atguigu.flinkgmall.bean.OrderWide;
import com.atguigu.flinkgmall.bean.PaymentInfo;
import com.atguigu.flinkgmall.bean.PaymentWide;
import com.atguigu.flinkgmall.utils.MyKafkaUtil;
import org.apache.flink.api.common.eventtime.SerializableTimestampAssigner;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.restartstrategy.RestartStrategies;
import org.apache.flink.runtime.state.filesystem.FsStateBackend;
import org.apache.flink.streaming.api.CheckpointingMode;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.CheckpointConfig;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.co.ProcessJoinFunction;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.util.Collector;
import org.apache.hadoop.yarn.webapp.hamlet2.Hamlet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//支付宽表处理程序
public class PaymentWideApp {
    public static void main(String[] args) throws Exception {

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(4);

        //设置检查点
        /*env.enableCheckpointing(5000L, CheckpointingMode.EXACTLY_ONCE);
        env.getCheckpointConfig().enableExternalizedCheckpoints(CheckpointConfig.ExternalizedCheckpointCleanup.RETAIN_ON_CANCELLATION);
        env.getCheckpointConfig().setCheckpointTimeout(60000L);
        env.setRestartStrategy(RestartStrategies.fixedDelayRestart(3,5000L));
        env.setStateBackend(new FsStateBackend("hdfs://hadoop102:8020/flinkgmall/check"));
        System.setProperty("HADOOP_USER_NAME","atguigu");*/

        //TODO 1.接收数据流
        String groupId = "payment_wide_group";
        String paymentInfoSourceTopic = "dwd_payment_info";
        String orderWideSourceTopic = "dwm_order_wide";
        String paymentWideSinkTopic = "dwm_payment_wide";
        //从kafka获取订单宽表的数据
        FlinkKafkaConsumer<String> orderWideKafkaSource = MyKafkaUtil.getkafkalog(orderWideSourceTopic, groupId);
        //从kafka获取支付数据
        FlinkKafkaConsumer<String> paymentInfoKafkaSource = MyKafkaUtil.getkafkalog(paymentInfoSourceTopic, groupId);

        //TODO 2.将数据添加到流中
        DataStreamSource<String> orderWideStrDS = env.addSource(orderWideKafkaSource);
        DataStreamSource<String> paymentInfoStrDS = env.addSource(paymentInfoKafkaSource);

        //TODO 3.将数据流进行格式转换
        SingleOutputStreamOperator<OrderWide> orderWideDS = orderWideStrDS.map(jsonstr -> JSON.parseObject(jsonstr, OrderWide.class));
        SingleOutputStreamOperator<PaymentInfo> paymentInfoDS = paymentInfoStrDS.map(jsonstr -> JSON.parseObject(jsonstr, PaymentInfo.class));

        //TODO 4.对数据流添加水位线
        SingleOutputStreamOperator<OrderWide> orderWideWithWatermarkDS = orderWideDS.assignTimestampsAndWatermarks(
                WatermarkStrategy.<OrderWide>forMonotonousTimestamps()
                        .withTimestampAssigner(
                                new SerializableTimestampAssigner<OrderWide>() {
                                    @Override
                                    public long extractTimestamp(OrderWide orderWide, long recordTimestamp) {
                                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                        Date createDate = null;
                                        try {
                                            createDate = sdf.parse(orderWide.getCreate_time());
                                        } catch (ParseException e) {
                                            e.printStackTrace();
                                        }
                                        return createDate.getTime();
                                    }
                                }
                        )
        );

        SingleOutputStreamOperator<PaymentInfo> paymentInfoWithWatermarkDS = paymentInfoDS.assignTimestampsAndWatermarks(
                WatermarkStrategy.<PaymentInfo>forMonotonousTimestamps()
                        .withTimestampAssigner(
                                new SerializableTimestampAssigner<PaymentInfo>() {
                                    @Override
                                    public long extractTimestamp(PaymentInfo paymentInfo, long recordTimestamp) {
                                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                        Date callbackDate = null;
                                        try {
                                            callbackDate = sdf.parse(paymentInfo.getCallback_time());
                                        } catch (ParseException e) {
                                            e.printStackTrace();
                                        }
                                        return callbackDate.getTime();
                                    }
                                }
                        )
        );
        //TODO 5.根据orderid分组
        KeyedStream<OrderWide, Long> orderWideKeyedDS = orderWideWithWatermarkDS.keyBy(orderwide -> orderwide.getOrder_id());
        KeyedStream<PaymentInfo, Long> paymentInKeyedDS = paymentInfoWithWatermarkDS.keyBy(paymentinfo -> paymentinfo.getOrder_id());
        //TODO 双流join
        SingleOutputStreamOperator<PaymentWide> joinDS = paymentInKeyedDS.intervalJoin(orderWideKeyedDS)
                .between(Time.minutes(-30), Time.minutes(0))
                .process(new ProcessJoinFunction<PaymentInfo, OrderWide, PaymentWide>() {
                    @Override
                    public void processElement(PaymentInfo paymentInfo, OrderWide orderWide, Context ctx, Collector<PaymentWide> out) throws Exception {
                        out.collect(new PaymentWide(paymentInfo, orderWide));
                    }
                });

        joinDS.print(">>>>");
        //TODO 将join的数据写到kafka相应主题
        joinDS.map(paymentwide->JSON.toJSONString(paymentwide))
                .addSink(MyKafkaUtil.sinktokafka(paymentWideSinkTopic));


        env.execute();

    }
}
