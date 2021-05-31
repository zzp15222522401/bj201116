package jicheng.lian;

import java.security.PublicKey;

//子类矩形Rectange继承Graphic图形
//包含属性：length、width
//重写求面积getArea()和求周长getPerimeter()方法，显示信息getInfo()加长宽信息
public class Rectange extends Graphic{
    private double length;
    private double width;

    public Rectange() {
    }

    public Rectange(String name, double length, double width) {
        super(name);
        this.length = length;
        this.width = width;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }
    public double getArea(){
        return width*length;
    }public double getPerimeter(){
        return 2*(width+length);
    }public String getInfo(){
        return super.getInfo()+"长度："+length+"宽度："+width;
    }
    public Rectange(String name ,double length ,double width,int i){
        super(name);
        this.length=length;
        this.width=width;
    }
}
