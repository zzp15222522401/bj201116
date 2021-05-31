package com.atguigu.mapreduce.lianxi.lianxi6;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class Partitiondriver {
    //手机号136、137、138、139开头都分别放到一个独立的4个文件中，
    // 其他开头的放到一个文件中。
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf=new Configuration();
        Job job = Job.getInstance(conf);

        job.setJarByClass(Partitiondriver.class);
        job.setMapperClass(Partionermapper.class);
        job.setReducerClass(Partitionreducer.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(PhoneBean.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(PhoneBean.class);

        FileInputFormat.addInputPath(job,new Path("E:\\aaa\\bbb\\phone_data.txt"));
        //这个表示正常按手机号分区的结果
        FileOutputFormat.setOutputPath(job,new Path("E:\\aaa\\out\\partitionoutput"));
        job.setPartitionerClass(Partition.class);
        job.setNumReduceTasks(5);

        job.waitForCompletion(true);
    }
}
