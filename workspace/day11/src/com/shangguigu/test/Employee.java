package com.shangguigu.test;

public abstract class Employee {
    private String name;

    public Employee() {
    }

    public Employee(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract double earning();
    public String getInfo(){
        return "姓名:"+name+"实发工资："+earning();
    }
}
