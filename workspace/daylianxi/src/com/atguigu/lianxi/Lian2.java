package com.atguigu.lianxi;

public class Lian2 {
    public static void main(String[] args) {
        Animal animal=new Cat();
        //animal.run();

        if(animal instanceof Dog){
            Dog dog=(Dog)animal;//向下转型(强转)
            dog.run();
        }
        //Dog dog=new Dog();
        /*Animal animal1=new Dog();
        System.out.println(animal1 instanceof Cat);*/

        new Thread().start();
    }
}
 class Animal {

    public void eat(){
        System.out.println("吃饭");
    }

    public void working(){
        System.out.println("上午的工作");
        eat();
        System.out.println("下午的工作");
    }
}
 class Cat extends Animal {
    @Override
    public void eat() {
        System.out.println("吃老鼠");
    }
}
class Dog extends Animal{
    @Override
    public void eat() {
        System.out.println("吃骨头");
    }

    public void run(){
        System.out.println("run...");
    }
}
