package com.atguigu.flinkgmall.app.dwd;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.ververica.cdc.connectors.mysql.MySQLSource;
import com.alibaba.ververica.cdc.connectors.mysql.table.StartupOptions;
import com.alibaba.ververica.cdc.debezium.DebeziumSourceFunction;
import com.atguigu.flinkgmall.bean.TableProcess;
import com.atguigu.flinkgmall.funs.MyDebeziumDeserializationSchema;
import com.atguigu.flinkgmall.funs.TableProcessfun;
import com.atguigu.flinkgmall.funs.SideSink;
import com.atguigu.flinkgmall.utils.MyKafkaUtil;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.restartstrategy.RestartStrategies;
import org.apache.flink.api.common.state.MapStateDescriptor;
import org.apache.flink.runtime.state.filesystem.FsStateBackend;
import org.apache.flink.streaming.api.datastream.*;
import org.apache.flink.streaming.api.environment.CheckpointConfig;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.streaming.connectors.kafka.KafkaSerializationSchema;
import org.apache.flink.util.OutputTag;
import org.apache.kafka.clients.producer.ProducerRecord;

import javax.annotation.Nullable;

public class BaseDBApp {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(4);
        //设置检查点相关信息
        /*env.enableCheckpointing(5000L);
        env.getCheckpointConfig().setCheckpointTimeout(60000L);
        env.getCheckpointConfig().enableExternalizedCheckpoints(CheckpointConfig.ExternalizedCheckpointCleanup.RETAIN_ON_CANCELLATION);
        env.setRestartStrategy(RestartStrategies.fixedDelayRestart(3,5000L));

        env.setStateBackend(new FsStateBackend("hdfs://hadoop102:8020/flinkgmall/check"));
        System.setProperty("HADOOP_USER_NAME","atguigu");*/
        //TODO  从kafka中获取业务数据，转成数据流，做简单筛选
        String topic="ods_base_db_m";
        String groupid="basedbapp";

        FlinkKafkaConsumer<String> getkafkadblog = MyKafkaUtil.getkafkalog(topic, groupid);
        SingleOutputStreamOperator<JSONObject> jsonobj = env.addSource(getkafkadblog)
                .map(JSON::parseObject);
        //对获取到的数据进行简单的ETL
        SingleOutputStreamOperator<JSONObject> filterDS = jsonobj.filter(new FilterFunction<JSONObject>() {
            @Override
            public boolean filter(JSONObject value) throws Exception {
                boolean flag = value.getString("table") != null && value.getString("table").length() > 0
                        && value.getJSONObject("data") != null && value.getString("data").length() > 5;
                return flag;
            }
        });
        //filterDS.print();
        //TODO 使用flinkCDC从自定义配置表中获取数据
        DebeziumSourceFunction<String> cdcsource = MySQLSource.<String>builder()
                .hostname("hadoop102")
                .port(3306)
                .username("root")
                .password("000000")
                .databaseList("flinkgmall-realtime")
                .tableList("flinkgmall-realtime.table_process")
                .startupOptions(StartupOptions.initial())
                //自定义反序列化方式，将数据中我们需要的数据进行筛选，反序列化
                .deserializer(new MyDebeziumDeserializationSchema())
                .build();

        DataStreamSource<String> mysqlsource = env.addSource(cdcsource);

        //TODO 将两条数据流关联，做分流处理
        //定义侧输出流
        OutputTag<JSONObject> cdctag=new OutputTag<JSONObject>("cdctag"){};
        //定义广播状态描述器
        MapStateDescriptor<String, TableProcess> mapStateDescriptor =
                new MapStateDescriptor<>("table_process", String.class, TableProcess.class);
        //将配置表的数据流转换成广播流
        BroadcastStream<String> broadcastsource = mysqlsource.broadcast(mapStateDescriptor);
        //将两条流关联
        BroadcastConnectedStream<JSONObject, String> connectedStream = filterDS.connect(broadcastsource);
        //获取关联后处理完的流，即分流完的数据流
        SingleOutputStreamOperator<JSONObject> processDS = connectedStream.process(new TableProcessfun(cdctag, mapStateDescriptor));

        //TODO 侧输出流出到hbase上相对应的表中，主流输出到kafka对应的主题上
        //侧输出流输出
        DataStream<JSONObject> sideOutput = processDS.getSideOutput(cdctag);
        //sideOutput.print("<<<<<");
        //主流输出
        //processDS.print(">>>>>");

        //将侧输出流数据写到hbase上
        sideOutput.addSink(new SideSink());


        //将事实数据写到kafka的dwd主题上
        processDS.addSink(MyKafkaUtil.sinktokafkaColums(new KafkaSerializationSchema<JSONObject>() {

            @Override
            public ProducerRecord<byte[], byte[]> serialize(JSONObject jsonobj, @Nullable Long timestamp) {
                String topic = jsonobj.getString("sink_table");
                JSONObject data = jsonobj.getJSONObject("data");
                return new ProducerRecord<byte[], byte[]>(topic,data.toJSONString().getBytes());
            }
        }));

        env.execute();
    }
}
