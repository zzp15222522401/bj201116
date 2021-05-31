package com.atguigu.mapreduce.lianxi.lianxi5;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class Comparemapper extends Mapper<LongWritable,Text,PhoneBean,Text> {
    Text outv=new Text();
    PhoneBean outk=new PhoneBean();
    //FileSystem fs;
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String linedata = value.toString();
        String[] line = linedata.split("\t");
        outv.set(line[1]);
        int updata=Integer.parseInt(line[line.length-3]);
        int downdata=Integer.parseInt(line[line.length-2]);
        outk.setUpdata(updata);
        outk.setDowndata(downdata);
        outk.sumdata();
        context.write(outk,outv);//这里的需求是按流量综合的多少排序，所以需要把PhoneBean作为输出的key
    }
}
