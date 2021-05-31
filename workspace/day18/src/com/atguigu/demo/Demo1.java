package com.atguigu.demo;

import java.util.LinkedList;
import java.util.List;

public class Demo1 {
    public static void main(String[] args) {
        List list=new LinkedList();
        list.add("23");
        list.add("java");
        list.add(true);
        list.add(10);
        list.add("java");
        list.add('T');
        System.out.println(list);
       /* list.add(2,100);
        System.out.println(list);

        list.addAll(list);
        System.out.println(list);*/

        /*list.remove(new Character('T'));
        System.out.println(list);
        Iterator iterator = list.iterator();
        while (iterator.hasNext()){
            Object next = iterator.next();
            if("java".equals(next))
                iterator.remove();
        }
        System.out.println(list);
        list.set(2,false);
        System.out.println(list);
        System.out.println(list.get(5));
*/
    }
}

