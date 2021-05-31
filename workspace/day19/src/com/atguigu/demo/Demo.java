package com.atguigu.demo;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class Demo {
    public static void main(String[] args) {
        HashMap map=new HashMap();
        map.put("jdbc",1);//添加数据
        map.put(6546,'s');
        map.put(true,1);
        map.put('T',5);
        map.put(0.5,3);
        System.out.println(map);
        map.replace('T','t');//替换
        System.out.println(map);
        map.remove(6546);
        System.out.println(map);//删除

        //map遍历没有foreach方法，也没有实现Iterator接口方法
        Set set = map.keySet();//遍历map中的key值
        for (Object o : set) {
            System.out.print(o+" ");
        }
        System.out.println();
        Collection values = map.values();//遍历map中的value值
        for (Object value : values) {
            System.out.print(value+" ");
        }
    }
}
//输入一个字符串String str="www.atguigu.com",取出每个字符以及相应个数；
class Lian{
    public static void main(String[] args) {
        String str="www.atguigu.com";
        Map<Character,Integer> map=new HashMap<>();
        char[] chars = str.toCharArray();


        for (int i = 0; i <chars.length ; i++) {
            Integer o = map.get(chars[i]);//通过get方法得到map的key值相对应的value值。
            if(o==null){
                map.put(chars[i],1);
            }
            else {
                map.put(chars[i],o+1);
            }
        }
        System.out.println("*********************************");
        System.out.println(map);
    }
}