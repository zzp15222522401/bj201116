package com.atguigu.mapreduce.compress;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * keyin  读取文件的位置   LongWritable
 * valuein  读取文件的每一行    Text
 *
 * keyout 输出数据的key  表示一个单词  Text
 * valueout 输出的value      1     IntWritable
 */
public class WCMapper extends Mapper<LongWritable,Text,Text, IntWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String string = value.toString();//获取每一行数据，转换成字符串方便切割
        String[] word = string.split(" ");//进行切割，按照业务需求
        for (String s : word) {
            Text wcmap= new Text(s);//遍历集合，将每个单词转换成Text类型，这就是输出的key
            IntWritable writable = new IntWritable(1);//输出的value（1）转换成Intwritable类型
            context.write(wcmap,writable);//context表示输出数据的包装对象
        }


    }
}
