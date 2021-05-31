package Test;

import java.util.Scanner;
import java.util.jar.JarEntry;

/*）public static void main(String[] args)：在main方法中，创建Employee[]数组，
并创建5个员工对象放到数组中，并为员工对象的属性赋值
（2）创建EmployeeManager对象，
（3）调用print方法，显示员工信息
（4）调用sort方法对员工数组进行按照年龄排序，并调用print方法，显示员工信息
（5）调用addSalary方法给每一个员工加薪1000元，并调用print方法，显示员工信息
    编号(id)、姓名(name)、薪资(salary)、年龄(age)
* */
public class Test2 {
    public static void main(String[] args) {
        Employee[] users=new Employee[5];//固定员工数量，当超过这个数量时会超限；
        Scanner input=new Scanner(System.in);

        users[0] = new Employee();
        users[0].setInfo(1,"张三",10000,23);

        users[1] = new Employee();
        users[1].setInfo(2,"李四",12000,28);

        users[2] = new Employee();
        users[2].setInfo(3,"王五",8000,18);

        users[3] = new Employee();
        users[3].setInfo(4,"赵六",6000,40);

        users[4] = new Employee();
        users[4].setInfo(5,"钱七",15000,21);

        EmployeeManager x=new EmployeeManager();
        x.print(users);
        System.out.println("------------------------------");
        x.sort(users);
        x.print(users);
        System.out.println("****************************");
        x.addSalary(users,1000.0);
        x.print(users);



    }
}
