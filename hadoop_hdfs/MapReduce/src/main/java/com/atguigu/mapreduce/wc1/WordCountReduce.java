package com.atguigu.mapreduce.wc1;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

/**
 * reducer的输入key value数据类型是和mapper输出数据的数据类型是一样的
 * 输出的数据类型看需求
 */
public class WordCountReduce extends Reducer<Text, IntWritable,Text,IntWritable> {
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int sum=0;
        for (IntWritable value : values) {
            int i = value.get();
            sum+=i;

        }

        context.write(key,new IntWritable(sum));
    }
}
