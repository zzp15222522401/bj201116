package com.atguigu.lianxi;

/**
 * 18、用实现Runnable接口的方式，启动一个线程完成在线程中打印1-100的数字
 */
public class Lian1 {
    public static void main(String[] args) {
        Print p=new Print();//实例化目标对象
        Thread thread=new Thread(p);//实力化线程对象
        thread.start();
    }
}
class Print implements Runnable{

    @Override
    public void run() {
        for (int i = 1; i <=100 ; i++) {
            System.out.print(i+"\t");
            if(i%10==0)
                System.out.println();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}