package com.shangguigu.demo.demo1;

public interface LiveAble {
    public void eat();
    public void breathe();
    default  void sleep(){
        System.out.println("静止不动");
    }
    public static void drink(){
        System.out.println("喝水");
    }
}
