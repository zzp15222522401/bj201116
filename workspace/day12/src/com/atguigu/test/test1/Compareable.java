package com.atguigu.test.test1;

/**
 *自定义一个数组工具类MyArrays，它包含一个静态方法，
 * 可以给任意对象数组用冒泡排序实现从小到大排序，该怎么定义这个方法呢？（用到了Comparable接口其中含有compareTo方法）
 */
/*需要自定义方法编写排序，注意类型转换（重点），因为不确定数组类型，要根据需求确定按什么标准排序，（这里按照编号排序，也可以按照薪资）
        在比较大小时候用到的方法因为继承关系需要重写（按需求重写compareTo）最后调用自己编写的工具类就可以实现排序了*/

public class Compareable {
    public static void main(String[] args) {
        Employee[] arr = new Employee[5];
        arr[0] = new Employee(100,"张三",13000);
        arr[1] = new Employee(20,"李四",13000);
        arr[2] = new Employee(300,"王五",14000);
        arr[3] = new Employee(488,"赵六",14000);
        arr[4] = new Employee(50,"钱七",9000);
        MyArrays.sort(arr);
        for (int i = 0; i <arr.length ; i++) {
            System.out.println(arr[i]);
        }

    }
}
final class  MyArrays{
            public static void sort(Object [] object){/*需要用到多态和compartor*/
                for (int i = 0; i <object.length-1 ; i++) {
                    for (int j = 0; j <object.length-i-1 ; j++) {
                        Comparable a=(Comparable)object[j];//※将任意类型的数组转换成Comparable类型（向下强转）
                         if(a.compareTo(object[j+1])>0){    //利用comparableTo方法对比object[j]和object[j+1]大小，返回值为正数说明j>j+1,否则就小于，=0则相等
                            Object temp=object[j];
                            object[j]=object[j+1];
                            object[j+1]=temp;
                         }                                   //Comparable中有comparableTo方法，可以进行对比，返回int型数据
                    }
                }
            }
        }
class Employee implements Comparable {
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
        return "编号：" + id + "名字：" + name + "薪资：" + salary;
    }

    @Override
    public int compareTo(Object o) {
        Employee employee=(Employee)o;
        if (this.salary!=employee.salary)//重写方法按薪资大小来，薪资相等按序号来。
            return Double.compare(this.getSalary(),employee.getSalary());
        return this.id-employee.id;
    }
}