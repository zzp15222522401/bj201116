package com.atguigu.demo;

/**
 * 1、声明一个坐标类Coordinate<T>，它有两个属性：x,y，都为T类型
 * 2、在测试类中，创建两个不同的坐标类对象，
 * 分别指定T类型为String和Double，并为x,y赋值，打印对象
 */
public class Demo2 {
    public static void main(String[] args) {
    Coordinate<String> coordinate=new Coordinate<String>("1","2");
    Coordinate<Double> coordinate1=new Coordinate<Double>(1.5,2.5);
        System.out.println(coordinate);
        System.out.println(coordinate1);
    }
}
class Coordinate<T>{
    T x;
    T y;

    public Coordinate(T x, T y) {
        this.x = x;
        this.y = y;
    }

    public Coordinate() {
    }

    public T getX() {
        return x;
    }

    public void setX(T x) {
        this.x = x;
    }

    public T getY() {
        return y;
    }

    public void setY(T y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}