package com.atguigu.mapreduce.compress;


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
        /*configuration.set("mapreduce.map.output.compress","true");//开启map端输出压缩
        configuration.set("mapreduce.map.output.compress.codec",
                    "org.apache.hadoop.io.compress.DefaultCodec");//指定map端压缩解码器的格式

        configuration.set("mapreduce.output.fileoutputformat.compress","true");//开启reduce端输出的压缩
        configuration.set("mapreduce.output.fileoutputformat.compress.codec",//指定输出文件的压缩格式
                "org.apache.hadoop.io.compress.DefaultCodec");*/


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

        FileInputFormat.setInputPaths(job,new Path("E:\\aaa\\in\\hello.txt"));//本地的输入路径（可以是多个文件）
        FileOutputFormat.setOutputPath(job,new Path("E:\\aaa\\out\\compress_out2"));//本地输出路径

       /* FileInputFormat.setInputPaths(job,new Path(args[0]));//在hadoop上运行
        FileOutputFormat.setOutputPath(job,new Path(args[1]));*/

        boolean b = job.waitForCompletion(true);//提交任务
        if(b){
            System.out.println("提交任务成功");
        }
    }
}
