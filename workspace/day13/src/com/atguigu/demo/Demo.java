package com.atguigu.demo;

import org.junit.Test;

public class Demo {
    /*public static void main(String[] args) {

    }*/
    @Test
    public void Test1(){

        try {
            Test2();
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        System.out.println("Test1的输出");
    }
    @Test
    public void Test2()throws IndexOutOfBoundsException,NullPointerException{

            Test3();
        System.out.println("Test2的输出");
    }
    @Test
    public void Test3() throws NullPointerException,IndexOutOfBoundsException{
        String [] strings=new String[5];
        String str=null;
            strings[5]="";
            //int length1 = str.length();

        System.out.println("异常后的输出");
    }

}

