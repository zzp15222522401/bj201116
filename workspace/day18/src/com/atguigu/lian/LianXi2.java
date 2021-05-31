package com.atguigu.lian;

import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

/**
 *键盘录入一个字符串，去掉其中重复字符，打印出不同的那些字符，必须保证顺序。
 * 例如输入：aaaabbbcccddd，打印结果为：abcd
 */
public class LianXi2 {
    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        Set set=new LinkedHashSet();
        System.out.println("输入一个字符串(需要有重复字符)：");
        String str=input.next();
        char[] chars = str.toCharArray();
        for (int i = 0; i <chars.length ; i++) {
            set.add(chars[i]);
        }
        for (Object o : set) {
            System.out.print(o+"");
        }
    }
}
