package com.atguigu.mapreduce.lianxi.lianxi4;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class Combinermapper extends Mapper<LongWritable, Text,Text, IntWritable> {
    Text outk=new Text();
    IntWritable outv=new IntWritable();
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String linedatas = value.toString();
        String[] words = linedatas.split(" ");
        for (String word : words) {
            outk.set(word);
            outv.set(1);
            context.write(outk,outv);
        }


    }
}
