package com.atguigu.lian;
/**
 * （1）用Comparable接口对下列四位同学的成绩做降序排序，如果成绩一样，
 *      那在成绩排序的基础上按照年龄由小到大排序。
 * （2）用Comparator实现按照姓名排序
 */

import java.util.Comparator;
import java.util.TreeSet;

public class LianXi3 {//用Comparable实现
    public static void main(String[] args) {
        TreeSet list =new TreeSet();
        Student student=new Student();
        list.add(new Student("liusan",20,90.0));
        list.add(new Student("lisi",22,90.0));
        list.add(new Student("wangwu",20,99.0));
        list.add(new Student("sunliu",22,100.0));
        for (Object o : list) {
            System.out.println(o);
        }

    }
}
class Student implements Comparable<Student>{
    private  String name;
    private int age;
    private  double score;

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

    public Student(String name, int age, double score) {
        this.name = name;
        this.age = age;
        this.score = score;
    }

    public Student() {
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", score=" + score +
                '}';
    }


    @Override
    public int compareTo(Student o) {
           if (this.score-o.score>0){
               return -1;
           }
           else if(this.score-o.score<0){
                return 1;
           }
           else
               return this.age-o.age;
    }
}
class Comparator1{
    public static void main(String[] args) {
        TreeSet list =new TreeSet(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                 return o1.getName().compareTo(o2.getName());
            }
        });
        list.add(new Student("liusan",20,90.0));
        list.add(new Student("lisi",22,90.0));
        list.add(new Student("wangwu",20,99.0));
        list.add(new Student("sunliu",22,100.0));
        for (Object o : list) {
            System.out.println(o);
        }
    }
}