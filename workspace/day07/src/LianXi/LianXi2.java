package LianXi;

import Demo.Demo;

public class LianXi2 {
    public static void main(String[] args) {
        Demo1 demo2=new Demo1();
        int x=10;
        int y=20;
        String arrs[]=new String[]{"java","mysql","hadoop"};
        demo2.method(x,y,arrs);
        System.out.println("------------------");
        System.out.println(x);
        System.out.println(y);
        for (int i = 0; i <arrs.length; i++) {
            System.out.println(arrs[i]);
        }
    }
}
class Demo1{
   void method(int a,int b,String[]arrs){
       a=15;
       arrs[0]="bigdata";
       System.out.println(a);
       System.out.println(b);
       for (int i = 0; i <arrs.length; i++) {
           System.out.println(arrs[i]);
       }
    }
}