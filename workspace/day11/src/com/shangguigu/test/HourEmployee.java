package com.shangguigu.test;

public class HourEmployee extends Employee {
    private double workhour;
    private double hoursalary;

    public HourEmployee() {
    }

    public HourEmployee(String name, double workhour, double hoursalary) {
        super(name);
        this.workhour = workhour;
        this.hoursalary = hoursalary;
    }

    public double getWorkhour() {
        return workhour;
    }

    public void setWorkhour(double workhour) {
        this.workhour = workhour;
    }

    public double getHoursalary() {
        return hoursalary;
    }

    public void setHoursalary(double hoursalary) {
        this.hoursalary = hoursalary;
    }
    public double earning(){
        double H=workhour*hoursalary;
        return H;
    }
    /*public String getInfo(){
        return super.getInfo()+"工作小时数"+workhour+"小时薪资"+hoursalary;
    }*/
}
