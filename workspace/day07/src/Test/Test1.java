package Test;
/*声明一个图形工具类GraphicTools，包含如下方法：
1、void printRectangle()：该方法打印5行5列*矩形
2、void printRectangle(int line, int column, String sign)：该方法打印line行colomn列由sign组成的矩形
3、double getTriangleArea(double base, double height)：根据底边和底边对应的高求三角形面积
4、double getTriangleArea(double a, double b, double c)：根据三角形的三条边求三角形面积，如果a,b,c不能组成三角形，打印不能组成三角形，并返回0.0
​	声明Test02测试类，并在main方法中调用测试
* */
public class Test1 {
    public static void main(String[] args) {
        Count1 a=new Count1();
        a.printRectangle();
        a.printRectangle(5,4,"我爱你");
        double area=a.getTriangleArea(5.4,10.2);
        System.out.println(area);
        double area1=a.getTriangleArea(10.0,10.0,5.0);
        System.out.println(area1);
    }

}
