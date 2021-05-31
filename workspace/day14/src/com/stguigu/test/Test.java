package com.stguigu.test;

import java.util.Random;

public class Test {
    public static void main(String[] args) {
        Integer i1=128;
        Integer i2=128;
        System.out.println(i1==i2);

        Double d1=12.5;
        Double d2=12.5;
        System.out.println(d1==d2);

        Integer i3=new Integer(100);
        Integer i4=100;
        System.out.println(i3==i4);

        Integer i5=100;
        int i6=100;
        System.out.println(i5==i6);//i5在运算的时候，会自动拆箱
    }
    @org.junit.Test
    public void Method(){
        Random a=new Random();
        System.out.println(a.nextInt());
    }

}
