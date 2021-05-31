package com.atguigu.mapreduce.reducejoin;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

public class ReduceJoinMapper extends Mapper<LongWritable, Text, Text, ReduceJoinBean> {

    Text outk = new Text();
    ReduceJoinBean outv = new ReduceJoinBean();
    FileSplit fileSplit;

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        InputSplit inputSplit = context.getInputSplit();
        fileSplit = (FileSplit) inputSplit;
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String linedata = value.toString();
        //这里的读的每行数据可能是两个表的任意一张表，所以我们需要在这之前判断是哪一个表的数据
        if (fileSplit.getPath().getName().contains("order")) {//获取文件的路径名  判断这个表是那一张表
            //1001	01	1
            String[] datas = linedata.split("\t");//对表中的数据进行切割
            outv.setOrderid(datas[0]);
            outv.setAmount(Integer.parseInt(datas[2]));
            outv.setPid(datas[1]);
            outv.setPname("");
            outv.setFlag("order");
            outk.set(datas[1]);
        } else {//这张表是PD表01	小米
            String[] datas = linedata.split("\t");//对表中的数据进行切割
            outv.setOrderid("");
            outv.setAmount(0);
            outv.setPid(datas[0]);
            outv.setPname(datas[1]);
            outv.setFlag("pd");
            outk.set(datas[0]);
        }
        context.write(outk, outv);
    }
}
