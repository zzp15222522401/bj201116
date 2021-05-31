package com.atguigu.mapreduce.phonedatacompaeable;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class FlowMapper extends Mapper<LongWritable,Text, FlowBean,Text> {
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
        context.write(flowBean,text);
    }
}
