package com.atguigu.mapreduce.lianxi.lianxi2;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

public class ReduceJoinMapper extends Mapper<LongWritable, Text,Text,ReduceJoinBean> {

    Text outk=new Text();
    ReduceJoinBean outv=new ReduceJoinBean();
    FileSplit fileSplit;
    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        fileSplit= (FileSplit) context.getInputSplit();
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String string = value.toString();

        if(fileSplit.getPath().getName().contains("order")){
            String[] split = string.split("\t");
            outv.setAmout(Integer.parseInt(split[2]));
            outv.setId(split[0]);
            outv.setPid(split[1]);
            outv.setPname("");
            outv.setTatol("order");
            outk.set(split[1]);
        }else{
            String[] split = string.split("\t");
            outv.setAmout(0);
            outv.setId("");
            outv.setPid(split[0]);
            outv.setPname(split[1]);
            outv.setTatol("pd");
            outk.set(split[0]);
        }
        context.write(outk,outv);
    }
}
