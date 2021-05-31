package com.atguigu.flinkgmall.app.dws;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.atguigu.flinkgmall.bean.VisitorStats;
import com.atguigu.flinkgmall.utils.ClickhouseUtil;
import com.atguigu.flinkgmall.utils.DateTimeUtil;
import com.atguigu.flinkgmall.utils.MyKafkaUtil;
import org.apache.flink.api.common.eventtime.SerializableTimestampAssigner;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.tuple.Tuple4;
import org.apache.flink.streaming.api.CheckpointingMode;
import org.apache.flink.streaming.api.datastream.*;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;
import org.apache.flink.streaming.api.functions.windowing.ProcessWindowFunction;
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.util.Collector;


import java.time.Duration;
import java.util.Date;

//访客表的处理程序
public class VisitorStatsApp {
    public static void main(String[] args) throws Exception {
        //TODO 获取环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(4);
        //检查点的设置 在此忽略
        //env.enableCheckpointing(5000L, CheckpointingMode.EXACTLY_ONCE);

        //TODO 设置kafka消费主题和消费者组和输出topic
        String groupId = "visitor_stats_app";
        String pageViewSourceTopic = "dwd_page_log";
        String uniqueVisitSourceTopic = "dwm_unique_visit";
        String userJumpDetailSourceTopic = "dwm_user_jump_detail";

        //TODO 分别从不同主题上获取访客表所需数据  page_loge,UV数据,userjump用户跳出数据
        FlinkKafkaConsumer<String> pageLogKafkaSourece = MyKafkaUtil.getkafkalog(pageViewSourceTopic, groupId);
        FlinkKafkaConsumer<String> UVKafkaSource = MyKafkaUtil.getkafkalog(uniqueVisitSourceTopic, groupId);
        FlinkKafkaConsumer<String> userJumpKafkaSource = MyKafkaUtil.getkafkalog(userJumpDetailSourceTopic, groupId);
        //将三个数据源分别添加到数据流中
        DataStreamSource<String> pageLogDS = env.addSource(pageLogKafkaSourece);
        DataStreamSource<String> UVDS = env.addSource(UVKafkaSource);
        DataStreamSource<String> userJumpDS = env.addSource(userJumpKafkaSource);

        //测试从三个主题上是否能获取到数据
       /* pageLogDS.print("****");
        UVDS.print("####");
        userJumpDS.print("$$$$");*/

        //TODO 将三个数据流数据类型转换成访客类型
        //1.页面日志数据转换
        SingleOutputStreamOperator<VisitorStats> pageLogMapVistorDS = pageLogDS.map(
                new MapFunction<String, VisitorStats>() {
                    @Override
                    public VisitorStats map(String jsonStr) throws Exception {
                        JSONObject jsonObject = JSON.parseObject(jsonStr);
                        VisitorStats visitorStats = new VisitorStats(
                                "",
                                "",
                                jsonObject.getJSONObject("common").getString("vc"),
                                jsonObject.getJSONObject("common").getString("ch"),
                                jsonObject.getJSONObject("common").getString("ar"),
                                jsonObject.getJSONObject("common").getString("is_new"),
                                0L,
                                1L,
                                0L,
                                0L,
                                jsonObject.getJSONObject("page").getLong("during_time"),
                                jsonObject.getLong("ts")

                        );
                        String lastPageId = jsonObject.getJSONObject("page").getString("last_page_id");
                        if (lastPageId == null || lastPageId.length() == 0) {
                            visitorStats.setSv_ct(1L);
                        }
                        return visitorStats;
                    }
                }
        );
        //2.独立访客数据转换
        SingleOutputStreamOperator<VisitorStats> UVMapVisitorDS = UVDS.map(new MapFunction<String, VisitorStats>() {
            @Override
            public VisitorStats map(String jsonStr) throws Exception {
                JSONObject jsonObject = JSON.parseObject(jsonStr);
                VisitorStats visitorStats = new VisitorStats(
                        "",
                        "",
                        jsonObject.getJSONObject("common").getString("vc"),
                        jsonObject.getJSONObject("common").getString("ch"),
                        jsonObject.getJSONObject("common").getString("ar"),
                        jsonObject.getJSONObject("common").getString("is_new"),
                        1L,
                        0L,
                        0L,
                        0L,
                        0L,
                        jsonObject.getLong("ts")
                );
                return visitorStats;
            }
        });
        //3.用户跳出数据转换
        SingleOutputStreamOperator<VisitorStats> userJumpMapVisitorDS = userJumpDS.map(new MapFunction<String, VisitorStats>() {
            @Override
            public VisitorStats map(String jsonStr) throws Exception {
                JSONObject jsonObject = JSON.parseObject(jsonStr);
                VisitorStats visitorStats = new VisitorStats(
                        "",
                        "",
                        jsonObject.getJSONObject("common").getString("vc"),
                        jsonObject.getJSONObject("common").getString("ch"),
                        jsonObject.getJSONObject("common").getString("ar"),
                        jsonObject.getJSONObject("common").getString("is_new"),
                        0L,
                        0L,
                        0L,
                        1L,
                        0L,
                        jsonObject.getLong("ts")


                );
                return visitorStats;
            }
        });

        //TODO 将三条数据流关联在一起
        DataStream<VisitorStats> union = pageLogMapVistorDS.union(UVMapVisitorDS, userJumpMapVisitorDS);

        //TODO 给关联后的流添加水位线
        SingleOutputStreamOperator<VisitorStats> visitorWithWatermarkDS = union.assignTimestampsAndWatermarks(
                WatermarkStrategy.<VisitorStats>forBoundedOutOfOrderness(Duration.ofSeconds(3))
                        //.withIdleness(Duration.ofSeconds(30)) //处理空闲数据源
                        .withTimestampAssigner(new SerializableTimestampAssigner<VisitorStats>() {
                            @Override
                            public long extractTimestamp(VisitorStats visitorStats, long recordTimestamp) {
                                return visitorStats.getTs();
                            }
                        })
        );
        //TODO keyby分组
        KeyedStream<VisitorStats, Tuple4<String, String, String, String>> visitorKeyedDS = visitorWithWatermarkDS.keyBy(
                new KeySelector<VisitorStats, Tuple4<String, String, String, String>>() {
                    @Override
                    public Tuple4<String, String, String, String> getKey(VisitorStats visitorStats) throws Exception {
                        return Tuple4.of(
                                visitorStats.getVc(),
                                visitorStats.getCh(),
                                visitorStats.getAr(),
                                visitorStats.getIs_new()
                        );
                    }
                }
        );

        //TODO 根据事件时间开窗，窗口时间设置为10s
        WindowedStream<VisitorStats, Tuple4<String, String, String, String>, TimeWindow> windowDS = visitorKeyedDS
                .window(TumblingEventTimeWindows.of(Time.seconds(10)));

        //TODO  开窗后聚合，因为这里开窗后的数据类型和开窗前是一致的，所以我们聚合采用reduce就可以，当前后数据不一样时，可以使用aggregate

        SingleOutputStreamOperator<VisitorStats> reduceDS = windowDS.reduce(
                //对窗口数据进行聚合
                new ReduceFunction<VisitorStats>() {
                    @Override
                    public VisitorStats reduce(VisitorStats visitor1, VisitorStats visitor2) throws Exception {
                        visitor1.setPv_ct(visitor1.getPv_ct() + visitor2.getPv_ct());
                        visitor1.setSv_ct(visitor1.getSv_ct() + visitor2.getSv_ct());
                        visitor1.setUj_ct(visitor1.getUj_ct() + visitor2.getUj_ct());
                        visitor1.setUv_ct(visitor1.getUv_ct() + visitor2.getUv_ct());
                        visitor1.setDur_sum(visitor1.getDur_sum() + visitor2.getDur_sum());
                        return visitor1;
                    }
                },
                //填补窗口时间数据，补全数据
                new ProcessWindowFunction<VisitorStats, VisitorStats, Tuple4<String, String, String, String>, TimeWindow>() {
                    @Override
                    public void process(Tuple4<String, String, String, String> stringStringStringStringTuple4, Context context, Iterable<VisitorStats> visitorStats, Collector<VisitorStats> out) throws Exception {
                        for (VisitorStats visitorStat : visitorStats) {
                            //补全VisitorStats类中的开始和结束时间
                            visitorStat.setStt(DateTimeUtil.toYMDHms(new Date(context.window().getStart())));
                            visitorStat.setEdt(DateTimeUtil.toYMDHms(new Date(context.window().getEnd())));

                            visitorStat.setTs(System.currentTimeMillis());
                            out.collect(visitorStat);
                        }

                    }
                }
        );
        reduceDS.print(">>>>");
        //TODO 将数据写到clickhouse（先在click上建好表）
        reduceDS.addSink(ClickhouseUtil.getjdbcsink("insert into visitor_stats_1116 values(?,?,?,?,?,?,?,?,?,?,?,?)"));
        //执行
        env.execute();
    }
}
