package com.atguigu.demo.lianxi;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *         用一个String[]数组存点数
 * ​	2、用一个String[]数组存花色
 * ​	3、用一个String[]数组存大王、小王
 * ​	4、用上面的数组，生成一副扑克牌
 * ​	5、遍历显示全副扑克牌
 * ​	6、模拟给4个人随机发牌，每个人11张牌
 * ​	7、显示每个人的牌和剩余的牌
 */
public class LianXi2 {
    public static void main(String[] args) {
        String[] dian=new String[]{"1","2","3","4","5","6","7","8","9","10","J","Q","K","A"};
        String[] hua=new String[]{"黑桃","方片","红桃","梅花"};
        String[] wang=new String[]{"大王","小王"};
        List list=new ArrayList();
        for (int i = 0; i <dian.length ; i++) {
            for (int j = 0; j <hua.length ; j++) {
                list.add(hua[j]+dian[i]);
            }
        }
        for (int j = 0; j <wang.length ; j++) {
            list.add(wang[j]);
        }
        System.out.println(list);
        Random r=new Random();
        List list1=new ArrayList();
        for (int i = 0; i <11 ; i++) {//随机生成一个54以内的数，list减去下标为这个数的元素，新的集合将这个元素加上。
            list1.add(list.remove(r.nextInt(list.size())));
        }
        System.out.println(list1);
        List list2=new ArrayList();
        for (int i = 0; i <11 ; i++) {//随机生成一个54以内的数，list减去下标为这个数的元素，新的集合将这个元素加上。
            list2.add(list.remove(r.nextInt(list.size())));
        }
        System.out.println(list2);
        List list3=new ArrayList();
        for (int i = 0; i <11 ; i++) {//随机生成一个54以内的数，list减去下标为这个数的元素，新的集合将这个元素加上。
            list3.add(list.remove(r.nextInt(list.size())));
        }
        System.out.println(list3);
        System.out.println(list);
    }
}
