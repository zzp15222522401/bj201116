package com.atguigu.test.test1;
import java.util.Comparator;
/**
 * （1）如果一个类，没有实现Comparable接口，而这个类你又不方便修改
 * （例如：一些第三方的类，你只有.class文件，没有源文件），那么这样类的对象也要比较大小怎么办？
 * （2）如果一个类，实现了Comparable接口，也指定了两个对象的比较大小的规则，
 * 但是此时此刻我不想按照它预定义的方法比较大小，但是我又不能随意修改，因为会影响其他地方的使用，怎么办？
 * JDK在设计类库之初，也考虑到这种情况了，所以又增加了一个java.util.Comparator接口。
 */
class Test1_1{

    public static void main(String[] args) {

        Employee2 a1= new Employee2(300,"王五",14000);
        Employee2 a2= new Employee2(488,"赵六",7000);
        Comparator1 c=new Comparator1();//实例化对象为了调用compare方法
        if(c.compare(a1,a2)>0)/*比较大小时，通过比较器类型的对象调用compare()方法，
                                将要比较大小的两个对象作为compare方法的实参传入，根据方法的返回值决定谁大谁小*/
            System.out.println(a1.toString());
        else
            System.out.println(a2.toString());
    }

}
/*第一步：编写一个类，我们称之为比较器类型，实现java.util.Comparator接口，并重写方法
- 方法体就是你要如何指定的两个对象的大小    如下：
* */

public class Comparator1 implements Comparator {//比较类继承Comparator中的compare方法，重写其方法
    public int compare(Object o1, Object o2) {//把o1,o2转换成Employee类型，然后根据Employee中的某一个属性值进行判断大小。
        Employee2 e1=(Employee2)o1;
        Employee2 e2=(Employee2)o2;
        if(e1.getSalary()>e2.getSalary())
        return 1;
        else if (e1.getSalary()<e2.getSalary())
            return -1;
        else
            return 0;
    }



}


class Employee2{
    private int id;
    private String name;
    private double salary;

    public Employee2() {
    }

    public Employee2(int id, String name, double salary) {
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

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "编号：" + id + "名字：" + name + "薪资：" + salary;
    }
}