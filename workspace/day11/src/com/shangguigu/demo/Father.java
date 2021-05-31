package com.shangguigu.demo;

public abstract class Father {
    public abstract void run();
    public void work(){
        System.out.println("上班");
        run();
        System.out.println("下班");
    }
}
