package com.atguigu.demo;

import java.io.*;
/**
 * 缓冲流(主要针对于字节流的提升)
 * 明显提高了字节流的效率1396
 */

public class Demo1 {
    public static void main3(String[] args) {
        /**
         * 字节流的运行效率
         * 383996
         */
        long l = System.currentTimeMillis();
        FileInputStream input=null;
        FileOutputStream out=null;
        try {//缓冲字节流的读写
            input= new FileInputStream("e:/aaa/zhujie.wmv");
            out= new FileOutputStream("e:/aaa/zhujie2.wmv");

            int count;
            while((count=input.read())!=-1){
                out.write(count);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        long l1 = System.currentTimeMillis();
        System.out.println(l1-l);
    }
    public static void main2(String[] args) {
        /**
         * 缓冲流(主要针对于字节流的提升)
         * 明显提高了字节流的效率1396
         */
        long l = System.currentTimeMillis();
        BufferedInputStream input=null;
        BufferedOutputStream out=null;
        try {//缓冲字节流的读写
            input=new BufferedInputStream(
                            new FileInputStream("e:/aaa/zhujie.wmv"));
            out=new BufferedOutputStream(
                    new FileOutputStream("e:/aaa/zhujie1.wmv"));

            int count;
            while((count=input.read())!=-1){
                out.write(count);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        long l1 = System.currentTimeMillis();
        System.out.println(l1-l);
    }
    public static void main1(String[] args) {
        BufferedInputStream input=null;
        try {//缓冲字节流的读取(无论是否有缓冲字节读取汉字都会出现乱码)
            input=
                    new BufferedInputStream(
                            new FileInputStream("e:/aaa/bbb.txt"));

            int count;
            while((count=input.read())!=-1){
                System.out.print((char)count);
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
