package jicheng.lian;

public class Demo1 {
    public static void main(String[] args) {
        Demo2 a=new Demo2("张三");
        System.out.println(a.get());
        Demo2 b=new Demo2(1,2);
        System.out.println(a.get());
    }

}
class Demo2 {
    private final String A="602";
    private final double B = 1.0;
    private String name;
    public Demo2(String name){
        this.name=name;
    }
    public Demo2(){

    }
    public Demo2(int x,double b){

    }
    String get(){
        return A+"****"+B+name;
    }


}