package com.shangguigu.test;

import com.shangguigu.demo.demo1.Animal;
import com.shangguigu.demo.demo1.LiveAble;
import com.shangguigu.demo.demo1.Plant;

public class Test3 {
    public static void main(String[] args) {
        Animal animal=new Animal();
        Plant plant=new Plant();
        animal.eat();
        animal.sleep();
        animal.breathe();
        System.out.println("*****************");
        plant.eat();
        plant.sleep();
        plant.breathe();
        System.out.println("*******************");
        LiveAble.drink();/*静态方法通过类名.调用，这里就用接口名.调用*/
    }
}
