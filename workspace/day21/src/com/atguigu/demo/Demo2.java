package com.atguigu.demo;

import java.io.*;

public class Demo2 {
    public static void main(String[] args) {
        try {//四大顶级抽象父类
            /**
             * InputStream/InputStream/Writer/Reader
             */

            //字节流输入输出
            FileInputStream input=new FileInputStream("e:/aaa/bbb.txt");
            FileOutputStream out=new FileOutputStream("e:/aaa/bbb.txt");
            //字符流输入输出
            FileReader input2=new FileReader("e:/aaa/bbb.txt");
            FileWriter out2=new FileWriter("e:/aaa/bbb.txt");
            //缓冲字节流输入输出
            BufferedInputStream input1=new BufferedInputStream(new FileInputStream("e:/aaa/bbb.txt"));
            BufferedOutputStream out1=new BufferedOutputStream(new FileOutputStream("e:/aaa/bbb.txt"));
            BufferedReader i1=new BufferedReader(new FileReader("e:/aaa/bbb.txt"));//特有方法 readline()读一行
            BufferedWriter i2=new BufferedWriter(new FileWriter("e:/aaa/bbb.txt"));
            //对象流
            ObjectInputStream input3=new ObjectInputStream(new FileInputStream("e:/aaa/bbb.txt"));
            ObjectOutputStream out3=new ObjectOutputStream(new FileOutputStream("e:/aaa/bbb.txt"));
            //转换流
            InputStreamReader input4=new InputStreamReader(new FileInputStream("e:/aaa/bbb.txt"));
            ObjectOutputStream out4=new ObjectOutputStream(new FileOutputStream("e:/aaa/bbb.txt"));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
