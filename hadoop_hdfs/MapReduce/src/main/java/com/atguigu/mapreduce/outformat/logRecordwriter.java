package com.atguigu.mapreduce.outformat;

import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

import java.io.IOException;

public class logRecordwriter extends RecordWriter<Text, NullWritable> {
    //文件路径和名称
    private String atguigu="E:\\aaa\\logs\\atguigu.log";
    private String other="E:\\aaa\\logs\\other.log";
    //声明FileSystem
    private FileSystem fs;
    //声明流对象
    FSDataOutputStream atguiguout;
    FSDataOutputStream otherout;
    public logRecordwriter(TaskAttemptContext job) throws IOException {
        fs=FileSystem.get(job.getConfiguration());//获取job中所有的conf的对象
        atguiguout= fs.create(new Path(atguigu));//流输出的路径
        otherout = fs.create(new Path(other));
    }

    /**
     * 编写业务代码
     * @param key
     * @param value
     * @throws IOException
     * @throws InterruptedException
     */

    public void write(Text key, NullWritable value) throws IOException, InterruptedException {
        String log = key.toString();
        if(log.contains("atguigu")){
            atguiguout.writeBytes(log+"\n");
        }else{
            otherout.writeBytes(log+"\n");
        }
    }

    /**
     * 关闭流和资源
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    public void close(TaskAttemptContext context) throws IOException, InterruptedException {
        IOUtils.closeStream(atguiguout);
        IOUtils.closeStream(otherout);
        fs.close();
    }
}
