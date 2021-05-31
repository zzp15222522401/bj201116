package com.atguigu.litaldata;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class Mapper1 extends Mapper<LongWritable, Text,Text, IntWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String string = value.toString();
        String[] s = string.split(" ");
        for (String s1 : s) {
            Text text=new Text(s1);
            IntWritable intWritable=new IntWritable(1);
            context.write(text,intWritable);
        }
    }
}
