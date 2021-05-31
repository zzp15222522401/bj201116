package com.atguigu.demo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

public class Demo3 {
    public static void main(String[] args) {
        Collection1 c=new Collection1();
        Collection collection=new ArrayList();

        collection.add("字符串");
        collection.add(123);
        collection.add(true);
        collection.add('a');
        /*System.out.println(collection);
        collection.add(c.method());
        System.out.println(collection);*/
        collection.addAll(collection);
        System.out.println(collection);
        collection.removeAll(Collections.singleton("字符串"));
        System.out.println(collection);
      /*  Collection collection1=new ArrayList();
        collection1.add(true);
        boolean contains = collection.contains(true);
       if(contains)
           collection.removeAll(collection1);
        System.out.println(collection);*/

        /*collection.toArray();
        System.out.println(collection);

        System.out.println(collection.size());*/
        Iterator a=collection.iterator();
        while(a.hasNext()){
        Object next = a.next();
        if(next.equals(true))
            a.remove();
        }
        System.out.println(collection);
    }
}
class Collection1 {
    public Collection method(){
        Collection collection=new ArrayList();
        collection.add("字符串");
        collection.add(123);
        collection.add(true);
        collection.add('a');
        return collection;
    }

}