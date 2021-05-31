package com.atguigu.mapreduce.lianxi.lianxi4;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class Combiner extends Reducer<Text, IntWritable,Text,IntWritable> {
    //处理从map端过来的数据，进行简单的合并操作
    //然后输出串给reduce在继续进行合并排序操等  combiner只有在不影响整体结果的情况下才会使用
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int sum = 0;
        for (IntWritable value : values) {
            sum+=value.get();
        }
        context.write(key,new IntWritable(sum));
    }
}
