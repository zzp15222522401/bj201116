package Demo;

import javax.sound.midi.Soundbank;

/**
 * - 员工类属性：编号、姓名、年龄、薪资
 * - 程序员类属性：编程语言，默认都是"java"
 * - 设计师类属性：奖金
 * - 架构师类属性：持有股票数量
 *   要求：属性私有化，无参有参构造，get/set，getInfo方法（考虑重写）
 * 2、在com.atguigu.test16包中声明Test16类，并在main中创建每一个类的对象，并为属性赋值，并调用它们的getInfo()显示信息
 */
public class Test {
    public static void main(String[] args) {
        Employee a=new Employee("a01","张三",20,5000);
        System.out.println(a.getInfo());
        Programmer b=new Programmer("b02","李四",30,8000);
        System.out.println(b.getInfo());
        Designer c=new Designer("c03","王五",40,10000,"C++",5000);
        System.out.println(c.getInfo());
        Architect d=new Architect("d04","六",50,15000,2000,"30%");
        System.out.println(d.getInfo());
    }
}
class Employee{
    private String Id;
    private String name;
    private int age;
    private double salary;

    public Employee() {
    }

    public Employee(String id, String name, int age, double salary) {
        Id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
    public String getInfo(){
        return "编号："+Id+"姓名："+name+"年龄："+age+"薪资:"+salary;
    }
}
class Programmer extends Employee{
    private  String write="java";

    public Programmer() {
    }

    public Programmer(String id, String name, int age, double salary) {
        super(id, name, age, salary);
    }

    public Programmer(String id, String name, int age, double salary, String write) {
        super(id, name, age, salary);
        this.write = write;
    }

    public String getWrite() {
        return write;
    }
    public String getInfo(){
        return super.getInfo()+"编程语言:"+write;
    }
}
class Designer extends Programmer{
    private double salarys;

    public Designer() {
    }

    public Designer(String id, String name, int age, double salary, String write, double salarys) {
        super(id, name, age, salary, write);
        this.salarys = salarys;
    }

    public Designer(String id, String name, int age, double salary, double salarys) {
        super(id, name, age, salary);
        this.salarys = salarys;
    }

    public double getSalarys() {
        return salarys;
    }

    public void setSalarys(double salarys) {
        this.salarys = salarys;
    }public String getInfo(){
        return super.getInfo()+"奖金："+salarys;
    }
}
class Architect extends Designer{
    private String havestock;

    public Architect() {
    }

    public Architect(String id, String name, int age, double salary, String write, double salarys, String havestock) {
        super(id, name, age, salary, write, salarys);
        this.havestock = havestock;
    }

    public Architect(String id, String name, int age, double salary, double salarys, String havestock) {
        super(id, name, age, salary, salarys);
        this.havestock = havestock;
    }

    public String getHavestock() {
        return havestock;
    }

    public void setHavestock(String havestock) {
        this.havestock = havestock;
    }
    public String getInfo(){
        return super.getInfo()+"持有股票数量："+havestock;
    }
}
