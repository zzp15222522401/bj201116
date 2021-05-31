package com.shangguigu.demo.demo1;

public class Test4 {
    public static void main(String[] args) {
        Rabbit rabbit=new Rabbit();
        WangBa wangBa=new WangBa();
        rabbit.run();
        System.out.println("***********");
        wangBa.run();
        wangBa.swim();
    }
}

