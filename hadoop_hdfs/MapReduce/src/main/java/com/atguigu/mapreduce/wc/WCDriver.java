package com.atguigu.mapreduce.wc;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


import java.io.IOException;

public class WCDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        //获取配置信息以及job对象(用来包装MR作业的对象)
        Configuration configuration=new Configuration();
        Job job = Job.getInstance(configuration);
        //关联本程序（驱动）的jar （WCDRIVER）以及Mapper和Reducer的类
        job.setJarByClass(WCDriver.class);
        job.setMapperClass(WCMapper.class);
        job.setReducerClass(WCReducer.class);
        //指定job执行的时候Mapper阶段输出数据的类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        //指定job执行最后输出数据的类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        /*FileInputFormat.setInputPaths(job,new Path("E:\\aaa\\bbb\\java.txt"));//本地的输入路径（可以是多个文件）
        FileOutputFormat.setOutputPath(job,new Path("E:\\aaa\\bbb\\output"));//本地输出路径*/

        FileInputFormat.setInputPaths(job,new Path(args[0]));//在hadoop上运行
        FileOutputFormat.setOutputPath(job,new Path(args[1]));

        boolean b = job.waitForCompletion(true);//提交任务
        if(b){
            System.out.println("提交任务成功");
        }
    }
}
