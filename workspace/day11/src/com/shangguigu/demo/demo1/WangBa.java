package com.shangguigu.demo.demo1;

public class WangBa implements Runner, Swimming {

    @Override
    public void run() {
        System.out.println("这是乌龟的跑");
    }

    @Override
    public void swim() {
        System.out.println("这是乌龟的游泳");
    }
}
