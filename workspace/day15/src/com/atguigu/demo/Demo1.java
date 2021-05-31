package com.atguigu.demo;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class Demo1 {
    public static void main(String[] args) {

    }
    @Test
    public void Method1(){//线程是安全的，效率较低
        long l = System.currentTimeMillis();
        StringBuffer a=new StringBuffer("0");
        for (int i = 0; i <10000000 ; i++) {
            a.append(i);
        }
        long l1 = System.currentTimeMillis();
        System.out.println(l1-l);
    }
    @Test
    public void Method2(){//线程是不安全的，效率较高
        long l = System.currentTimeMillis();
        StringBuilder a1=new StringBuilder("0");
        for (int i = 0; i <10000000 ; i++) {
            a1.append(i);
        }
        long l1 = System.currentTimeMillis();
        System.out.println(l1-l);
    }@Test
    public void Method(){//字符串运行，每次都new对象，较上面两种方法慢了N倍
        long l = System.currentTimeMillis();
        String a=new String("0");
        for (int i = 0; i <100000 ; i++) {
            a+=i;
        }
        long l1 = System.currentTimeMillis();
        System.out.println(l1-l);
    }@Test
    public void Method3(){//StringBuffer的增删改查  StringBuilder使用方法和它一样
        StringBuffer a=new StringBuffer();
        a.append("java");//增
        System.out.println(a);
        a.insert(1,"bigdata");//替换
        System.out.println(a);
        a.delete(1,5);//删
        System.out.println(a);
        a.replace(1,4,"bigdata");
        System.out.println(a);//改
        System.out.println(a.reverse());//反转

    }
    @Test
    public void Method4(){
        Date date=new Date();
        System.out.println(date);
        System.out.println(date.getTime());
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss ");//可以设置日期的格式，也可以选择默认
        System.out.println(simpleDateFormat.format(date));


        SimpleDateFormat simpleDateFormat1=new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss");
        try {
            Date parse = simpleDateFormat1.parse("2020/12/8-11:14:50");//外部输入信息可能会引出异常，这个异常不是程序员程序的问题。输入信息要与规定格式匹配
            System.out.println(simpleDateFormat1.format(parse));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void Method5(){
        Calendar calendar=Calendar.getInstance();
        System.out.println(calendar);
        //Date time = calendar.getTime();
        //System.out.println(time);
        //calendar.set();
        Date time = calendar.getTime();
        System.out.println(time);
    }
    @Test
    public void Test(){//JDK1.8之后的时间方法，需要看一看
        //localdata,localtime,localdatatime
        LocalDateTime localDateTime=LocalDateTime.now();//系统目前的时间
        int dayOfYear = localDateTime.getDayOfYear();//获取时间的某一个（年、月、日。。。。）
        System.out.println(dayOfYear);
        LocalDateTime of = LocalDateTime.of(2020, 12, 8, 12, 1, 1);//定义时间
        System.out.println(of);
        DateTimeFormatter dateTimeFormatter= DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");//指定日期的格式
        System.out.println(dateTimeFormatter.format(of));//通过定义的格式显示时间

        String str="2020年12月07日 20:20:20";
        LocalDateTime parse = LocalDateTime.parse(str, dateTimeFormatter);
        System.out.println(parse);

    }
}
