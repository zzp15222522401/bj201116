package com.atguigu.app;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.alibaba.otter.canal.protocol.Message;
import com.atguigu.constant.GmallConstants;
import com.atguigu.utils.MyKafkaSender;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;

import java.net.InetSocketAddress;
import java.util.List;

public class CanalClient {

    /**
     * TODO 连接Canal,监视mysql，将mysql数据库的数据实时的传输到kafka中
     * @param args
     * @throws InvalidProtocolBufferException
     */
    public static void main(String[] args) throws InvalidProtocolBufferException {
        //获取canal的连接对象
        CanalConnector canalConnector = CanalConnectors.newSingleConnector(new InetSocketAddress
                        ("hadoop102", 11111),
                "example", "", "");
        while(true){
            //连接canal
            canalConnector.connect();
            //指定想要监控的数据库
            canalConnector.subscribe("gmall.*");
            //获取message
            Message message = canalConnector.get(100);
            //获取Entry
            List<CanalEntry.Entry> entries = message.getEntries();
            //判断获取的Entry是否有数据
            if(entries.size()>0){
                for (CanalEntry.Entry entry : entries) {
                    //获取表名
                    String tableName = entry.getHeader().getTableName();
                    //获取数据的类型
                    CanalEntry.EntryType entryType = entry.getEntryType();
                    //判断entry的类型，我们需要的是ROWDATA类型的数据
                    if(CanalEntry.EntryType.ROWDATA.equals(entryType)){
                        //获取StoreValue
                        ByteString storeValue = entry.getStoreValue();
                        //获取的StoreValue序列化不可以，我们需要反序列化
                        CanalEntry.RowChange rowChange = CanalEntry.RowChange.parseFrom(storeValue);
                        //查看序列化后数据的类型
                        CanalEntry.EventType eventType = rowChange.getEventType();

                        //对反序列化后的数据进行解析
                        handler(tableName,eventType,rowChange);

                    }
                    else {
                        System.out.println("还没有数据，稍等...");
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }


    }
    public static void handler(String tableName, CanalEntry.EventType eventType, CanalEntry.RowChange rowChange){
        //根据表名，数据类型来判断是什么数据
        if("order_info".equals(tableName)&&CanalEntry.EventType.INSERT.equals(eventType)){
            SendTOKafka(GmallConstants.KAFKA_TOPIC_ORDER,rowChange);
        }else if("order_detail".equals(tableName)&&CanalEntry.EventType.INSERT.equals(eventType)){
      SendTOKafka(GmallConstants.KAFKA_TOPIC_ORDER_DETAIL,rowChange);
        }
        else if("user_info".equals(tableName)&&CanalEntry.EventType.INSERT.equals(eventType)||
                CanalEntry.EventType.UPDATE.equals(eventType)) {
            SendTOKafka(GmallConstants.KAFKA_TOPIC_USER_INFO,rowChange);
        }
    }

    private static void SendTOKafka(String topic,CanalEntry.RowChange rowChange) {
        List<CanalEntry.RowData> rowDatasList = rowChange.getRowDatasList();

        for (CanalEntry.RowData rowData : rowDatasList) {
            List<CanalEntry.Column> afterColumnsList = rowData.getAfterColumnsList();
            JSONObject jsonObject = new JSONObject();
            for (CanalEntry.Column column : afterColumnsList) {
                jsonObject.put(column.getName(),column.getValue());
            }
            System.out.println(jsonObject.toString());
            MyKafkaSender.send(topic,jsonObject.toString());
        }
    }
}
