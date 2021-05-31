package LianXi;

import Demo.Demo;


public class LianXi {
    /*public static void main(String[] args) {
        Demo a=new Demo();
        a.setage(100);
        System.out.println(a.getage());
    }*/
   /* private int age;
    private String name;
    private int id;
    private  String gender;
    public LianXi(){}
    public LianXi(int a ,String n,int i){
        name=n;
        age=a;
        id=i;

        System.out.println("姓名:"+name+"\n年龄"+age+"\nID"+id);
    }
    public void setAge(int x){
        age=x;
    }
    public int getAge() {
        return age;
    }
    public void setName(String y){
        name=y;
    }
    public String getName(){
        return name;
    }
    public void setId(int z){
        id=z;
    }
    public int getId(){
        return id;
    }
    public String get() {
        return "姓名:" + name + "\n年龄" + age + "\nID" + id;
    }*/
   /*
   * 声明一个员工类，
- 包含属性：编号、姓名、薪资、性别，要求属性私有化，提供get/set，
- 提供无参构造器和有参构造器
- 提供getInfo()
（2）在测试类的main中分别用无参构造和有参构造创建员工类对象，调用getInfo
* JavaBean是 Java语言编写类的一种标准规范。符合JavaBean的类，
* 要求类必须是具体的和公共的，并且具有无参数的构造方法，成员变量私有化，并提供用来操作成员变量的`set` 和`get` 方法。
   * */
    private String id;
    private String name;
    private double salary;
    private String gender;
    public void setId(String id){
        this.id=id;
    }public String getId(){
        return id;
    }public void setName(String name){
        this.name=name;
    }public String getName(){
        return name;
    }public void setSalary(double salary){
        this.salary=salary;
    }public double getSalary(){
        return salary;
    }public void setGender(String gender){
        this.gender=gender;
    }
    public LianXi(){

    }
    public LianXi(String id,String name,double salary,String gender){
        this.id=id;
        this.name=name;
        this.salary=salary;
        this.gender=gender;
    }
    public String get(){
        return "编号："+id+"\t姓名"+name+"\t薪资"+salary+"\t性别"+gender;
    }
}

