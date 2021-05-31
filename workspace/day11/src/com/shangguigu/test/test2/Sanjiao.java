package com.shangguigu.test.test2;

public class Sanjiao {
    private double a;
    private double b;
    private double c;

    public Sanjiao() {
    }

    public Sanjiao(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public double getC() {
        return c;
    }

    public void setC(double c) {
        this.c = c;
    }

    @Override
    public String toString() {
        return "三角形的三遍分别是：a="+a+"b="+b+"c="+c;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(a);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(b);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(c);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj==null)
        return false;
        if(this==obj)
            return true;
        Sanjiao s=(Sanjiao)obj;
        if(this.getArea()==s.getArea()&&this.getPiremeter()==s.getPiremeter()) {
            return true;
        }
        return false;
    }/*重写toString算法，如果三角形的面积和周长都相等，这两个三角形就算相等*/
    public double getArea(){
        double p=(a+b+c)/2;
        return Math.sqrt(p*(p-a)*(p-b)*(p-c));
    }
    public double getPiremeter(){
        return a+b+c;
    }
}
class Test2{
    public static void main(String[] args) {
        Sanjiao a1=new Sanjiao(4,5,6);
        Sanjiao a2=new Sanjiao(5,5,5);
        System.out.println(a1.getPiremeter());
        System.out.println(a1.getArea());
        System.out.println(a1.hashCode());
        System.out.println(a1.toString());
        System.out.println("这两个三角形是否相同？"+a1.equals(a2));
        System.out.println("******************");
        System.out.println(a2.getPiremeter());
        System.out.println(a2.getArea());
        System.out.println(a2.hashCode());
        System.out.println(a2.toString());



    }
}
