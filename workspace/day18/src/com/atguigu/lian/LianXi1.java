package com.atguigu.lian;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 *      1、随机生成10个[1,100]之间的整数，放到List集合中，遍历显示
 * ​	2、找出前3名最大值，删除它们，注意可能重复
 * ​	3、显示删除后的结果
 */
public class LianXi1 {
    public static void main(String[] args) {
        Random r=new Random();
        List list=new ArrayList();
        for (int i = 0; i <10 ; i++) {
            list.add(r.nextInt(100)+1);
        }
        System.out.println("随机生成的10个数："+list);
        Object[] objects = list.toArray();
        List list1=new ArrayList();
        Arrays.sort(objects);
        for (int i = 9; i >6 ; i--) {
            list1.add(objects[i]);
        }
        System.out.println("最大的三个数为："+list1);
        list.removeAll(list1);
        System.out.println("删除后："+list);

    }
}
