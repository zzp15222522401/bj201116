package com.atguigu.mapreduce.lianxi.lianxi6;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class Partionermapper extends Mapper<LongWritable, Text,Text,PhoneBean> {
    Text outk=new Text();
    PhoneBean outv=new PhoneBean();
    //FileSystem fs;
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String linedata = value.toString();
        String[] line = linedata.split("\t");
        outk.set(line[1]);
        int updata=Integer.parseInt(line[line.length-3]);
        int downdata=Integer.parseInt(line[line.length-2]);
        outv.setUpdata(updata);
        outv.setDowndata(downdata);
        outv.sumdata();
        context.write(outk,outv);
    }
}
