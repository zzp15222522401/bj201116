package com.atguigu.mapreduce.outformat;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class outFormatDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf=new Configuration();
        Job job=Job.getInstance(conf);

        job.setMapperClass(outFormatMapper.class);
        job.setReducerClass(outFormatReducer.class);
        job.setJarByClass(outFormatDriver.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(NullWritable.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);

        //指定输出文件位置和类型
        job.setOutputFormatClass(logoutFormat.class);

        FileInputFormat.addInputPath(job,new Path("E:\\aaa\\in\\log.txt"));
        FileOutputFormat.setOutputPath(job,new Path("E:\\aaa\\out\\output1"));

        job.waitForCompletion(true);
    }
}
