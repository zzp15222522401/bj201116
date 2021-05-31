package com.shangguigu.test;
/**
 * 垃圾回收机制--->对应前面的Student
 * */
public class Test2 {
    public static void main(String[] args) throws InterruptedException {
        Student a=new Student(2,"adaf",20);
        Student a1=new Student(5,"iuikg",50);
        a1=new Student(1,"65465",50);
        new Student(3,"6846845",20);

        System.gc();

        try {
            Thread.sleep(2000);//等待2秒再结束main，为了看效果
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("睡醒了");
    }
}
