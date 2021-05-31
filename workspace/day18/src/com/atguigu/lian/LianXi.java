package com.atguigu.lian;

import java.util.*;

/**
 * 模拟乐透号码。
 * * 随机生成10个号码放到集合中，范围1-50，作为乐透号码。不能重复。
 * * 键盘录入10个整数放到集合中，范围1-50，不能重复。
 * * 录入的整数与乐透号码对比，统计猜中了几个
 */
public class LianXi {
    public static void main(String[] args) {
        Set set=new HashSet();
        LianXi.method1(set);

        ArrayList set1=new ArrayList();
        LianXi.method2(set1);

        System.out.println("您输入的号码为："+set1);
        System.out.println("乐透号码为："+set);//随机生成的10个整数
       LianXi.method3(set,set1);



    }
    public static void method1(Set set){
        Random r=new Random();
        while(true) {//如果生成的数据含有重复的数，是不可能录入到集合中去的。
            set.add(r.nextInt(50)+1);
            if(set.size()>=10)
                break;
        }
    }
    public static void method2(ArrayList arrayList){
        Scanner s=new Scanner(System.in);
        System.out.println("输入10个整数（1-50）,不能重复：");
        for (int i = 0; i <10 ;i++ ) {
            System.out.println("第"+(i+1)+"个数");
            int int1=s.nextInt();
            arrayList.add(int1);
        }
    }
    public static void method3(Set set,ArrayList set1){
        int count=0;
        for (int i = 0; i <set1.size() ; i++) {
            if(set.contains(set1.get(i))){
                count++;
            }
        }
        System.out.println("你猜中了"+count+"个");
    }

}
