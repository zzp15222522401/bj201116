package LianXi;
//定义一个程序计算程序运行的时间，但是程序不确定。
// 我们把这个运行的程序归为抽象类。然后在子类中实例化。
//我们把确定的写出来把不确定的定为抽象
public abstract class Text {

    public long codetime(){
        long a=System.currentTimeMillis();
        code1();
        long b=System.currentTimeMillis();
        return  b-a;
    }
    public abstract  void code1();

}
