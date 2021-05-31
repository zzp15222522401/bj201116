package com.shangguigu.test;

public class Manager extends SalaryEmployee {
    private double salarys;

    public Manager() {
    }

    public Manager(String name, double salary, double workday, double leaveday, double salarys) {
        super(name, salary, workday, leaveday);
        this.salarys = salarys;
    }

    public double getSalarys() {
        return salarys;
    }

    public void setSalarys(double salarys) {
        this.salarys = salarys;
    }
    public double earning(){
        return super.earning()*(1+salarys);
    }
    /*public String getInfo(){
        return super.getInfo()+"奖金比例"+salarys;
    }*/
}
