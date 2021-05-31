package com.atguigu.mapreduce.lianxi.lianxi1;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class OutputFormat extends FileOutputFormat<Text, NullWritable> {//创建一个类继承Fileoutputformat，重写recordwritabele方法
    public RecordWriter<Text, NullWritable> getRecordWriter(TaskAttemptContext job) throws IOException, InterruptedException {
        logRecorddWritable lrw=new logRecorddWritable(job);//自定义一个对象 创建这个对象的类让它继承recordwritable类 重写其中的方法
        return lrw;
    }
}
