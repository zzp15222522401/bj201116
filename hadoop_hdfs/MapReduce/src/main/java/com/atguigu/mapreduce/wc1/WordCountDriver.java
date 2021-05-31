package com.atguigu.mapreduce.wc1;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class WordCountDriver {//在本地运行       在IDEA中直接向集群提交任务
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
    //获取配置信息和job对象
        Configuration conf=new Configuration();
        Configuration configuration = new Configuration();

        configuration.set("fs.defaultFS", "hdfs://hadoop102:8020");
        configuration.set("mapreduce.framework.name","yarn");
        configuration.set("mapreduce.app-submission.cross-platform","true");
        configuration.set("yarn.resourcemanager.hostname","hadoop103");

        Job job=Job.getInstance(conf);
        job.setJar("E:\\shangguiguJava\\IDE\\code\\IDExuexidaima\\hadoop_hdfs\\MapReduce\\target\\MapReduce-1.0-SNAPSHOT.jar");
        //指定job运行中mapper和reducer的类
        job.setJarByClass(WordCountDriver.class);
        job.setMapperClass(WordCountMapper.class);
        job.setReducerClass(WordCountReduce.class);
        //指定在job运行时mapper阶段输出的数据类型的类
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        //指定最终输出结果的数据类型的类
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        //设置路径
        /*FileInputFormat.addInputPath(job,new Path("E:\\aaa\\bbb\\java.txt"));
        FileOutputFormat.setOutputPath(job,new Path("E:\\aaa\\bbb\\output1"));*/

        FileInputFormat.addInputPath(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));

        boolean b = job.waitForCompletion(true);
        if(b){
            System.out.println("恭喜你成功了");
        }

    }
}
