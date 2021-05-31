package com.atguigu.lianxi;

import java.util.*;


public class Lian3 {
        public static void main(String[] args) {
            Map c =new HashMap<>();
            Student student=new Student();
            List d =new ArrayList<>();
            d.add("xiao");
            d.add("xiao1");
            d.add("xiao2");
            d.add("xiao3");


            List e = new ArrayList<>();
            e.add("hong");
            e.add("hong1");
            e.add("hong2");
            e.add("hong3");


            c.put("xiao",d);
            c.put("hong",e);
            //System.out.println(c);
            Scanner input = new Scanner(System.in);

            Set<Map.Entry<String, List>> entries = c.entrySet();
            List list=new ArrayList();
            for (Map.Entry<String, List> entry : entries) {
                String key = entry.getKey();
                List value = entry.getValue();
                System.out.println(key+"="+value);
                list.add(value);
            }
            System.out.println(list);
            System.out.print("请输入学员的姓名：");
            String next = input.next();
            if(list.contains(next)){
                    System.out.println("找到了");
                }else{
                    System.out.println("没找到");
                }







        }

    }
    class Student{
        private String name;
        private int age;

        public Student(String name) {
            this.name = name;

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
                    '}';
        }
    }

