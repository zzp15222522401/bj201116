package com.atguigu.test.test2;

public class Di {
    private static String yuxi="皇帝玉玺";
    public static class Dong{
        static void order(){
            System.out.println("董卓拿了玉玺");
        }
    }

    public static void main(String[] args) {
        Di.Dong.order();
    }
}
 class TestInner{
    public static void main(String[] args){
        Outer.Inner in = new Sub();
        in.method();//输出 hello inner
    }
}

class Outer {
    abstract class Inner{
        abstract void method();
    }
}
class Sub extends Outer.Inner{
    static Outer out = new Outer();
    Sub(){
        out.super();
    }

    @Override
    void method() {
        System.out.println("hello inner");
    }

}
