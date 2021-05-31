package com.atguigu.demo;

public class Demo3 {
    public static void main(String[] args) {
        Thread t = new Thread(){
            public void run(){
                System.out.println(getName() + "的优先级：" + getPriority());
            }
        };
        t.setPriority(Thread.MAX_PRIORITY);
        t.start();

        System.out.println(Thread.currentThread().getName() +"的优先级：" + Thread.currentThread().getPriority());
    }
}
