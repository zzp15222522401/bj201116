package LianXi;
/*声明公民类Citizen，包含属性：姓名，生日，身份证号，
其中姓名是String类型，生日是MyDate类型，身份证号也是String类型。
声明Test03测试类，在main方法中创建你们家庭成员的几个对象，并打印信息。
* */
public class Test03 {
    public static void main(String[] args) {
        String [] arrs=new String[4];//家庭成员4个人
        Citizen baba=new Citizen();
        baba.name="老爸";
        baba.id="111111";
        System.out.println(baba.name+baba.id);
        baba.a(1968,01,15);
        Citizen mama=new Citizen();
        baba.name="老妈";
        baba.id="2222222";
        System.out.println(baba.name+baba.id);
        baba.a(1965,12,18);
        Citizen my=new Citizen();
        baba.name="我";
        baba.id="3333333";
        System.out.println(baba.name+baba.id);
        baba.a(1995,12,25);
        Citizen jiejie=new Citizen();
        baba.name="老姐";
        baba.id="4444444";
        System.out.println(baba.name+baba.id);
        baba.a(1993,10,11);
    }


}
class Citizen {
    MyDate a=new MyDate();
    String name;
    String id;
    void  a(int year,int month,int day){
        System.out.println("生日是："+year+"年"+month+"月"+day+"日");
    }
}
class MyDate{
    int year;
    int month;
    int day;


}
