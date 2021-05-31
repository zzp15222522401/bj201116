package com.atguigu.demo;
/**
 * 对象流(对象必须实现Serializable接口，如果有对象关联，那么关联对象也要实现这个接口)
 *      对磁盘进行写入时，写入的资源我们时看不懂的，但是电脑可以读取到，
 *      对象在写入时最好加上版本号
 *              private static final long serialVersionUID = 7001603351278911867L;
 *              防止以后Student内容改动后读取不到，改动后读取的Student会把改动部分读取成默认值。
 */

import java.io.*;

public class Demo3 {
    public static void main(String[] args) {
        ObjectInputStream input=null;
        ObjectOutputStream out =null;
        try {
            //out=new ObjectOutputStream(new FileOutputStream("e:/aaa/bbb/duixiang1.txt"));
            input=new ObjectInputStream(new FileInputStream("e:/aaa/bbb/duixiang.txt"));
            System.out.println(input.readBoolean());
            System.out.println(input.readChar());
            System.out.println(input.readDouble());
            Object o = input.readObject();
            System.out.println(o);


        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main1(String[] args) {
        ObjectOutputStream out=null;//我们这里对电脑磁盘进行的写入是不可读的，但是电脑可以读取。
        Student student= new Student("张三",20,'男',2000.0,new Car("宝马"));
        try {
             out= new ObjectOutputStream(
                    new FileOutputStream("e:/aaa/bbb/duixiang.txt"));
             out.writeBoolean(true);
             out.writeChar('尚');
             out.writeDouble(0.5);
             out.writeObject(student);

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
class Student implements Serializable{
    private static final long serialVersionUID = 7001603351278911867L;
    private String name;
    private int age;
    private char gender;
    private double salary;
    private Car car;

    public Student() {
    }

    public Student(String name, int age, char gender, double salary, Car car) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.salary = salary;
        this.car = car;
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

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", salary=" + salary +
                ", car=" + car +
                '}';
    }
}
class Car implements Serializable {
    private static final long serialVersionUID = -6069466596511134995L;
    private String name;

    public Car(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                '}';
    }
}