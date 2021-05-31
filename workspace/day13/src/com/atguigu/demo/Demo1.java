package com.atguigu.demo;

import org.junit.Test;

public class Demo1 {
        public static void main(String[] args) {

            int result = test("12");
            System.out.println(result);
        }

        public static int test(String str){
            try{
                Integer.parseInt(str);
                return 1;
            }catch(NumberFormatException e){
                return -1;
            }finally{
                System.out.println("test结束");
            }
        }
    @Test
    public  void Test1() {
        int test = test(3,5);
        System.out.println(test);//输出8
    }
    public  int test(int x, int y){
        int result = x;
        try{
            if(x<0 || y<0){
                return 0;
            }
            result = x + y;
            return result;
        }finally{
            result = x - y;
            //return result;        输出-2
        }
    }

}

