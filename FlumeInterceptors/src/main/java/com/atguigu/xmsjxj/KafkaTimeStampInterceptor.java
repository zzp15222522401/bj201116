package com.atguigu.xmsjxj;

import com.google.gson.JsonParser;
import org.apache.avro.io.BinaryEncoder;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.specific.SpecificDatumWriter;
import org.apache.flume.source.avro.AvroFlumeEvent;
import org.apache.kafka.clients.consumer.ConsumerInterceptor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class KafkaTimeStampInterceptor implements ConsumerInterceptor<String,byte[]> {
    private JsonParser parser=new JsonParser();

    private ByteArrayOutputStream tempOutStream = new ByteArrayOutputStream();
    private BinaryEncoder encoder = EncoderFactory.get().directBinaryEncoder(tempOutStream, null);
    private SpecificDatumWriter<AvroFlumeEvent> writer = new SpecificDatumWriter<>(AvroFlumeEvent.class);

    @Override
    public ConsumerRecords<String, byte[]> onConsume(ConsumerRecords<String, byte[]> records) {
        //生成临时的分区和Records的缓存
        Map<TopicPartition, List<ConsumerRecord<String, byte[]>>> temp = new HashMap<>();



        //遍历每一个分区，修改该分区的所有Record的格式
        for (TopicPartition partition : records.partitions()) {
            //获取旧分区数据
            List<ConsumerRecord<String, byte[]>> list = records.records(partition);

            //将普通的json数据替换为Event数据
            List<ConsumerRecord<String, byte[]>> newList = list.stream().map(originalRecord -> {
                String line = new String(originalRecord.value(), StandardCharsets.UTF_8);
                Map<CharSequence, CharSequence> header = new HashMap<>();
                header.put("timestamp", parser.parse(line).getAsJsonObject().get("ts").getAsString());

                tempOutStream.reset();
                AvroFlumeEvent e = new AvroFlumeEvent(header, ByteBuffer.wrap(originalRecord.value()));
                try {
                    writer.write(e, encoder);
                    encoder.flush();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

                return new ConsumerRecord<String, byte[]>(
                        originalRecord.topic(),
                        originalRecord.partition(),
                        originalRecord.offset(),
                        originalRecord.key(),
                        tempOutStream.toByteArray()
                );
            }).collect(Collectors.toList());

            //更新改分区记录
            temp.put(partition, newList);
        }
        return new ConsumerRecords<>(temp);
    }

    @Override
    public void onCommit(Map<TopicPartition, OffsetAndMetadata> offsets) {

    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
