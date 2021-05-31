package com.shangguigu.demo;

public class Demo {
    public static void main(String[] args) {
        Father s= new Sister();
        Father son=new Son();
        s.work();
        son.work();
        System.out.println("=========================");
       /* Father a=son;
        Father b=s;
        //多态的形式
        a.run();
        b.run();*/
        /*Son x=(Son)son;
        Sister y=(Sister) s;
        //由下向上转型，需要强制转换
        x.play();
        y.eat();*/


        if( s instanceof Sister){
            Sister a=(Sister) s;
            a.eat();
        }else if (s instanceof Son){
            Son b=(Son) son;
            b.play();
        }
        System.out.println("********************");
        if(son instanceof Son){
            Son a=(Son)son;
            a.play();
        }else if(son instanceof Sister){
            Sister b=(Sister)son;
            b.eat();
        }

    }
}
