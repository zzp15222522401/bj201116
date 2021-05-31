package com.atguigu.demo;


public class Demo2 {
    public static void main(String[] args) {
        Thread02 thread02=new Thread02();
        thread02.start();//继承Thread类的线程并启动

        Thread01 thread01=new Thread01();
        Thread thread=new Thread(thread01);
        thread.start();//实现Runnable接口的线程并启动

    }
}
class Thread02 extends Thread{//继承Thread类并重写run方法
    @Override
    public void run() {
        for (int i = 1; i <=1000 ; i++) {
            if(i%2==0)
                System.out.println(i);
        }
    }
}
class Thread01 implements Runnable{//实现Runnable接口并实现run方法

    @Override
    public void run() {
        for (int i = 1; i <=1000 ; i++) {
            if(i%2!=0)
                System.out.println(i);
        }
    }
}

