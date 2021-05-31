package com.atguigu.kafka.producer;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class ProducerCallBack {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //新建對象，根據properties配置

        Properties properties = new Properties();
        try {
            properties.load(ProducerCallBack.class.getClassLoader().getResourceAsStream("producer.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<String, String>(properties);
        //发送producerRecord
       /* kafkaProducer.send(new ProducerRecord<String, String>(
                "second",
                "01",
                "01hello"
        ));*/

       for (int i=0;i<3000;i++){
           kafkaProducer.send(
                   new ProducerRecord<String, String>("third", Integer.toString(i), "hello" + i),
                   new Callback() {
                       public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                           if (recordMetadata != null) {
                               System.out.println(recordMetadata + "成功了");
                           } else
                               System.out.println("失败了");
                       }
                   });

           System.out.println(i+"条数据已发送");
       }
        //关闭资源
        kafkaProducer.close();
    }
}
