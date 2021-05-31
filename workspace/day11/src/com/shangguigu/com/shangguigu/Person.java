package com.shangguigu.com.shangguigu;

public abstract class Person {
    public abstract void toilet();
}
class Woman extends Person{
    public void toilet(){
        System.out.println("优雅的走路");
    }
}
class Man extends Person{
    public void toilet(){
        System.out.println("着急的走路");
    }
}
class Test{

    public static void main(String[] args) {
        //Person a=new Woman   Person b=new Man;
        //省略了上述步骤
        goToToilet(new Woman());
        goToToilet(new Man());
    }
    public static void goToToilet(Person p) {
        p.toilet();
    }
}