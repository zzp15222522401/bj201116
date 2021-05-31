package com.atguigu.mapreduce.lianxi.lianxi5;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class Comparereducer extends Reducer<PhoneBean,Text,Text, PhoneBean> {
    @Override
    protected void reduce(PhoneBean key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        for (Text value : values) {
            context.write(value,key);//结果我们显示的是不同的手机号作为key，流量作为value的形式输出，所以我们遍历手机号
                                    //保证即使有相同的手机号也不会只出现一次，否则就是显示相同的上下行流量只会有一个手机号
        }
    }
}
