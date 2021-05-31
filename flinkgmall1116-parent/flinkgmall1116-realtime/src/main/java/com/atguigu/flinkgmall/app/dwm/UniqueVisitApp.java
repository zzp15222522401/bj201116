package com.atguigu.flinkgmall.app.dwm;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.atguigu.flinkgmall.utils.MyKafkaUtil;
import org.apache.flink.api.common.functions.RichFilterFunction;
import org.apache.flink.api.common.state.StateTtlConfig;
import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.api.common.time.Time;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;

import java.text.SimpleDateFormat;

//TODO 求UV数据  独立访客
public class UniqueVisitApp {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(4);
        //省略检查点的设置了
        String topic="dwd_page_log";
        String groupid="uniquevisitapp";
        String sinktopic="dwm_unique_visit";
        //从kafka的页面日志读取数据，转成流
        FlinkKafkaConsumer<String> kafkaConsumer = MyKafkaUtil.getkafkalog(topic, groupid);

        DataStreamSource<String> kafkaDS = env.addSource(kafkaConsumer);

        //对流数据做筛选,先对数据格式转换 ,方便操作
        SingleOutputStreamOperator<JSONObject> jsonobjDS = kafkaDS.map(JSON::parseObject);
        //根据登录机器做分组
        KeyedStream<JSONObject, String> keyedDS = jsonobjDS.keyBy(jsonobj -> jsonobj.getJSONObject("common").getString("mid"));
        //将数据进行筛选，
        SingleOutputStreamOperator<JSONObject> UVDS = keyedDS.filter(new RichFilterFunction<JSONObject>() {
            private ValueState<String> lastupdata;
            private SimpleDateFormat sdf;

            @Override
            public void open(Configuration parameters) throws Exception {
                super.open(parameters);
                //定义状态用于保存本机的登陆时间
                ValueStateDescriptor<String> stateDescriptor = new ValueStateDescriptor<>("lastupdata", String.class);
                //设置状态的存活时间，我们需求是每天的独立访客，所以状态保存时间为1天
                StateTtlConfig stateTtlConfig = StateTtlConfig.newBuilder(Time.days(1L))
                        //设置状态修改后，失效时间是否会被修改，默认是
                        //.setUpdateType(StateTtlConfig.UpdateType.OnCreateAndWrite)
                        //设置状态过期后，如果获取状态是否返回，默认是
                        //.setStateVisibility(StateTtlConfig.StateVisibility.NeverReturnExpired)
                        .build();
                stateDescriptor.enableTimeToLive(stateTtlConfig);
                lastupdata = getRuntimeContext().getState(stateDescriptor);
                sdf = new SimpleDateFormat("yyyy-MM-dd");

            }

            @Override
            public boolean filter(JSONObject jsonObject) throws Exception {
                String last_page_id = jsonObject.getJSONObject("page").getString("last_page_id");
                //如果页面的记录上一页有数据的话，那么这条数据就过滤掉（不是第一次登录）
                if (last_page_id != null && last_page_id.length() > 0) {
                    return false;
                }
                //如果页面ts有数据的话,且访问时间是今天的话，那么就更新状态时间为今天
                Long ts = jsonObject.getLong("ts");//本次登录的时间
                String currentts = sdf.format(ts);
                String lastupts = lastupdata.value();//状态记录的上一次登录的时间
                if (lastupts != null && lastupts.length() > 0 && lastupts.equals(currentts)) {
                    return false;

                } else {
                    lastupdata.update(currentts);
                    return true;
                }

            }
        });

        UVDS.print(">>>");
        //将获取的UV数据写到kafka对应的主题上
        UVDS.map(jsonobj->jsonobj.toJSONString())
                .addSink(MyKafkaUtil.sinktokafka(sinktopic));

        env.execute();
    }
}
