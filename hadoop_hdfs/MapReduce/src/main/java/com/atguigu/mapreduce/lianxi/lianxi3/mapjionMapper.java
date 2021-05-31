package com.atguigu.mapreduce.lianxi.lianxi3;

import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class mapjionMapper extends Mapper<LongWritable, Text,Text, NullWritable> {

    Text outk=new Text();
    FileSystem fs;
    Map<String,String> map=new HashMap();
    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        URI[] cacheFiles = context.getCacheFiles();
        URI cacheFile = cacheFiles[0];//获取缓存文件的URI

        fs=FileSystem.get(context.getConfiguration());
        FSDataInputStream fsinput = fs.open(new Path(cacheFile));
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(fsinput));
        String readLine;
        while ((readLine = bufferedReader.readLine())!=null){
            String[] split = readLine.split("\t");
            map.put(split[0],split[1]);
        }
        IOUtils.closeStream(bufferedReader);
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String string = value.toString();
        String[] split1 = string.split("\t");
        String pname=map.get(split1[1]);
        //System.out.println(pname);
        String outkk=split1[0]+"\t"+pname+"\t"+split1[2];
        outk.set(outkk);
        context.write(outk,NullWritable.get());
    }
}
