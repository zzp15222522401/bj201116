package com.atguigu.flinkgmall.app.dwm;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.atguigu.flinkgmall.utils.MyKafkaUtil;
import org.apache.flink.api.common.eventtime.SerializableTimestampAssigner;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.cep.CEP;
import org.apache.flink.cep.PatternFlatSelectFunction;
import org.apache.flink.cep.PatternFlatTimeoutFunction;
import org.apache.flink.cep.PatternStream;
import org.apache.flink.cep.pattern.Pattern;
import org.apache.flink.cep.pattern.conditions.SimpleCondition;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.util.Collector;
import org.apache.flink.util.OutputTag;

import java.util.List;
import java.util.Map;

//用户跳出数据
public class UserJumpDeatilApp {
    public static void main(String[] args) throws Exception {
        //TODO 创建环境，从page_ods_log获取相应数据，对数据进行筛选，输出到kafka主题上

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(4);
        //检查点设置  再次忽略
        String consumertopic="dwd_page_log";
        String groupid="userjumpdeatilapp";
        String sinkTopic="dwm_user_jump_detail";

        FlinkKafkaConsumer<String> getkafkalog = MyKafkaUtil.getkafkalog(consumertopic, groupid);
        DataStreamSource<String> kafkaDS = env.addSource(getkafkalog);
        //测试数据
        /*DataStream<String> kafkaDS = env
            .fromElements(
                "{\"common\":{\"mid\":\"101\"},\"page\":{\"page_id\":\"home\"},\"ts\":10000} ",
                "{\"common\":{\"mid\":\"102\"},\"page\":{\"page_id\":\"home\"},\"ts\":12000}",
                "{\"common\":{\"mid\":\"102\"},\"page\":{\"page_id\":\"good_list\",\"last_page_id\":" +
                    "\"home\"},\"ts\":15000} ",
                "{\"common\":{\"mid\":\"102\"},\"page\":{\"page_id\":\"good_list\",\"last_page_id\":" +
                    "\"detail\"},\"ts\":30000} "
            );*/

        //对数据进行筛选，：上次访问为null,本次就是首次访问， 本次访问页面不为空，并且上次访问不为空， 且之间间隔不超过10s认定为 ---> 跳转
        SingleOutputStreamOperator<JSONObject> mapDS = kafkaDS.map(JSON::parseObject);
        //添加水位线，数据从dwd层获取，不再是原始数据，需要为flink添加水位线
        SingleOutputStreamOperator<JSONObject> DSwithwatermaster = mapDS.assignTimestampsAndWatermarks(
                WatermarkStrategy.<JSONObject>forMonotonousTimestamps()
                        .withTimestampAssigner(new SerializableTimestampAssigner<JSONObject>() {
                            @Override
                            public long extractTimestamp(JSONObject element, long recordTimestamp) {
                                return element.getLong("ts");
                            }
                        })
        );
        //根据用户设备id分组
        KeyedStream<JSONObject, String> keyedDS = DSwithwatermaster.keyBy(jsonobj -> jsonobj.getJSONObject("common").getString("mid"));

        //根据CEP设置复杂事件筛选的模板  符合模板的是跳转数据，并不是跳出数据
        Pattern<JSONObject, JSONObject> partern = Pattern.<JSONObject>begin("first").where(
                new SimpleCondition<JSONObject>() {
                    @Override
                    public boolean filter(JSONObject jsonObj) {
                        String lastpageid = jsonObj.getJSONObject("page").getString("last_page_id");
                        if (lastpageid == null || lastpageid.length() == 0) {
                            return true;
                        }
                        return false;
                    }
                }
        ).next("second").where(
                new SimpleCondition<JSONObject>() {
                    @Override
                    public boolean filter(JSONObject jsonObj) {
                        String pageid = jsonObj.getJSONObject("page").getString("page_id");
                        if (pageid != null && pageid.length() > 0) {
                            return true;
                        }
                        return false;
                    }
                }
        ).within(Time.seconds(10));
        //将模板应用到流中
        PatternStream<JSONObject> patternDS = CEP.pattern(keyedDS, partern);
        //定义侧输出流标记
        OutputTag<String> outputTag=new OutputTag<String>("outputTag"){};


        SingleOutputStreamOperator<String> resultDS = patternDS.flatSelect(outputTag, new PatternFlatTimeoutFunction<JSONObject, String>() {
                    @Override
                    //用于处理过时数据
                    public void timeout(Map<String, List<JSONObject>> pattern, long timeoutTimestamp, Collector<String> out) throws Exception {
                        List<JSONObject> list = pattern.get("first");
                        for (JSONObject jsonObject : list) {
                            //过时数据的处理结果向下游输出
                            out.collect(jsonObject.toJSONString());
                        }

                    }
                }, new PatternFlatSelectFunction<JSONObject, String>() {
                    @Override
                    //用于处理符合模板的数据，即没有过时的数据
                    public void flatSelect(Map<String, List<JSONObject>> pattern, Collector<String> out) throws Exception {
                        //这里我们不需要对符合模板的数据进行处理，我们需要的是跳出的数据。
                    }
                }
        );
        //将结果数据输出到侧输出流
        DataStream<String> sideOutput = resultDS.getSideOutput(outputTag);
        //输出结果查看
        sideOutput.print(">>>>");

        //将数据输出到kafka的dwm主题上
        sideOutput.addSink(MyKafkaUtil.sinktokafka(sinkTopic));


        env.execute();
    }
}
