package com.atguigu.demo;

import java.io.*;

/**
 * 字符型IO流 Reader和Writer
 *void flush() 刷新该流的缓冲   字符流不关闭流资源不会写入成功
 */
public class Demo {
    public static void main(String[] args) {
        Reader reader = null;
        Writer writer=null;
        try {//将bbb文件复制到ccc处。
             reader=new FileReader("e:/aaa/bbb.txt");
             writer=new FileWriter("e:/aaa/ccc.txt");
            char[] c=new char[100];
            int count;
            while((count=reader.read(c))!=-1){
                writer.write(c,0,count);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                writer.close();
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("完成");
    }
}
