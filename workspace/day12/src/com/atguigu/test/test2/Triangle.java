package com.atguigu.test.test2;

public class Triangle {
    private int a;
    private int b;
    private int c;

    public Triangle() {
    }

    public Triangle(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }
    public double getArea(){
        double p=(a+b+c)/2;
        return Math.sqrt(p*(p-a)*(p-b)*(p-c));
    }
    public double getP(){
        return a+b+c;
    }

    @Override
    public String toString() {
        return "周长："+getP()+"面积"+getArea();
    }
}
