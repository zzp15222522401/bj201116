package com.atguigu.test;

public class Demo3 {
    public static void main(String[] args) {
        Father a=new Son();
        System.out.println(a.age);
        System.out.println("**************");
        a.method();
    }

}
class Father{
    int age=40;
    void method(){
        System.out.println("这是Father的");
    }
}
class Son extends Father{
    int age=20;
    void method(){
        System.out.println("这是Son的");
    }
}