package com.atguigu.mapreduce.lianxi.lianxi5;




import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class Comparedriver {//谁排序就要把谁作为mapper(reducer输入)阶段输出的key
    //手机号136、137、138、139开头都分别放到一个独立的4个文件中，
    // 其他开头的放到一个文件中。
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf=new Configuration();
        Job job = Job.getInstance(conf);

        job.setJarByClass(Comparedriver.class);
        job.setMapperClass(Comparemapper.class);
        job.setReducerClass(Comparereducer.class);

        job.setMapOutputKeyClass(PhoneBean.class);
        job.setMapOutputValueClass(Text.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(PhoneBean.class);

        FileInputFormat.addInputPath(job,new Path("E:\\aaa\\bbb\\phone_data.txt"));
        //这个输出表示按总流量由大到小排序的结果（内部排序了，不是全部排序）
        FileOutputFormat.setOutputPath(job,new Path("E:\\aaa\\out\\partitionoutput1"));

        job.setPartitionerClass(Partition.class);
        job.setNumReduceTasks(5);

        job.waitForCompletion(true);
    }
}
