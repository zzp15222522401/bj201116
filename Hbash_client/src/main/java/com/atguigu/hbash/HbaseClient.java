package com.atguigu.hbash;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;

public class HbaseClient {
    private Connection hbaseconnection;
    private TableName tablename=TableName.valueOf("student2");
    @Before//1.先获取对象
    public void befor(){
        Configuration configuration= HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum", "hadoop102,hadoop103,hadoop104");
        try {
            hbaseconnection=ConnectionFactory.createConnection(configuration);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @After//3.关闭资源
    public void close(){
        try {

            hbaseconnection.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test//2.测试
    public void createtable() throws IOException {
        //获取连接管理对象
        Admin admin = hbaseconnection.getAdmin();

        //创建一个ColumnFamilyDescriptor对象
        ColumnFamilyDescriptor columnFamilyDescriptor = ColumnFamilyDescriptorBuilder
                .newBuilder("info".getBytes(StandardCharsets.UTF_8))
                .build();
        //创建一个TableDescriptor
        TableDescriptor tableDescriptor = TableDescriptorBuilder
                .newBuilder(tablename)
                .setColumnFamilies(Collections.singleton(columnFamilyDescriptor))
                .build();

        admin.createTable(tableDescriptor);
        admin.close();
    }
    @Test
    public void put() throws IOException {//往表里添加数据-->先获取表的对象

        Table table=hbaseconnection.getTable(tablename);
        Put put=new Put("1001".getBytes(StandardCharsets.UTF_8));
        put.addColumn("info".getBytes(StandardCharsets.UTF_8),
                "name".getBytes(StandardCharsets.UTF_8),
                "张三".getBytes(StandardCharsets.UTF_8));
        put.addColumn("info".getBytes(StandardCharsets.UTF_8),
                "age".getBytes(StandardCharsets.UTF_8),
                "18".getBytes(StandardCharsets.UTF_8));
        put.addColumn("info".getBytes(StandardCharsets.UTF_8),
                "agent".getBytes(StandardCharsets.UTF_8),
                "男".getBytes(StandardCharsets.UTF_8));
        table.put(put);
        table.close();

    }
    @Test
    public void scan() throws IOException {//scan使用，先找对应表 然后找需要的行  遍历所有行  在遍历每一行数据  查询数据
        Table table=hbaseconnection.getTable(tablename);

        Scan scan=new Scan();
        scan.withStartRow("1001".getBytes(StandardCharsets.UTF_8));
        scan.withStopRow("1002".getBytes(StandardCharsets.UTF_8));

        ResultScanner results = table.getScanner(scan);//找到需要数据的行
        for (Result result : results) {//遍历所有行
           showresult(result);
            }

        table.close();
    }
    @Test
    public void get() throws IOException {
        Table table=hbaseconnection.getTable(tablename);
        Get get=new Get("1001".getBytes(StandardCharsets.UTF_8));
        get.addColumn("info".getBytes(StandardCharsets.UTF_8),
                "name".getBytes(StandardCharsets.UTF_8));
        Result result = table.get(get);
        showresult(result);
        table.close();

    }
    public void showresult(Result result){
        Cell[] cells = result.rawCells();//求出K-V（即每一行的数据）
        for (Cell cell : cells) {//遍历所有K-V数据
            byte[] row = CellUtil.cloneRow(cell);
            byte[] cf = CellUtil.cloneFamily(cell);
            byte[] cq = CellUtil.cloneQualifier(cell);
            long timestamp = cell.getTimestamp();
            byte[] cv = CellUtil.cloneValue(cell);
            System.out.println(new String(row,StandardCharsets.UTF_8)+","+
                    new String(cf,StandardCharsets.UTF_8)+","+
                    new String(cq,StandardCharsets.UTF_8)+","+
                    timestamp+","+
                    new String(cv,StandardCharsets.UTF_8));
        }
    }
}
