package com.atguigu.mapreduce.lianxi.lianxi3;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class mapjoinDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException, URISyntaxException {
        Configuration conf=new Configuration();
        Job job = Job.getInstance(conf);

        job.setJarByClass(mapjoinDriver.class);
        job.setMapperClass(mapjionMapper.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(NullWritable.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);

        FileInputFormat.addInputPath(job,new Path("E:\\aaa\\in\\mapjoin"));
        FileOutputFormat.setOutputPath(job,new Path("E:\\aaa\\out"));

        //mapjoin需要将小文件先缓存到
        job.addCacheFile(new URI("file:///E:/aaa/bbb/pd.txt"));
        job.setNumReduceTasks(0);

        job.waitForCompletion(true);
    }
}
