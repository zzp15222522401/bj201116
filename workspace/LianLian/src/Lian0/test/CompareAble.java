package Lian0.test;

public interface CompareAble {
    default void compare(Apple a1,Apple a2){
        System.out.println("挑选较大苹果：");
        if(a1.getBig()>a2.getBig())
            System.out.println(a1.toString());
        else
            System.out.println(a2.toString());
    }
}
class Apple{
    private double big;
    private String color;

    public Apple() {
    }

    public Apple(double big, String color) {
        this.big = big;
        this.color = color;
    }

    public double getBig() {
        return big;
    }

    public void setBig(double big) {
        this.big = big;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return big+"-"+color;
    }
}
class CompareColor implements CompareAble{
    public void compare(Apple a1,Apple a2) {/*重写接口的默认方法*/
        System.out.println("挑选红色的:");
        if("红色".equals(a1.getColor()))
            System.out.println(a1.toString());
        else if("红色".equals(a2.getColor()))
            System.out.println(a2.toString());
    }
}
class CompareBig implements CompareAble{
//继承接口的默认方法，不重写就代表还用原来的方法。
}
class Worker{


    public void pickApple(CompareAble c, Apple a1, Apple a2){
        c.compare(a1,a2);
    }
}
class Test2 {
    public static void main(String[] args) {
        Worker worker = new Worker();
        Apple a1 = new Apple(5, "青色");
        Apple a2 = new Apple(3, "红色");
        worker.pickApple(new CompareBig(), a1, a2);
        worker.pickApple(new CompareColor(), a1, a2);

    }
}


