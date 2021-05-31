package com.shangguigu.demo.demo1;

public class Animal implements LiveAble {
    @Override
    public void eat() {
        System.out.println("吃东西");
    }

    @Override
    public void breathe() {
        System.out.println("吸入氧气呼出二氧化碳");
    }

    @Override/*默认的方法可以去实现也可以不实现，看需求*/
    public void sleep() {
        System.out.println("闭上眼睛睡觉");
    }
}
