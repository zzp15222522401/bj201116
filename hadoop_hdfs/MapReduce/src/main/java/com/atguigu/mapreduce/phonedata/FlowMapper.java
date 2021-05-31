package com.atguigu.mapreduce.phonedata;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class FlowMapper extends Mapper<LongWritable,Text,Text,FlowBean> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String linephone = value.toString();
        String[] phonedata = linephone.split("\t");

        int upFlow= Integer.parseInt(phonedata[phonedata.length-3]);
        int downFlow= Integer.parseInt(phonedata[phonedata.length-2]);
        //int sumFlow=upFlow+downFlow;

        Text text=new Text(phonedata[1]);

        FlowBean flowBean=new FlowBean();
        flowBean.setUpFlow(upFlow);
        flowBean.setDownFlow(downFlow);
        flowBean.setSumFlow();
        context.write(text,flowBean);
        /*// 获取当前行数据
        String linedata = value.toString();
        // 1	13736230513	192.196.100.1	www.atguigu.com	2481	24681	200
        String[] linedatas = linedata.split("\t");
        // 获取输出数据的key
        Text outk = new Text(linedatas[1]);
        // 组装输出数据的value
        FlowBean flowBean = new FlowBean();
        flowBean.setUpFlow(Integer.parseInt(linedatas[linedatas.length-3]));
        flowBean.setDownFlow(Integer.parseInt(linedatas[linedatas.length-2]));
        flowBean.setTotalFlow();
        // 将Map阶段的数据输出
        context.write(outk, flowBean);*/
    }
}
