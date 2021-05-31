package com.atguigu.mapreduce.lianxi.lianxi4;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class Combinerdriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf =new Configuration();
        Job job = Job.getInstance(conf);

        job.setJarByClass(Combinerdriver.class);
        job.setMapperClass(Combinermapper.class);
        job.setReducerClass(Combinerreduce.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        job.setCombinerClass(Combiner.class);
        FileInputFormat.addInputPath(job,new Path("E:\\aaa\\in\\hello.txt"));
        FileOutputFormat.setOutputPath(job,new Path("E:\\aaa\\out\\wordcountout"));

        job.waitForCompletion(true);
    }
}
