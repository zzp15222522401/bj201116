package com.atguigu.demo;
/**
 * 文件的复制，用单个字节和多个字节的读写方式分别进行
 */

import java.io.*;

public class Demo3 {
    public static void main(String[] args) {
        String str =new String("e:/aaa/bbb.txt");
        String str1=new String("e:/aaa/bbb3.txt");
        FileOutputStream out=null;
        FileInputStream input=null;
        //byte[]b=new byte[1024];
        try {
             input=new FileInputStream(str);
             out=new FileOutputStream(str1);
             int a=0;
             while((a=input.read())!=-1){//一个字节一个字节的进行读写
                 out.write(a);
             }
            /*int a=0;
            while((a=input.read(b))!=-1){//每次读取byte[]个
                //out.write(b);这里如果这么写就会有一部分资源重复,最后一次写入的只有部分覆盖，剩下的还是之前的
                out.write(b,0,a);//写入的资源是从0-a,前几次都是1024字节，最后一次就是a个字节
            }*/
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(input!=null)
                input.close();
                if(out!=null)
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main1(String[] args) {
        String str =new String("e:/aaa/bbb.txt");
        try {
            FileOutputStream out = new FileOutputStream(str,true);
            byte[]b=new byte[]{100,102,103,104,105,106,78,100};
            out.write(b);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
