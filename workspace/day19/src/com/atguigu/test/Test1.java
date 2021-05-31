package com.atguigu.test;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

//TreeMap集合必须实现排序方法(Comparable或者是Comparator)
public class Test1 {

    public static void main(String[] args) {
        Map map=new TreeMap(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        for (int i = 0; i <5 ; i++) {
            map.put(new User(i,i,"张"+i),"TreeMap"+i);
        }
        System.out.println(map);
        Map map1=new HashMap();
        for (int i = 0; i <5 ; i++) {
            map1.put(new User(i,i,"张"+i),"TreeMap"+i);
        }
        System.out.println(map1);
    }
}
class User{
    private int age;
    private int id;
    private String name;

    public User(int age, int id, String name) {
        this.age = age;
        this.id = id;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "age=" + age +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}