package com.atguigu.kafka.cunsumer;


import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;

import java.io.IOException;
import java.time.Duration;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;

public class ConsumerAync {
    public static void main(String[] args) throws IOException {
        //1.获取对象
        Properties properties = new Properties();
        properties.load(ConsumerAync.class.getClassLoader().getResourceAsStream("consumer.properties"));

        properties.setProperty("enable.auto.commit","false");//将自动提交改为是手动提交
        properties.setProperty("group.id","test03");
        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<String, String>(properties);

        //2.消费消息
        kafkaConsumer.subscribe(Collections.singleton("third"));
            while(true) {
                ConsumerRecords<String, String> poll = kafkaConsumer.poll(Duration.ofSeconds(10));
                for (ConsumerRecord<String, String> stringStringConsumerRecord : poll) {
                    System.out.println(stringStringConsumerRecord);
                }

                kafkaConsumer.commitAsync(new OffsetCommitCallback() {
                    @Override
                    public void onComplete(Map<TopicPartition, OffsetAndMetadata> offsets, Exception exception) {
                        System.out.println(offsets);
                    }
                });//手动异步提交
            }
        //关闭资源
       // kafkaConsumer.close();

    }
}
