package com.atguigu.demo;

import java.io.*;

public class Demo4 {


    public static void main(String[] args) {
        /**
         * 转换流，（字节转成字符（无法逆向））
         * 可以实现不同编码格式之间的转化（GBK-->UTF-8）在构造器中声明要读取（要和原文件格式一样）和写出的格式
         */
        OutputStreamWriter out=null;
        InputStreamReader input=null;
        try {
            InputStream inputStream=new FileInputStream("e:/aaa/ccc1.txt");
            input=new InputStreamReader(inputStream,"GBK");
            OutputStream outputStream=new FileOutputStream("e:/aaa/ccc2.txt");
            out=new OutputStreamWriter(outputStream,"UTF-8");

            int a;
            while((a=input.read())!=-1){
                out.write(a);
            }



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main1(String[] args) {
        InputStreamReader input=null;
        try {
            InputStream inputStream=new FileInputStream("e:/aaa/ccc.txt");
             input=new InputStreamReader(inputStream);

             int a;
             while((a=input.read())!=-1){
                 System.out.print((char)a);
             }



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
