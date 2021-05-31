package com.atguigu.mapreduce.phonedatacompaeable;



import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class FlowDriver {
    /**
     * 全排序（comparable和comparator方法）以及分区内排序
     * @param args
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf=new Configuration();
        Job job= Job.getInstance(conf);

        job.setJarByClass(FlowDriver.class);
        job.setMapperClass(FlowMapper.class);
        job.setReducerClass(FlowReduce.class);

        job.setMapOutputKeyClass(FlowBean.class);
        job.setMapOutputValueClass(Text.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(FlowBean.class);

        FileInputFormat.addInputPath(job,new Path("E:\\aaa\\bbb\\phone_data.txt"));
        FileOutputFormat.setOutputPath(job,new Path("E:\\aaa\\bbb\\output2"));

        /*job.setPartitionerClass(FlowPartition.class);
        job.setNumReduceTasks(5);*/

        //自定义排序需要在job中指定
        //job.setSortComparatorClass(FlowComparator.class);
        job.waitForCompletion(true);
    }
}
