package com.atguigu.lian;

import java.util.Comparator;
import java.util.TreeSet;

public class LianXi4 {
    public static void main(String[] args) {
        TreeSet list =new TreeSet(new Comparator<Student1>() {
            @Override//按照年龄由大到小，年龄相同按成绩有大到小。
            public int compare(Student1 o1, Student1 o2) {
                if(o1.getAge()-o2.getAge()>0)
                    return -1;
                if(o1.getAge()-o2.getAge()<0)
                    return 1;
                else        //可能会有精度缺失，减少使用
                    return (int) (o2.getScore()-o1.getScore());
            }
        });
        Student1 student=new Student1();
        list.add(new Student1("liusan",20,90.0));
        list.add(new Student1("lisi",22,90.0));
        list.add(new Student1("wangwu",20,99.0));
        list.add(new Student1("sunliu",22,100.0));
        list.add(new Student1("zhangsan",23,100.0));
        for (Object o : list) {
            System.out.println(o);
        }

    }
}
class Student1{
    private String name;
    private int age;
    private double score;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Student1(String name, int age, double score) {
        this.name = name;
        this.age = age;
        this.score = score;
    }

    public Student1() {
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", score=" + score +
                '}';
    }
}