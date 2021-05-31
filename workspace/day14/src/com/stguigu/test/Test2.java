package com.stguigu.test;

import org.junit.Test;

public class Test2 {
    public static void main(String[] args) {

    }
    @Test
    public void Ktest(){
            String str = new String("world");
            char[] ch = new char[]{'h','e','l','l','o'};
            change(str,ch);//把ch输入到change方法中，arr=hello,在方法中又对arr进行了赋值，把hello替换成了abcde，
                            // 此时的char数组ch就是abcde,最后输出还是abcde,不明白可以画内存图看看，和String的
                            //常量池以及数组的存储有关。
            System.out.println(str);
            System.out.println(String.valueOf(ch));
        }
        public static void change(String str, char[] arr){
            str = "change";
            arr[0] = 'a';
            arr[1] = 'b';
            arr[2] = 'c';
            arr[3] = 'd';
            arr[4] = 'e';
        }
    }


