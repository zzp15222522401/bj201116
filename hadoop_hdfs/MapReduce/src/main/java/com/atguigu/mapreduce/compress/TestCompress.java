package com.atguigu.mapreduce.compress;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionCodecFactory;
import org.apache.hadoop.io.compress.CompressionInputStream;
import org.apache.hadoop.io.compress.CompressionOutputStream;
import org.apache.hadoop.util.ReflectionUtils;
import org.junit.Test;

import java.io.*;
//在压缩和解压过程中，那个步骤需要用到压缩或解压对象，那么就对这个步骤进行包装
//压缩过程是输出用到解码器对象，所以对输出进行包装  .txt文件-->.txt.deflate
//解码是在输入时用到解码器对象，所以在输入时进行包装     .txt.deflate-->.txt
public class TestCompress {
    /**
     * 压缩
     */
    @Test
    public void compress() throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        String inputpath="E:\\aaa\\in\\hello.txt";//需要压缩的文件路径
        String outputpath="E:\\aaa\\in\\hello.txt";//压缩后的文件路径
        FileInputStream fileInputStream=new FileInputStream(new File(inputpath));
        //输入流

        Configuration conf=new Configuration();
        //获取压缩器对象
        String name="org.apache.hadoop.io.compress.DefaultCodec";
        Class<?> aClass = Class.forName(name);
        CompressionCodec codec = (CompressionCodec) ReflectionUtils.newInstance(aClass, conf);
        FileOutputStream fileOutputStream=new FileOutputStream(new File(outputpath+codec.getDefaultExtension()));
        //输出流,压缩后输出的文件一般都需要加上压缩器名，表示这个文件是以此压缩器压缩的
        CompressionOutputStream outputStream = codec.createOutputStream(fileOutputStream);

        IOUtils.copyBytes(fileInputStream,outputStream,conf);
        //文件写出
        fileInputStream.close();
        fileOutputStream.close();
        outputStream.close();

    }

    /**
     * 解压操作
     */
    @Test
    public void decompress() throws IOException {
        String input="E:\\aaa\\in\\hello.txt.deflate";
        String output="E:\\aaa\\in\\hello1.txt";
        FileInputStream fileInputStream=new FileInputStream(new File(input));
        FileOutputStream fileOutputStream=new FileOutputStream(new File(output));
        Configuration conf=new Configuration();
        //获取解码器对象
        CompressionCodec codec=new CompressionCodecFactory(conf).getCodec(new Path(input));

        //用解码器对象对输入流进行包装
        CompressionInputStream inputStream = codec.createInputStream(fileInputStream);

        IOUtils.copyBytes(inputStream,fileOutputStream,conf);

        fileInputStream.close();
        fileOutputStream.close();
        inputStream.close();

    }
}
