package jicheng2;

public class Demo {
    public static void main(String[] args){
        Son.test();
        System.out.println("-----------------------------");
        Son.test();
    }
}
class Father{
    private static int a = getNumber();
    static {
        System.out.println("Father(1)");
    }
    private static int b = getNumber();
    static{
        System.out.println("Father(2)");
    }
    public static int getNumber(){
        System.out.println("Father:getNumber()");
        return 1;
    }
}
class Son extends Father{
    private static int a = getNumber();
    static {
        System.out.println("Son(1)");
    }
    private static int b = getNumber();
    static{
        System.out.println("Son(2)");
    }
    public static int getNumber(){
        System.out.println("Son:getNumber()");
        return 1;
    }
    public static void test(){
        System.out.println("Son:test()");
    }
}