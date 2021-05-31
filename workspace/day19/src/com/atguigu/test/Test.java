package com.atguigu.test;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public class Test {
    public static void main(String[] args) {
        HashMap map=new HashMap();
        map.put("jdbc",1);//添加数据
        map.put(6546,'s');
        map.put(true,1);
        map.put('T',5);
        map.put(0.5,3);
        System.out.println(map);
        Collection values = map.values();
        for (Object o : values) {
            System.out.print(o);
        }
        System.out.println();
        Set set = map.keySet();
        for (Object o : set) {
            System.out.print(o+"\t");
        }
    }
}
