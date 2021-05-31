package com.atguigu.litaldata;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.CombineTextInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class Driver1 {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf=new Configuration();
        Job job=Job.getInstance(conf);
        job.setJarByClass(Driver1.class);
        job.setMapperClass(Mapper1.class);
        job.setReducerClass(Reducer1.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        //专门用于处理小文件的方法
        CombineTextInputFormat.setMaxInputSplitSize(job,3000);
        job.setInputFormatClass(CombineTextInputFormat.class);
        //job.setNumReduceTasks(3);
        FileInputFormat.addInputPath(job,new Path("E:\\aaa\\bbb\\"));
        FileOutputFormat.setOutputPath(job,new Path("E:\\aaa\\bbb\\output2"));

        //FileInputFormat.setMaxInputSplitSize(job,);

        job.waitForCompletion(true);
    }
}
