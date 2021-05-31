package com.atguigu.test;

public class Test2 {
    private static String str;
    private int a=5;

    public static void main(String[] args) {
        int[] arr = {1, 5, 9, 10, 68, 5, 97, 100, 52, 34};
        System.out.println(str);

    }
    public static void method(){
        System.out.println("这是静态的method方法");
    }
    public void method2(){
        System.out.println("这是method方法");
    }
    public String care(){
        return "111";
    }
    public int care(int a){
        return 1;
    }

}
