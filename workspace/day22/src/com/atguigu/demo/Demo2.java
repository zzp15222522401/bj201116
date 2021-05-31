package com.atguigu.demo;

import org.junit.Test;

import java.lang.reflect.*;

public class Demo2 {
    @Test
    public void main3(){//反射访问属性
        Class<User> userClass = User.class;
        try {
            User user = userClass.newInstance();
            Field a = userClass.getDeclaredField("age");//获得属性的对象
            a.setAccessible(true);//私有的属性值通过权限压制也可以进行访问了
            a.set(user,20);
            Object o = a.get(user);
            System.out.println(o);


        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void main2(){//反射创建对象
        Class<User> userClass = User.class;
        try {
            User user2 = userClass.newInstance();//创建对象

            Constructor constructor2 = userClass.getConstructor();//根据构造器创建对象(默认调用无参)
            Object o1 = constructor2.newInstance();

            Constructor user = userClass.getDeclaredConstructor(int.class, String.class);//根据构造器名和参数获取构造器对象
            //System.out.println(user);
            Object o = user.newInstance(100, "张三");//根据构造器对象调用对应的构造器（参数要一一对应）
            System.out.println(o);

            Constructor<User> constructor = userClass.getDeclaredConstructor(int.class,String.class,double.class);//获取构造器
            constructor.setAccessible(true);//暴力访问-权限压制(私有的也可以访问了)
            User user1 = constructor.newInstance(20, "李四", 2000.0);
            System.out.println(user1);

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void main1(){
        Class<User> userClass = User.class;

        try {

            //默认调用无参的

            /*Constructor<User> constructor = userClass.getConstructor(int.class, String.class);//根据参数列表获取构造器
            System.out.println(constructor);*/
            Constructor<?>[] constructors = userClass.getConstructors();//获取所有的构造器
            for (Constructor<?> constructor1 : constructors) {
                //System.out.println(constructor);
                System.out.println(constructor1.getName());//获取构造器名字（和类名相同）
                System.out.println(Modifier.toString(constructor1.getModifiers()));//获取构造器的修饰符
                Class<?>[] parameterTypes = constructor1.getParameterTypes();//获取构造器的形参列表
                for (Class<?> parameterType : parameterTypes) {
                    System.out.println(parameterType);
                }

            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Class<User> userClass = User.class;
        /*Field[] fields = userClass.getDeclaredFields();//获取本类中所有属性
        Field[] fields1 = userClass.getFields();//获取所有的共有的属性（包括父类继承下来的属性）
        for (Field field : fields) {
            System.out.println(field.getType());//获取属性类型信息
            System.out.println(field.getName());//获取属性名字
            System.out.println(Modifier.toString(field.getModifiers()));//获取修饰符*/


        try {
            Constructor user = userClass.getDeclaredConstructor(int.class, String.class);//根据构造器名和参数获取构造器对象
            //System.out.println(user);
            Object o = user.newInstance(100, "张三");//根据构造器对象调用对应的构造器（参数要一一对应）
            System.out.println(o);
            Method[] methods = userClass.getDeclaredMethods();//获取本类中所有的方法
           /* for (Method method : methods) {
                *//*System.out.println(method.getName());//方法名字
                System.out.println(Modifier.toString(method.getModifiers()));//方法修饰符
                System.out.println(method.getReturnType());*//*//方法返回值类型

                Class<?>[] parameterTypes = method.getParameterTypes();//方法形参列表
                for (Class<?> parameterType : parameterTypes) {
                    System.out.println(parameterType);
                }
            }*/



        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    //获取Class对象
    public static void main1(String[] args) {
        User user=new User();
        Class<User> userClass = User.class;
        System.out.println(userClass);//类名.class

        Class<? extends User> aClass = user.getClass();
        System.out.println(aClass);//类的对象.getClass


        String name="com.atguigu.demo.User";
        ClassLoader classLoader = User.class.getClassLoader();


        try {
            Class<?> aClass2 = classLoader.loadClass(name);
            Class<?> aClass1 = Class.forName(name);//Class.forname(类的全名)
            System.out.println(aClass2);
            System.out.println(aClass1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}
class User implements Runnable{
    private  int age;
    public String name;
    double salary;
    protected char gender;

    @Override
    public String toString() {
        return "User{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", gender=" + gender +
                '}';
    }

    public User() {
    }

    public User(int age, String name) {
        this.age = age;
        this.name = name;
    }

    private User(int age, String name, double salary) {
        this.age = age;
        this.name = name;
        this.salary = salary;
    }

    public User(int age, String name, double salary, char gender) {
        this.age = age;
        this.name = name;
        this.salary = salary;
        this.gender = gender;
    }

    public void mehthod(){
    System.out.println("User中的method方法");
    }

    @Override
    public void run() { }
    public String run1(){
        return null;
    }
    public void method1(int i,String s){
        System.out.println(i+s);
    }
}