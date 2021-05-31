package com.atguigu.mapreduce;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


import java.io.IOException;

public class D {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf=new Configuration();
        Job job= Job.getInstance(conf);

        job.setJarByClass(D.class);
        job.setMapperClass(M.class);
        job.setReducerClass(R.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        FileInputFormat.addInputPath(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));

        boolean b = job.waitForCompletion(true);
        if (b){
            System.out.println("成功");
        }
    }
}
