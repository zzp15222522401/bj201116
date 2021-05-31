package LianXi;

import com.sun.javafx.image.BytePixelSetter;

/*声明一个三角形类Triangle，包含属性：a,b,c，表示三条边，包含几个方法：
1、boolean  isRightTriangle()：判断是否是一个直角三角形
2、boolean isIsoscelesTriangle()：判断是否是一个等腰三角形
3、boolean isEquilateralTriangle()：判断是否是一个等边三角形
4、double getArea()：根据三条边，用海伦公式求面积
5、double getLength()：求周长
* */
public class Test4 {
    public static void main(String[] args) {
        Triangle sanjiao=new Triangle();
        boolean a=sanjiao.isIsoscelesTriangle(3,4,5);
        System.out.println(a);
        boolean b=sanjiao.isRightTriangle(3,4,5);
        System.out.println(b);
        boolean c=sanjiao.isEquilateralTriangle(3,3,3);
        System.out.println(c);
        double d=sanjiao.getLength(5,6,7);
        System.out.println("周长为："+d);
    }
}
class Triangle {
    boolean isRightTriangle(double a, double b, double c) {
            System.out.println("是否是直角三角形");
        if ((a * a + b * b == c * c) || (a * a + c * c == b * b) || (b * b + c * c == a * a)) {
            return true;
        } else
            return false;
    }
        boolean isIsoscelesTriangle(double a,double b, double c) {
            System.out.println("是否是等腰三角形");
            if (a == b || a == c || b == c) {
                return true;
            } else
                return false;

        }
    boolean isEquilateralTriangle(double a,double b, double c){
        System.out.println("是否是等边三角形");
        if (a == b && a == c ) {
            return true;
        } else
            return false;
    }
    double getLength(double a,double b, double c){
        double Length=a+b+c;
        return Length;
    }
}