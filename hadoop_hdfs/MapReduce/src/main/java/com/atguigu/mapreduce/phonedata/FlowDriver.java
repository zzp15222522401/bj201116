package com.atguigu.mapreduce.phonedata;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class FlowDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf=new Configuration();
        Job job=Job.getInstance(conf);

        job.setJarByClass(FlowDriver.class);
        job.setMapperClass(FlowMapper.class);
        job.setReducerClass(FlowReduce.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(FlowBean.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(FlowBean.class);
        //根据实际需求设置reducetask个数
        job.setNumReduceTasks(5);
        //根据业务需求进行分区，不设置就按照默认的进行分区(hashPartitioner)
        job.setPartitionerClass(phonePartitions.class);

        FileInputFormat.addInputPath(job,new Path("E:\\aaa\\bbb\\phone_data.txt"));
        FileOutputFormat.setOutputPath(job,new Path("E:\\aaa\\bbb\\output1"));

        /*job.setInputFormatClass(CombineTextInputFormat.class);
        CombineTextInputFormat.setMaxInputSplitSize(job, 4194304);*/

        job.waitForCompletion(true);
    }
}
