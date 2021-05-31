package com.atguigu.demo;

import org.junit.Test;

public class Demo1 {
    public static void main(String[] args) {
        User<String> a=new User<String>("张三",001,"飞");
        System.out.println(a);
        User <Boolean> b =new User<Boolean>("傻子",007,false);
        System.out.println(b);
    }
    @Test
    public void method(){
        Comparable<User>comparable=new Comparable<User>() {
            @Override
            public int compareTo(User o) {
                int id = o.getID();
                return id;
            }
        };
}
}
class User<T>{
    private String name;
    private int ID;
    private T way;

    public User() {
    }

    public User(String name, int ID, T way) {
        this.name = name;
        this.ID = ID;
        this.way = way;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public T getWay() {
        return way;
    }

    public void setWay(T way) {
        this.way = way;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", ID=" + ID +
                ", way=" + way +
                '}';
    }
}

