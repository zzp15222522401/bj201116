package Test;
/*声明一个图形工具类GraphicTools，包含如下方法：
1、void printRectangle()：该方法打印5行5列*矩形
2、void printRectangle(int line, int column, String sign)：该方法打印line行colomn列由sign组成的矩形
3、double getTriangleArea(double base, double height)：根据底边和底边对应的高求三角形面积
4、double getTriangleArea(double a, double b, double c)：根据三角形的三条边求三角形面积，如果a,b,c不能组成三角形，打印不能组成三角形，并返回0.0
​	声明Test02测试类，并在main方法中调用测试
* */
public class Count1 {
    void printRectangle(){
        for (int i = 0; i <5 ; i++) {
            for (int j = 0; j <5 ; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
    void printRectangle(int line,int column,String sign){
        for (int i = 0; i <line ; i++) {
            for (int j = 0; j <column ; j++) {
                System.out.print(sign);
            }
            System.out.println();
        }
    }
    double getTriangleArea(double base, double height){
        double area=base*height/2;
        return area;
    }
    double getTriangleArea(double a, double b, double c){
        double area1=0;
        if(a+b>c||a+c>b||b+c>a){
            double q=(a+b+c)/2;
           area1 = Math.pow(q*(q-a)*(q-b)*(q-c), 2);
        }
        return area1;
    }
}
