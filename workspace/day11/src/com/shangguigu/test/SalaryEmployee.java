package com.shangguigu.test;

public class SalaryEmployee extends Employee {
    private double salary;
    private double workday;
    private double leaveday;

    public SalaryEmployee() {
    }

    public SalaryEmployee(String name, double salary, double workday, double leaveday) {
        super(name);
        this.salary = salary;
        this.workday = workday;
        this.leaveday = leaveday;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getWorkday() {
        return workday;
    }

    public void setWorkday(double workday) {
        this.workday = workday;
    }

    public double getLeaveday() {
        return leaveday;
    }

    public void setLeaveday(double leaveday) {
        this.leaveday = leaveday;
    }

    public double earning(){
        double S=salary-salary/workday*leaveday;
        return S;
    }
    /*public String getInfo(){
       return super.getInfo()+"薪资："+salary+"工作天数"+workday+"请假天数"+leaveday;
    }*/
}
