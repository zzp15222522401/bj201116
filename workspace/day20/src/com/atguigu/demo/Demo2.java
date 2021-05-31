package com.atguigu.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Demo2 {
    public static void main(String[] args) {
        String str="e:/aaa/bbb/ddd.txt";
        File file=new File(str);
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            /*int read = fileInputStream.read();//每次读取一个,返回一个字节，读到末尾在读返回-1
            System.out.println(read);*/
            /*int a;//单个读取所有
            while((a=fileInputStream.read())!=-1){
                System.out.print((char)a);
            }*/
            int sum;
            byte[] b=new byte[10];//一般取1024的倍数
            //int read = fileInputStream.read(b);//执行一次读取byte[]个,返回读取的个数
            while((sum= fileInputStream.read(b))!=-1){//读到不是-1为止（也就是读完）
                for (int i = 0; i < sum; i++) {//每次读10个,但是最后一次可能没有10个字节了，
                            // 那么会有一部分重复，我们最后一次就剩多少读多少.
                    System.out.print((char) b[i]);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(fileInputStream!=null)
                    fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
