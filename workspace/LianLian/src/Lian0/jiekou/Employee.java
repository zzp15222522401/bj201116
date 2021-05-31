package Lian0.jiekou;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 声明一个Employee员工类，包含编号、姓名、薪资，
 * 声明一个测试类，在main中，创建Employee[]数组，长度为5，显示原来顺序结果
 * 调用java.util.Arrays数组工具类的排序方法public static void sort(Object[] a, Comparator c)
 * 对数组的元素进行排序，用匿名内部类的对象给c形参传入按照薪资比较大小的定制比较器对象。并显示排序后结果
 * 调用java.util.Arrays数组工具类的排序方法public static void sort(Object[] a, Comparator c)
 * 对数组的元素进行排序，用匿名内部类的对象给c形参传入按照编号比较大小的定制比较器对象。并显示排序后结果
 */
class Testemployee{
    public static void main(String[] args) {
        Employee[] employees=new Employee[5];
        employees[0]=new Employee(100,"张三",8000);
        employees[1]=new Employee(200,"赵四",7000);
        employees[2]=new Employee(300,"王五",6000);
        employees[3]=new Employee(400,"李六",5000);
        employees[4]=new Employee(500,"杨七",4000);
        for (Employee employee : employees) {
            System.out.println(employee.toString());
        }
        System.out.println("*********************");
        Arrays.sort(employees,new Comparator(){
            @Override
            public int compare(Object o1, Object o2) {
                Employee e1=(Employee)o1;
                Employee e2=(Employee)o2;
                return (int) (e1.getSalary()-e2.getSalary());
            }
        });

        for (Employee employee : employees) {
            System.out.println(employee.toString());
        }
    }
}


public class Employee {
    private int id;
    private String name;
    private int salary;

    public Employee(int id, String name, int salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public Employee() {
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

    public double getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return    "编号："+id+"\t姓名："+name+"\t薪资："+salary;
    }
}
