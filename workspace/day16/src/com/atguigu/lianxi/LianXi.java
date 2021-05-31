package com.atguigu.lianxi;

/**
 * 创建和启动2个子线程，一个打印奇数，一个打印偶数，
 * （1）要求实现交替打印。
 * （2）每个数打印间隔1秒
 */
public class LianXi {
    public static void main(String[] args) {
        Thread1 a1=new Thread1();
        Thread1 a2=new Thread1();
        a1.start();
        a2.start();

    }
}
class Thread1 extends Thread {
    private static int a;
    @Override
    public void run() {
        while (true) {

            synchronized (Thread1.class) {
                try {
                    Thread1.class.notify();
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    a ++;
                    System.out.println(Thread.currentThread().getName()+"\t"+a);
                    Thread1.class.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        }
    }
}