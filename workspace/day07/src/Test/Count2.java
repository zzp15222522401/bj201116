package Test;

import java.sql.SQLOutput;
import java.util.Scanner;

/*2、声明一个EmployeeManager类，包含如下方法：
​	（1）public void print(Emplyee[] all)：遍历打印员工数组中的每个员工的详细信息
​	（2）public void sort(Employee[] all)：将all员工数组按照年龄从高到低排序
​	（3）public void addSalary(Employee[] all, double increament)：将all员工数组的每一个员工的工资，增加increament
* */
 class EmployeeManager {
    Employee a=new Employee();
    /*Employee[] users=new Employee[5];//固定员工数量，当超过这个数量时会超限；
    Scanner input=new Scanner(System.in);*/
    public void print(Employee[] all){
        for (int i = 0; i <all.length ; i++) {
            all[i].printInfo();
        }


    }
    public void sort(Employee[] all){
        for (int i = 0; i <all.length-1 ; i++) {
            for (int j = 0; j <all.length-1-i ; j++) {
            if(all[j].age<all[j+1].age){
                Employee temp=all[j];
                all[j]=all[j+1];
                all[j+1]=temp;
            }
            }
        }
    }
//将all员工数组的每一个员工的工资，增加increament
    public void addSalary(Employee[] all, double increament){
        for (int i = 0; i <all.length ; i++) {
            all[i].salary=all[i].salary+increament;
        }
    }



   /*   声明一个Employee员工类，
​		包含属性：编号(id)、姓名(name)、薪资(salary)、年龄(age)，此时属性不私有化
​		包含方法：
​		（1）void printInfo()：可以打印员工的详细信息
​		（2）void setInfo(int  i, String n, double s, int a)：可以同时给id,name,salary,age赋值
   * */
}
class Employee {
    int id;
    String name;
    double salary;
    int age;

    void printInfo() {
        System.out.println("编号" + id + "姓名" + name + "薪资" + salary + "年龄" + age);
    }

    void setInfo(int i, String n, double s, int a) {
        id=i;
        name=n;
        salary=s;
        age=a;
    }
}