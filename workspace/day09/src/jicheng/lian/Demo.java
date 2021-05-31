package jicheng.lian;

public class Demo {
    public static void main(String[] args) {
        Graphic a=new Graphic("sanjiao");
        System.out.println(a.getInfo());
        Circle b=new Circle("圆形",5);
        System.out.println(b.getInfo());
        Rectange c=new Rectange("矩形",4,5);
        System.out.println(c.getInfo());

    }
}
