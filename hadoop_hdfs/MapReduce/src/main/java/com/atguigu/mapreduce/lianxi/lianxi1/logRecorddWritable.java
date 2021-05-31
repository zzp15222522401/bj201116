package com.atguigu.mapreduce.lianxi.lianxi1;

import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

import java.io.IOException;

public class logRecorddWritable extends RecordWriter<Text, NullWritable> {
    FileSystem fs;
    FSDataOutputStream atguigu;
    FSDataOutputStream other;
    public logRecorddWritable(TaskAttemptContext job) throws IOException {
         fs=FileSystem.get(job.getConfiguration());//获取流对象
         atguigu = fs.create(new Path("e:/aaa/out/atguigu.log"));
         other = fs.create(new Path("e:/aaa/out/other.log"));

    }
    public void write(Text key, NullWritable value) throws IOException, InterruptedException {
        String string = key.toString();
        if(string.contains("atguigu")){
            atguigu.writeBytes(string+"\n");
        }else{
            other.writeBytes(string+"\n");
        }
    }

    public void close(TaskAttemptContext context) throws IOException, InterruptedException {
        IOUtils.closeStream(atguigu);
        IOUtils.closeStream(other);
        fs.close();
    }
}
