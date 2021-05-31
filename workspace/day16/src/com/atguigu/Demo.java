package com.atguigu;

import org.junit.Test;

/**
 * 线程A用来生成包子的，线程B用来吃包子的，包子可以理解为同一资源，
 * 线程A与线程B处理的动作，一个是生产，一个是消费，那么线程A与线程B之间就存在线程通信问题。
 * 需要定义两个类，一个卖包子一个买包子，还有包子自身是否含有（可以给包子定义很多种类），买卖中间互相唤醒。
 */
public class Demo {
    public static void main(String[] args) {
        Baozi baozi=new Baozi();
        set s=new set(baozi);
        eat e=new eat(baozi);
        Thread a1=new Thread(s);
        Thread a2=new Thread(e);
        a1.start();
        a2.start();

    }
    @Test
    public void method(){

    }
}
class set implements Runnable{
    private Baozi baozi;//包子
    public set(Baozi baozi) {
        this.baozi = baozi;
    }

    @Override
    public void run() {
        while(true)
            synchronized ("set") {
                if (baozi.isBaozi()) {
                    try {
                        "set".wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();

                }
                synchronized ("eat") {
                    baozi.setBaozi(true);
                    System.out.println("包子好了");
                    "eat".notify();//唤醒吃货
                }
            }
        }
    }

class eat implements Runnable{
    private Baozi baozi;//包子
    public eat(Baozi baozi) {
        this.baozi = baozi;
    }

    @Override
    public void run() {
        while(true)
            synchronized ("eat") {
                if(!baozi.isBaozi()){
                    try {
                        "eat".wait();

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                synchronized ("set") {
                    baozi.setBaozi(false);
                    System.out.println("吃完包子了");
                    "set".notify();//唤醒包子铺
                }
            }
    }
}
class Baozi{
    boolean baozi=true;

    public Baozi(boolean baozi) {
        this.baozi = baozi;
    }

    public Baozi() {
    }

    public boolean isBaozi() {
        return baozi;
    }

    public void setBaozi(boolean baozi) {
        this.baozi = baozi;
    }
}