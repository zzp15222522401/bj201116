package com.atguigu.demo;

public class Demo4 {//倒计时10秒中
    public static void main(String[] args) {
        /*for (int i = 10; i >=0 ; --i) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("倒计时："+i+"秒");
        }
        System.out.println("倒计时结束");*/



        //强行加塞：
        Thread11 thread=new Thread11();
        thread.start();
        for (int i = 0; i <10 ; i++) {
            System.out.println(i);
            if(i==5) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
class Thread11 extends Thread{
    @Override
    public void run() {
        for (int i = 0; i <3 ; i++) {
            System.out.println("这是第强行加塞进来的");
        }
    }
}