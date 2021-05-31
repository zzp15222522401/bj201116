public class Test01 {
    public static void main(String[] args) {
        Employee person1=new Employee();
        Employee person2=new Employee();
        person1.name="张三";
        person1.bianhao=1;
        person1.age=23;
        person1.xinzi=10000;
        person2.name="李四";
        person2.bianhao=2;
        person2.age=22;
        person2.xinzi=11000;
        System.out.println("员工1的编号"+ person1.bianhao+"姓名"+person1.name+"年龄"+person1.age+"薪资"+person1.xinzi);
        System.out.println("员工2的编号"+ person2.bianhao+"姓名"+person2.name+"年龄"+person2.age+"薪资"+person2.xinzi);
    }

}
