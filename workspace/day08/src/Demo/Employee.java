package Demo;
//id自增，所以此ID绝对不可能是静态的，不能被static修饰，需要重新定义一个静态的属性，然后在此基础上自增赋给ID。
//被static修饰的方法，可以直接用类名去调用，看需求采取是否用对象去调。例在Demo1中可以直接使用Employee.test();
public class Employee {
    private  int id;
    //private static int id=1000;
    private String name;
    private int age;
    private double salary;
    private static int i=1000;
    public  Employee(String name,int age,Double salary){
        this.id=i++;
        this.name=name;
        this.age=age;
        this.salary=salary;

    }
   /* public static void test(){        静态方法中无法直接调用非静态的资源，没有this关键字，动态方法中就可以直接使用静态资源。
        System.out.println(i);
        System.out.println(age);
    }*/

    public Employee() {
    }
    public int getId() {
        return id;
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
    public String get(){
        return "id为："+id+"姓名："+name+"年龄："+age+"薪资："+salary;
    }
}
