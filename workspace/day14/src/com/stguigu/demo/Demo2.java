package com.stguigu.demo;

import org.junit.Test;

import java.util.Arrays;

public class Demo2 {
    @Test
    public void Test1(){
        String str="www.atguigu.com";
        String str1="atguigu";
        System.out.println(str.length());//字符串的长度
        System.out.println(str.contains(str1));//字符串是否包含
        System.out.println(str.compareTo(str1));//字符串的对比大小，从第一位开始，逐位对比，有区分就停止
        System.out.println(str.toUpperCase());//将字符串转换成大写
        System.out.println(str.lastIndexOf("."));//最后一次出现str:中字符的位置
        System.out.println(str.indexOf("com"));//""双引号之间的字符串出现的位置，有就返回位置，没有就返回-1
        System.out.println(str.substring(5));//从第几位开始截取字符串
        System.out.println(str.substring(5, 11));//从第几位开始截取，到第几位结束。

    }
    //java","bigdata","mysqal","html等多个语言，要求把每个语言都分开，放入数组中存储起来

    private String method(String s,String s1){
        String s0=null;
        if(s.contains(s1)){
            int a=s.indexOf(s1);
           s0=s.substring(a,a+s1.length());
            return s0;
        }
        return s0;
    }
    @Test
    public void Test2(){
        String  str="java,bigdata,mysql,html,python,c++,oracle";
        str+=",";
        //根据逗号寻找编程语言的个数，为逗号数+1，
        int count=0;
        for (int i = 0; i <str.length() ; i++) {
            if(str.charAt(i)==','){
                count++;
            }
        }
        //根据语言个数创建数组，根据逗号分别截取每一个语言，截取完后将原字符串重新定义，保证下一次截取的还满足需求

        String[] arrs=new String[count];
        for (int i = 0; i <count ; i++) {
            int index = str.indexOf(",");
            arrs[i]=str.substring(0,index);
            str=str.substring(index+1);

        }
        System.out.println(Arrays.toString(arrs));


    }
    @Test
    public void Test3(){
        String str="我爱你中国1 0 a";
        byte[] bytes = str.getBytes();
        System.out.println(Arrays.toString(bytes));
        byte[] bytes1={49,48,97};
        String s=new String(bytes1);
        System.out.println(s);
    }
}
