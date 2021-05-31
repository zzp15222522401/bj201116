package com.atguigu.demo;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Arrays;

public class Demo1 {
    public static void main(String[] args) {
        String str="e:/aaa";//删除aaa目录(电脑中确保已有文件)
        File file=new File(str);
        Demo1 demo1=new Demo1();
        demo1.method(file);
    }
    //1.列出file中所有的子文件和目录
    //2.看files是属于文件还是目录
    //文件就直接删除，目录的话就进一步判断此目录下的情况（递归）
    public void method(File file){
        File[] files = file.listFiles();//返回file中所有的子目录和文件
        System.out.println(Arrays.toString(files));
        for (File file1 : files) {
            if(file1.isDirectory()){
                method(file1);
            }else{
                file1.delete();
            }
        }
        file.delete();
    }
    public static void main3(String[] args) {
        String str="d:/aaa/bbb/ccc/ddd/java.txt";//新建java.txt
        File file=new File(str);
        if(!file.exists()){//判断是否存在，不存在就创建
            File parentFile = file.getParentFile();
            //System.out.println(parentFile);
            parentFile.mkdirs();
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main2(String[] args) {
       File file = new File("e:/aaa/bbb/ccc");
        String[] list = file.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".txt");
            }
        });
        System.out.println(Arrays.toString(list));
    }
    public static void main1(String[] args) {
        File file=new File("E:/shangguiguJava/上课视频、笔记、代码/教师资料/03_尚硅谷_注解.wmv");
        File file2=new File("../day05");

        System.out.println(file.length());
    }

}
