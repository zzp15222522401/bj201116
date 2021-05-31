package Lian0.test;

public interface Universe {
    void doAnything();
}
class Star{
    public void shine(){
        System.out.println("star:"+"星星一闪一闪亮晶晶");
    }
}
class Sun extends Star implements Universe{
    @Override
    public void shine() {
        System.out.println("sun:"+"光照八分钟，到达地球");
    }

    @Override
    public void doAnything() {
        System.out.println("sun:"+"太阳吸引着9大行星旋转");
    }
}
class Test1{
    public static void main(String[] args) {
        Star star=new Star();
        star.shine();
        System.out.println("*******************");
        Universe universe=new Sun();
        universe.doAnything();
        Sun sun=(Sun)universe;
        sun.shine();
    }
}