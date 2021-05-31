package com.shangguigu.test;

public class Demo1 {

    public static void main(String[] args) {
        Employee[] employees=new Employee[3];
        employees[0]=new SalaryEmployee("张三",10000,30,1);
        employees[1]=new HourEmployee("李四",240,25);
        employees[2]=new Manager("经理",15000,25,6,0.2);
        double sum=0;
        for (int i = 0; i < employees.length; i++) {
            System.out.println(employees[i].getInfo());
            sum+=employees[i].earning();

        }
        System.out.println("工资总额："+sum);
    }
}
