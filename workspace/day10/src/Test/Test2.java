package Test;
/**
 * 声明三角形类，包含a,b,c三边
 * ​	（1）属性私有化，提供无参，有参构造，提供get/set
 * ​	（2）重写：toString()
 * ​	（3）重写：hashCode和equals方法
 * ​	（4）编写  public double getArea()：求面积方法
 * ​	（5）编写 public double getPiremeter()：求周长方法
 * ​	2、声明测试类Test12，在测试类中创建两个三角形对象，调用以上方法进行测试
 */
public class Test2 {
    public static void main(String[] args) {
        Triangle s1=new Triangle(5,6,7);
        Triangle s2=new Triangle(7,6,7);
        System.out.println(s1);
        System.out.println(s2);
        System.out.println("s1的周长是："+s1.getPiremeter());
        System.out.println("s1的面积是："+s1.getArea());
        System.out.println("s2的周长是："+s2.getPiremeter());
        System.out.println("s2的面积是："+s2.getArea());
        System.out.println(s1.equals(s2));
    }
}
class Triangle{
    private double a;
    private double b;
    private double c;
    public void Triangle (){
    }

    public Triangle(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public double getC() {
        return c;
    }

    public void setC(double c) {
        this.c = c;
    }

    @Override//对toString进行了重写，所以输出语句变了不在默认为地址值了
    public String toString() {
        return "这是重写的toString方法";
    }

    @Override
    public int hashCode() {
        return -100;
    }

    @Override
    public boolean equals(Object obj) {
        return true;
    }
    public double getArea(){
        double p=(a+b+c)/2;
        return Math.sqrt(p*(p-a)*(p-b)*(p-c));
    }
    public double getPiremeter(){
        double piremeter=a+b+c;
        return piremeter;
    }
}
