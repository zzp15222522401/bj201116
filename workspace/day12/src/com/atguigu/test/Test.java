package com.atguigu.test;

public class Test {
    public static void main(String[] args) {
        Employee[] arr = new Employee[5];
        arr[0] = new Employee(1,"张三",13000);
        arr[1] = new Employee(2,"李四",13000);
        arr[2] = new Employee(3,"王五",14000);
        arr[3] = new Employee(4,"赵六",7000);
        arr[4] = new Employee(5,"钱七",9000);
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j <arr.length-i-1 ; j++) {
                if(arr[j].compareTo(arr[j+1])>0){
                    Employee temp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                }
            }


        }
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }

    }
}
 class Employee implements Comparable{
    private int id;
    private String name;
    private int salary;

    public Employee() {
    }

    public Employee(int id, String name, int salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
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

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "编号："+id+"名字："+name+"薪资："+salary;
    }

    @Override
    public int compareTo(Object o) {
        if(o==null)
            return  999999999;
        Employee employee=(Employee)o;
        if(this.salary>((Employee) o).salary)
        return 1;
        else if(this.salary<((Employee) o).salary)
            return -1;
        else
            return 0;
    }
}
