package com.atguigu.demo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Demo {
    public static void main(String[] args) {
        Collection collection=new ArrayList();
        collection.add("bigdata");
        collection.add("mysql");
        collection.add("java");
        collection.add("bigdata");

        //第一种方式删除（添加新的小集合）
        Collection collection1=new ArrayList();
        collection1.add("bigdata");
        collection.removeAll(collection1);
        System.out.println(collection);

        //第二种方式删除（用迭代器）
        Iterator iterator=collection.iterator();
        while (iterator.hasNext()){//判断下一个位置是否有值
            Object string=iterator.next();//取出下一个位置的值
            if("bigdata".equals(string))
                iterator.remove();
        }
        for (Object o : collection) {
            System.out.println(o);
        }
    }
}
