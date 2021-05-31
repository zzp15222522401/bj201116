package Test;

public class Test4 {
    public static void main(String[] args) {
        /*Circle c = new Circle(5);
        Rectangle r = new Rectangle(4, 6);
        Triangle1 t = new Triangle1(3, 4, 5);
        System.out.println("c1的面积：" + c.getArea());
        System.out.println("r1的面积：" + r.getArea());
        System.out.println("t1的面积：" + t.getArea());

        System.out.println("c1和r1的面积是否相等：" + compar(c, r));
        System.out.println("c1和t1的面积是否相等：" + compar(c, t));
        System.out.println("r1和t1的面积是否相等：" + compar(r, t));

        double max1 = bigarea(c, r);
        System.out.println("c1和r1中面积大的是：" + max1);
        double max2 = bigarea(c, t);
        System.out.println("c1和t1中面积大的是：" + max2);
        double max3 = bigarea(r, t);
        System.out.println("r1和t1中面积大的是：" + max3);
*/
        Graphic[] g=new Graphic[3];
        g[0]=new Circle(5);
        g[1]=new Rectangle(6,8);
        g[2]=new Triangle1(3,6,5);
        a1(g);
        System.out.println("********************");
        a2(g);
        a1(g);
    }
    public  static void a1(Graphic[] g){
        for (int i = 0; i < g.length; i++)
            System.out.println(g[i].getInfo());
    }
    public static void a2(Graphic[] g){
        for (int i = 0; i <g.length-1 ; i++) {
            for (int j = 0; j <g.length-i-1 ; j++) {
                if(g[j].getArea()>g[j+1].getArea()){
                    Graphic temp=g[j];
                    g[j]=g[j+1];
                    g[j+1]=temp;
                }
            }
        }

    }

    public static boolean compar(Graphic c, Graphic r) {
        boolean b = false;
        if (c.getArea() == r.getArea()) {
            b = true;
            return b;
        }
        return b;
    }

    public static double bigarea(Graphic c, Graphic r) {
        return c.getArea() > r.getArea() ? c.getArea() : r.getArea();
    }
}

class Graphic{
    public double getArea(){
        return 0.0;
    }
    public double getPerimeter(){
        return 0.0;
    }
    public String getInfo(){
        return "面积："+getArea()+"周长："+getPerimeter();
    }
}
class Circle extends Graphic{
    private  double r;

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    public Circle() {
    }

    public Circle(double r) {
        this.r = r;
    }public double getArea(){
        return Math.PI*r*r;
    }
    public double getPerimeter(){
        return Math.PI*2*r;
    }
    public String getInfo(){
        return "图形："+"圆形"+super.getInfo()+"半径"+r;
    }
}
class Rectangle extends Graphic {
    private double a;
    private double b;

    public Rectangle() {
    }

    public Rectangle(double a, double b) {
        this.a = a;
        this.b = b;
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

    public double getArea() {
        return a * b;
    }

    public double getPerimeter() {
        return 2 * (a + b);
    }

    public String getInfo() {
        return "图形：" + "矩形" + super.getInfo() + "长：" + a + "宽：" + b;
    }
}

class Triangle1 extends Graphic{
    private double a;
    private double b;
    private double c;

    public Triangle1() {
    }

    public Triangle1(double a, double b, double c) {
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


    public double getArea() {

        double p=(a+b+c)/2;

        return Math.sqrt(p*(p-a)*(p-b)*(p-c));
    }


    public double getPerimeter( ) {
        return a+b+c;
    }

    public String getInfo(){
        return "图形："+"三角形"+super.getInfo()+"边"+a+"边"+b+"边"+c;
    }
}
