package com.atguigu.test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * 1. 已知一个文件java.txt,内容如下，统计出每个单词出现的个数！(空格隔开的就认为是一个单词)
 * dajia
 * zai beijing
 * xue xi
 * xue shen mo ne
 * xue bigdata java mysql java java
 */
public class Test1 {//单词出现个数,对应的关系，用HashMap最好解决,这里的文件我们应想到用缓冲字符流去读（有readline方法）,
                    //每一行表示一个字符串,根据每一行字符串是否含有" "（空格）来进行字符串的分割（split方法），得到字符串型的数组
                    //在根据map的get方法的到对应key值（每个单词（字符串形式））的value值（int型数字）,重复无法添加,value+1,得到对应的map集合
    public static void main(String[] args) {
        BufferedReader input=null;
        HashMap<String,Integer> map=new HashMap();
        try {
            input= new BufferedReader(
                            new FileReader("E:\\aaa\\bbb\\java.txt"));
            int count;
            int a;
            while((count=input.read())!=-1){
                String s = input.readLine();
                s+=s+" ";
                System.out.println(s);
                if(s.contains(" ")){
                    String [] split = s.split(" ");
                    for (int i=0;i<split.length;i++){
                        Integer o = map.get(split[i]);
                        if(o==null){
                            map.put(split[i],1);
                        }else{
                            map.put(split[i],o+1);
                        }

                    }
                }

            }
            System.out.println(map);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(input!=null)
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
