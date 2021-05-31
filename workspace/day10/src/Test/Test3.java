package Test;

public class Test3 {
    public static void main(String[] args) {
        final int EMPLOYEE = 10;//表示普通员工
        final int PROGRAMMER = 11;//表示程序员
        final int DESIGNER = 12;//表示设计师
        final int ARCHITECT = 13;//表示架构师

        String[][] EMPLOYEES = {
                {"10", "1", "段誉", "22", "3000"},
                {"13", "2", "令狐冲", "32", "18000", "15000", "2000"},
                {"11", "3", "任我行", "23", "7000"},
                {"11", "4", "张三丰", "24", "7300"},
                {"12", "5", "周芷若", "28", "10000", "5000"},
                {"11", "6", "赵敏", "22", "6800"},
                {"12", "7", "张无忌", "29", "10800","5200"},
                {"13", "8", "韦小宝", "30", "19800", "15000", "2500"},
                {"12", "9", "杨过", "26", "9800", "5500"},
                {"11", "10", "小龙女", "21", "6600"},
                {"11", "11", "郭靖", "25", "7100"},
                {"12", "12", "黄蓉", "27", "9600", "4800"}
        };

    }
}
class Employee{
    private  int id;
    private String name;
    private int age;
    private  double salary;

    public Employee() {
    }

    public Employee(int id, String name, int age, double salary) {
        this.id = id;
        this.name = name;
        this.age = age;
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
    public String getinfo(){
        return "编号："+id+"姓名："+name+"年龄："+age+"薪资："+salary;
    }
}
class Programmer extends Employee{
    private   String write="java";

    public Programmer() {
    }

    public Programmer(int id, String name, int age, double salary) {
        super(id, name, age, salary);
    }

    public String getWrite() {
        return write;
    }

    public void setWrite(String write) {
        this.write = write;
    }
    public String getinfo(){
        return super.getinfo()+write;
    }
}
class Designer extends Programmer{
    private int salarys;

    public Designer() {
    }

    public Designer(int id, String name, int age, double salary, int salarys) {
        super(id, name, age, salary);
        this.salarys = salarys;
    }

    public int getSalarys() {
        return salarys;
    }

    public void setSalarys(int salarys) {
        this.salarys = salarys;
    }
    public String getinfo(){
        return super.getinfo()+salarys;
    }
}
class Architect extends Designer{
    private double stock;

    public Architect() {
    }

    public Architect(int id, String name, int age, double salary, int salarys, double stock) {
        super(id, name, age, salary, salarys);
        this.stock = stock;
    }

    public double getStock() {
        return stock;
    }

    public void setStock(double stock) {
        this.stock = stock;
    }
    public String getinfo(){
        return super.getinfo()+stock;
    }
}
