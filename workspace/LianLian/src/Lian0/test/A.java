package Lian0.test;

public interface A {
    public void showA();
    public default void showB(){
        System.out.println("BBBB");
    };
}
class B implements A{
    @Override
    public void showA() {
        System.out.println("AAAA");
    }
}
class Test{
    public static void main(String[] args) {
        B b=new B();
        b.showA();
        b.showB();
    }
}
