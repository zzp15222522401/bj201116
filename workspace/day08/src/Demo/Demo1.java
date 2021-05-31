package Demo;

public class Demo1 {
    public static void main(String[] args) {
       /* Demo a=new Demo();
        a.setname("张");
        a.setgender("男");
        a.setage(40);
        System.out.println(a.getage());*/
       /* LianXi a1=new LianXi();
        a1.setAge(10);
        System.out.println(a1.getAge());
        System.out.println("*************");
        LianXi a2=new LianXi(18,"张三",001);*/
        /*LianXi a=new LianXi();
        a.get();
        System.out.println("******************");
        LianXi a1=new LianXi("001","张三",10000,"男");
        System.out.println(a1.get());
        System.out.println("===================");
        a.setId("005");
        System.out.println(a.getId());*/

        Employee a=new Employee("张三",18,20000.0);
        Employee a1=new Employee("李四",25,5000.0);
        Employee a2=new Employee("赵五",30,18000.0);
        Employee a3=new Employee("王六",20,6000.0);
        System.out.println(a.get());
        System.out.println(a1.get());
        System.out.println(a2.get());
        System.out.println(a3.get());

    }
}
