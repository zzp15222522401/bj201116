package Lian2;

public class Demo {
    public static void main(String[] args) {
        //需求，在每次实例化Dept的对象的时候，都要执行一部分代码   Sout("www.atguigu.com");
        Dept dept=new Dept();
        System.out.println("=================================");
        Dept dept1=new Dept("aaa","bbb");

    }
}

class Super{
    {
        System.out.println("父类的初始化块");
    }
    static{
        System.out.println("父类的静态初始化块");
    }

    public Super() {
        System.out.println("父类无参构造");
    }
}

class Dept extends Super{
    private int id;
    private static final String name;
    private String address;
    //实例初始化块   实例化对象一次就调用一次
    {
        //this.name="";
        System.out.println("www.atguigu.com");
    }
    static{
        name="";
        System.out.println("静态初始化块");
    }

    public Dept(int id, String name, String address) {
        System.out.println("全参构造器");
        this.id = id;
        //this.name = name;
        this.address = address;
    }
    public Dept(String name, String address) {
        System.out.println("部分参数构造器");
        //this.name = name;
        this.address = address;

    }
    public Dept() {
        System.out.println("无参构造器");
        //this.name="";
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

   /* public void setName(String name) {
        this.name = name;
    }*/

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

