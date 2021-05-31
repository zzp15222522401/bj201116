package com.atguigu.demo.lianxi;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

/**
 * 键盘录入学生信息，保存到集合中。
 * * 循环录入的方式，1：表示继续录入，0：表示结束录入。
 * * 定义学生类，属性为姓名，年龄，使用学生对象保存录入数据。
 * * 使用ArrayList集合，保存学生对象，录入结束后，用foreach遍历集合。
 */
public class LianXi {
    public static void main(String[] args) {
//        Student[] students=new Student[5];
        Scanner input=new Scanner(System.in);
        Collection<Student> collection=new ArrayList<>();

        /*collection.add(new Student("张三",18));
        collection.add(new Student("李四",20));
        collection.add(new Student("王五",20));
        collection.add(new Student("小刘",19));
        for (Student student : collection) {
            System.out.println(student);
        }*/
        boolean flag=true;
        while(flag){
                System.out.println("请选择\t1.录入  0.退出");
                int a=input.nextInt();
                    if(a==1){
                         System.out.println("姓名：");
                         String name=input.next();
                         System.out.println("年龄：");
                         int age=input.nextInt();
                         Student student=new Student(name,age);
                         collection.add(student);
                    }
                    else if(a==0){
                        for (Student student1 : collection) {
                            System.out.println(student1);
                        }
                             return;
                    }
            }
    }
}
class Student{
    private String name;
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Student() {
    }

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

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}