package com.atguigu.mapreduce.mapjoin;

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

/**
 * mapjoin是先将小文件存储到磁盘里，然后进行读取在关联
 * 所以我们需要在map中进行磁盘文件的读取（java的IO）
 */
public class MapJoinMapper extends Mapper<LongWritable, Text,Text, NullWritable> {
    Map<String,String> map=new HashMap();
    Text outk=new Text();
    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        context.getCounter("mapjoin", "setup").increment(1);
        URI[] cacheFiles = context.getCacheFiles();//获取我们已经自定的缓存文件（小文件）可以设置成多个
        URI cacheFile = cacheFiles[0];//我们这里只设置了一个所以我们取第一个文件

        FileSystem fs = FileSystem.get(context.getConfiguration());//获取我们的文件读取对象
        FSDataInputStream fsinput = fs.open(new Path(cacheFile));//开流
        //01	小米          读取pd文件
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fsinput));//java中的缓冲流进行文件的读取
        String line;//读取的每行数据
        while ((line = bufferedReader.readLine()) != null) {//将文件全部读取完，一直读到不为空
            String[] split = line.split("\t");
            map.put(split[0], split[1]);
        }
        //关闭流资源
        IOUtils.closeStream(bufferedReader);
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        context.getCounter("mapjoin", "map").increment(1);
        String string = value.toString();
        //1001	01	1  在这里只读取这个order文件，然后与我们上面存储的map集合进行关联查找到相对应pname
        //order中的split[1]对应我们已经处理完的map中的01
        String[] split = string.split("\t");
        String pname = map.get(split[1]);//获取到pname
        //根据要求我们定义输出的样式
        String result = split[0] + "\t" + pname + "\t" + split[2];
        outk.set(result);
        context.write(outk, NullWritable.get());
    }
}
