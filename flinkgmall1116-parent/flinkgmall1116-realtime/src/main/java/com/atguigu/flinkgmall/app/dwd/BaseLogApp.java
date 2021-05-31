package com.atguigu.flinkgmall.app.dwd;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.atguigu.flinkgmall.utils.MyKafkaUtil;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.api.common.restartstrategy.RestartStrategies;
import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.runtime.state.filesystem.FsStateBackend;
import org.apache.flink.streaming.api.CheckpointingMode;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.CheckpointConfig;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.ProcessFunction;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.util.Collector;
import org.apache.flink.util.OutputTag;

import java.text.SimpleDateFormat;

/**
 * 从kafka中获取数据，并对数据进行修复
 */
public class BaseLogApp {

    public static void main(String[] args) throws Exception {
        //1.创建flink的环境对象
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        //2.设置并行度  和kafka中过的分区数相对应
        env.setParallelism(4);

        /*//3.设置检查点
        //3.1 设置检查点的间隔时间以及策略
        env.enableCheckpointing(5000L, CheckpointingMode.EXACTLY_ONCE);
        //3.2设置检查点的超时时间
        env.getCheckpointConfig().setCheckpointTimeout(60000L);
        //3.3 设置取消job后检查点是否删除
        env.getCheckpointConfig().enableExternalizedCheckpoints(CheckpointConfig.ExternalizedCheckpointCleanup.RETAIN_ON_CANCELLATION);
        //3.4 设置检查点重启策略(重启次数和重启间隔)
        env.setRestartStrategy(RestartStrategies.fixedDelayRestart(5,5000L));
        //3.5设置状态后端(将检查点保存在hdfs上)
        env.setStateBackend(new FsStateBackend("hdfs://hadoop102:8020/flinkgmall/ck"));
        //3.6往hdfs上写数据需要添加用户权限
        System.setProperty("HADOOP_USER_NAME","atguigu");*/
        //4.配置kafka环境，从kafka中获取数据(封装Mykafkautil获取kafka数据 )
        String topic ="ods_base_log";
        String consumergroup="ods_base_log_group";
        //从kafka中获取数据并转换成数据流
        FlinkKafkaConsumer<String> kafkasource = MyKafkaUtil.getkafkalog(topic, consumergroup);
        DataStreamSource<String> kafkaDS = env.addSource(kafkasource);
        //kafkaDS.print();
        //5.对数据进行修复 判断新老用户
        //5.1将读取到的数据进行格式转换，转换成JSONobject
        SingleOutputStreamOperator<JSONObject> jsonobj = kafkaDS.map(new MapFunction<String, JSONObject>() {
            @Override
            public JSONObject map(String value) throws Exception {
                return JSON.parseObject(value);
            }
        });
        SingleOutputStreamOperator<JSONObject> jsonrepair = jsonobj.keyBy(r -> r.getJSONObject("common").get("is_new"))
                .map(new RichMapFunction<JSONObject, JSONObject>() {//修复数据，不对数据进行改变
                    //定义一个状态变量用于存储用户的登录状态（是否为新用户,只要是今天之内登陆的都算新用户）
                    private transient ValueState<String> fitstup;
                    //根据时间戳判断是否为新用户
                    private SimpleDateFormat dateFormat;

                    @Override
                    //初始化
                    public void open(Configuration parameters) throws Exception {
                        super.open(parameters);
                        fitstup = getRuntimeContext().getState(new ValueStateDescriptor<String>("firstup", String.class));
                        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    }

                    @Override
                    public JSONObject map(JSONObject value) throws Exception {
                        Object isnew = value.getJSONObject("common").get("is_new");
                        Long ts = value.getLong("ts");
                        String date = dateFormat.format(ts);//获取当前登录时间
                        //如果登录用户为首次登录的话，可能是错误的，需要修复
                        if (isnew.equals("1")) {
                            if (fitstup.value() != null && fitstup.value().length() > 0) {
                                if (!fitstup.value().equals(date)) {
                                    isnew = "0";
                                    value.getJSONObject("common").put("is_new", isnew);
                                }
                            } else {//这是首次登录，更新状态
                                fitstup.update(date);
                            }
                        }
                        return value;
                    }
                });
        //定义不同的侧输出流
        OutputTag<String> startlogoutput=new OutputTag<String>("start"){};
        OutputTag<String> displaylogout=new OutputTag<String>("display"){};
        //对修复后的数据进行分流操作，将不同的日志类别（启动，曝光，页面）分发到kafka不同的主题上
        SingleOutputStreamOperator<String> splitDS = jsonrepair.process(new ProcessFunction<JSONObject, String>() {
            @Override
            public void processElement(JSONObject jsonobj, Context ctx, Collector<String> out) throws Exception {
                //获取启动日志
                JSONObject startlog = jsonobj.getJSONObject("start");
                //将接受到的json转换成string
                String jsonString = jsonobj.toJSONString();
                //判断是否为启动日志，不是启动就是页面日志
                if (startlog != null && startlog.size() > 0) {
                    ctx.output(startlogoutput, jsonString);

                } else {
                    out.collect(jsonString);
                    JSONArray display = jsonobj.getJSONArray("displays");
                    if (display != null && display.size() > 0) {
                        String page_id = jsonobj.getJSONObject("page").getString("page_id");
                        for (int i = 0; i < display.size(); i++) {
                            JSONObject displayJSONObject = display.getJSONObject(i);
                            displayJSONObject.put("page_id", page_id);
                            ctx.output(displaylogout, displayJSONObject.toJSONString());
                        }
                    }
                }
            }
        });

        DataStream<String> starttagout = splitDS.getSideOutput(startlogoutput);
        DataStream<String> distagout = splitDS.getSideOutput(displaylogout);

        starttagout.print("启动");
        distagout.print("曝光");
        splitDS.print("页面");

        //将分流后的数据发送到kafka不同主题
        String startSinkTopic = "dwd_start_log";
        String displaySinkTopic = "dwd_display_log";
        String pageSinkTopic = "dwd_page_log";

        //将不同的数据流发送到不同主题上
        starttagout.addSink(MyKafkaUtil.sinktokafka(startSinkTopic));
        distagout.addSink(MyKafkaUtil.sinktokafka(displaySinkTopic));
        splitDS.addSink(MyKafkaUtil.sinktokafka(pageSinkTopic));


        //6.执行
        env.execute();
    }
}
