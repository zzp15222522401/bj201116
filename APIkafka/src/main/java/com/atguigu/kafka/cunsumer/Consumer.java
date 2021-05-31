package com.atguigu.kafka.cunsumer;


import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.io.IOException;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class Consumer {
    public static void main(String[] args) throws IOException {
        //1.获取对象
        Properties properties = new Properties();
        properties.load(Consumer.class.getClassLoader().getResourceAsStream("consumer.properties"));

        //properties.setProperty("enable.auto.commit","false");//将自动提交改为是手动提交
        properties.setProperty("group.id","test02");
        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<String, String>(properties);

        //2.消费消息
        kafkaConsumer.subscribe(Collections.singleton("first"));
            while(true) {
                ConsumerRecords<String, String> poll = kafkaConsumer.poll(Duration.ofSeconds(10));

                for (ConsumerRecord<String, String> stringStringConsumerRecord : poll) {
                    System.out.println(stringStringConsumerRecord);
                }

                //kafkaConsumer.commitSync();//手动同步提交
            }
                //关闭资源
        //kafkaConsumer.close();

    }
}
