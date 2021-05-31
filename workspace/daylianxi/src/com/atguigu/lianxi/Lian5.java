package com.atguigu.lianxi;

import org.junit.Test;

import java.util.Arrays;

public class Lian5 {
    public static void main(String[] args) {
        /**
         *              *
         *             ***
         *            *****
         *           *******
         *          *********
         *
         *          i       " "     *
         *          0      n-i       1
         *          1        3      3
         *          2        2      5
         *          3        1      7
         *          n        0      2*n+1
         *
         */
        for (int i = 0; i <5; i++) {//控制行数
            for (int k = 0; k <5-i-1 ; k++) {//控制空格数
                System.out.print(" ");
            }
            for (int j = 0; j <2*i+1 ; j++) {//控制*数
                System.out.print("*");
            }
            System.out.println();
        }
    }
    @Test
    public void main1(){
        String s="342567891";//字符串进行升序
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        System.out.println(chars);

    }
    @Test
    public void main2(){//monday,tuesday,wednesday,thursday,friday,saturday,sunday
        Day[] values = Day.values();
        for (Day value : values) {
            System.out.println(value);
        }


    }
    @Test
    public void main3(){

    }
}
class Lan1{//10、编写一个懒汉式单例设计模式
    private static Lan1 lan;
    private Lan1() {}
    public static Lan1 getLan(){
        lan=null;
        if(lan==null){
            lan=new Lan1();
        }
        return lan;
    }
}
enum Day{
    Monday("monday"),Tuesday("tuesday"),Wednesday("wednesday"),Thursday("thursday"),Friday("friday"),Saturday("saturday"),Sunday("sunday");
    private String day;

    Day(String day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return day;
    }
}
class Lian5_1{
    //18、用实现Runnable接口的方式，
    // 启动一个线程完成在线程中打印1-100的数字
    public static void main(String[] args) {
        Realize realize=new Realize();
        Thread a=new Thread(realize);
        a.start();
    }
    static class Realize implements Runnable{
        @Override
        public void run() {
            for (int i = 1; i <101 ; i++) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(i);
            }


        }
    }
}