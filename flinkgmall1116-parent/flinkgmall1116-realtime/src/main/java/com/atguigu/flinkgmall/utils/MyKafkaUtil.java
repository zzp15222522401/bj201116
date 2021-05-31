package com.atguigu.flinkgmall.utils;

import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;
import org.apache.flink.streaming.connectors.kafka.KafkaSerializationSchema;
import org.apache.hbase.thirdparty.com.google.gson.JsonObject;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import javax.annotation.Nullable;
import java.util.Properties;

//kafka生产者和消费者的构造方法
public class MyKafkaUtil {
    private static String kafkaserver="hadoop102:9092,hadoop103:9092,hadoop104:9092";
    private static String DEFAULT_TOPIC="default_topic";
    public static FlinkKafkaConsumer<String> getkafkalog(String topic,String groupid){

        Properties properties = new Properties();
        //设置bootstrap-server的地址
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,kafkaserver);
        //设置消费者组
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG,groupid);
        FlinkKafkaConsumer<String> kafkaConsumer = new FlinkKafkaConsumer<String>(topic, new SimpleStringSchema(), properties);
        return kafkaConsumer;
    }
    //构建kafka生产者
    public static FlinkKafkaProducer<String> sinktokafka(String topic){
        //kafka默认数据分区策略是粘性分区，有可能有的partition暂时没有数据，这里设置为循环分区，可以使得数据均匀分到每个分区上
        Properties prop=new Properties();
        prop.setProperty(ProducerConfig.TRANSACTION_TIMEOUT_CONFIG,15*60*1000+"");//事务超时时间
        prop.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,kafkaserver);
        return  new FlinkKafkaProducer<String>(
                DEFAULT_TOPIC,
                new KafkaSerializationSchema<String>() {
            @Override
            public ProducerRecord<byte[], byte[]> serialize(String element, @Nullable Long timestamp) {
                return new ProducerRecord<byte[], byte[]>(topic,null,element.getBytes());
            }},
                prop,
                FlinkKafkaProducer.Semantic.EXACTLY_ONCE
        );
        //return new FlinkKafkaProducer<String>(kafkaserver,topic,new SimpleStringSchema()); //此为一般sink模式
    }
    //构建kafka生产者，将主流数据全部发送到kafka不同的主题上 （不只是一个主题了）
    public static <T>FlinkKafkaProducer<T> sinktokafkaColums(KafkaSerializationSchema kafkaSerializationSchema){
        Properties properties=new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,kafkaserver);
        //设置生产者的超时时间
        properties.setProperty(ProducerConfig.TRANSACTION_TIMEOUT_CONFIG,15*60*1000+"");
        return new FlinkKafkaProducer<T>(DEFAULT_TOPIC, kafkaSerializationSchema,properties, FlinkKafkaProducer.Semantic.EXACTLY_ONCE);
    }
    public static String getKafkaDDL(String topic,String groupid){
        String str= "'connector' = 'kafka',"+
        "'topic' = '"+topic+"',"+
        "'properties.group.id' = '"+groupid+"',"+
        "'scan.startup.mode' = 'latest-offset',"+
        "'properties.bootstrap.servers' = '"+kafkaserver+"',"+
        "'format' = 'json'";
        return str;
    }
}
