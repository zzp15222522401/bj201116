package com.atguigu.test;

import org.junit.Test;

public class Test31 {
    public static void main(String[] args) {
        Integer a1=100;
        //在自动包装过程中，调用了Integer valueOf方法，此方法定义在Integer缓存范围（-128~127）内
        //返回原数据，在超过缓存范围后会定义一个新的Integer数据来接收，
        // 所以在范围内数据都是相等的，都是一个地址（赋值两次而已），超过缓存后会返回new Interger
        //完全是两个不同的对象，所以两个对象的地址就不相等了
        Integer a2=100;
        System.out.println(a1==a2);
        Integer a3=200;
        Integer a4=200;
        System.out.println(a3==a4);
    }
    @Test
    public void T1(){
            char[] data = {'h','e','l','l','o','j','a','v','a'};
            String s1 = String.copyValueOf(data);
            String s2 = String.copyValueOf(data,0,5);
            int num = 123456;
            String s3 = String.valueOf(num);
            System.out.println(s1);
            System.out.println(s2);
            System.out.println(s3);
        }
}

