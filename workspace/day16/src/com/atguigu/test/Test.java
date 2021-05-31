package com.atguigu.test;
//单人生产玩具50个，每200毫秒生产一个，当生产到20个时，加入吃馒头的子线程，要求每1秒吃一个，吃完三个后结束继续生产。
public class Test {
    public static void main(String[] args) {
        Thread1 a=new Thread1();
        Thread A=new Thread(a);

        for (int i = 1; i <=50 ; i++) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("这是生产的第"+i+"个");
            if(i==20){
                try {
                    A.start();
                    A.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
class Thread1 implements Runnable {

    @Override
    public void run() {
        for (int i = 1; i <=3 ; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("这是吃完的第"+i+"个 ");
        }
    }
}