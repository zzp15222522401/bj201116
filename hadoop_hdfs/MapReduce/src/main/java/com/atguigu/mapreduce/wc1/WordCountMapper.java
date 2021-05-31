package com.atguigu.mapreduce.wc1;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * 采用一行一行的方法读取文件，然后进行切割，组成key-value的形式
 *
 */
public class WordCountMapper extends Mapper<LongWritable,Text,Text, IntWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //longwritable key 这里用不到
        String string = value.toString();//将读取的每行数据进行数据转换
        String[] s = string.split(" ");
        for (String s1 : s) {
          Text text = new Text(s1);
            IntWritable intWritable = new IntWritable(1);
            context.write(text,intWritable);
        }

    }
}
