package com.stguigu.demo; /**
 * - public String() ` ：初始化新创建的 String对象，以使其表示空字符序列。
 * - String(String original)`： 初始化一个新创建的 `String` 对象，使其表示一个与参数相同的字符序列；
 *   换句话说，新创建的字符串是该参数字符串的副本。
 * - public String(char[] value) ` ：通过当前参数中的字符数组来构造新的String。
 * - public String(char[] value,int offset, int count) ` ：通过字符数组的一部分来构造新的String。
 */
import org.junit.Test;

public class Demo1 {
    public static void main(String[] args) {
        String s="123";
        String S="456";
        String s1=new String("123456");
        System.out.println(s+S==s1);
        String s2=new String("123");
        String s3=new String("456");
        System.out.println(s1==s2+s3);
        char[] c={'1','2','3'};
        String s4=new String(c);
        System.out.println(s1==s3);


    }
    @Test
    public void Test1(){
        String s1 = "hello";
        String s2 = "world";
        String s3 = "helloworld";

        String s4 = s1 + "world";//s4字符串内容也helloworld，s1是变量，"world"常量，变量 + 常量的结果在堆中
        String s5 = s1 + s2;//s5字符串内容也helloworld，s1和s2都是变量，变量 + 变量的结果在堆中
        String s6 = "hello" + "world";//常量+ 常量 结果在常量池中，因为编译期间就可以确定结果

        System.out.println(s3 == s4);//false
        System.out.println(s3 == s5);//false
        System.out.println(s3 == s6);//true
    }

    }
    /*public static void main(String[] args) {
        String s="";
        String s1=new String("6846");
        char [] chars={'1','2','3','4','5'};
        String s2=new String(chars);
        String s3=new String(chars,3,2);
        System.out.println(s2);
    }*/


