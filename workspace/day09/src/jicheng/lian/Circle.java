package jicheng.lian;
//子类Circle圆继承Graphic图形
//包含属性：radius
//重写求面积getArea()和求周长getPerimeter()方法，显示信息getInfo()加半径信息
public class Circle extends Graphic {
   private double radius;

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public Circle(String name, double radius) {
        super(name);
        this.radius = radius;
    }

    public Circle() {
    }public double getArea(){
        return Math.PI*radius*radius;
    }public double getPerimeter(){
        return 2*Math.PI*radius;
    }
    String getInfo(){
        return super.getInfo()+"半径："+radius;
    }
}
