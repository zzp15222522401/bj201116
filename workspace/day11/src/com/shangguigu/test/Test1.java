package com.shangguigu.test;

public class Test1 {
    public static void main(String[] args) {

        Student a=new Student(1,"张三",18);
        Student b=new Student(1,"张三",18);

        System.out.println(a==b);
        System.out.println(a.equals(b));
        //重写了equals方法
    }


    }


class Student{
    private int id;
    private String name;
    private int age;

    public Student() {
    }

    public Student(int id, String name, int age) {
        this.id = id;
        this.name = name;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object obj) {/*Object obj=new Student();多态*/
        if(obj==null)
            return false;
        if(this==obj)
            return true;
        if(obj instanceof Student){
            Student student=(Student)obj;
            if(student.getAge()==this.getAge()&&student.getId()==this.getId()&&(this.getName().equals(student.getName())))
            return true;
        }

        return false;
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println(this.name+"即将被删除");
    }
}
