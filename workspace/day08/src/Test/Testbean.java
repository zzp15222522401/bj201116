package Test;

import java.security.PrivilegedExceptionAction;

/*- 在com.atguigu.test02.bean包中定义一个圆形Circle类。
  - 属性：私有化
    - r：半径-
    构造方法：
    - 无参构造方法
    - 满参构造方法
  - 成员方法：
    - get/set方法
    - showArea方法：打印圆形面积
    - showPerimeter方法：打印圆形周长
- 在com.aguigu.test02.test包中定义测试类TestCircle：创建Circle对象，并测试。
- 开发提示：
  > 面向周长公式：2 * 3.14*  半径
  > 圆形面积公式：3.14* 半径^2
* */
public class Testbean {
    /*private double r;
    private double pi= Math.PI;
    public Testbean(){
    }public Testbean(double r){
        this.r=r;
    }
    public double getR() {
        return r;
    }
    public void setR(double r) {
        this.r = r;
    }
    //showArea方法：打印圆形面积
    //- showPerimeter方法：打印圆形周长
    public double showArea(){
        double area=pi*r*r;
        return area;
    }
    public void showPerimeter(){
        double Perimeter=2*pi*r;
        System.out.println(Perimeter);
    }*/


    /*
    * - - year：年
  - month：月
  - day：日
- 构造方法：
  - 满参构造方法
- 成员方法：
  - get/set方法
  - void showDate方法：打印日期。
  - boolean isLeapYear()方法：判断当前日期是否是闰年
    * */
    private int year;
    private int month;
    private int day;
    Testbean(int year,int month,int day){
        this.year=year;
        this.month=month;
        this.day=day;
    }

    public Testbean() {

    }

    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public int getMonth() {
        return month;
    }
    public void setMonth(int month) {
        this.month = month;
    }
    public int getDay() {
        return day;
    }
    public void setDay(int day) {
        this.day = day;
    }
    //void showDate方法：打印日期。
    //  - boolean isLeapYear()方法：判断当前日期是否是闰年
    public void showDate(){
        System.out.println(year+"年"+month+"月"+day+"日");
    }
    boolean isLeapYear(){
        if(year%4==0&&year%100!=0||year%400==0){
            return true;
        }
        return false;
    }
}
