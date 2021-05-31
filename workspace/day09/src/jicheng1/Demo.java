package jicheng1;

public class Demo {
    public static void main(String[] args) {
        Student student=new Student("张三",18,'男',98);
        Teacher teacher=new Teacher("李四",25,'男',8000);
        Person person=new Person("赵五",20,'女');

        person.getinfo();
        student.getinfo();
        teacher.getinfo();

    }
}
class fu{
    private int a;
    private double b;

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }
}
class zi extends fu{
    private int c;
    private String d;

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }
}
class Ceshi{
    public static void main(String[] args) {
        fu fu=new fu();
        zi zi=new zi();
       // fu.a   fu.b 子类和父类中含有相同的私有化属性时，属性是不能直接调用的，只能用set/get方法赋值调用；
        fu.setA(5);
        fu.setB(5.5);
        zi.setA(6);
        System.out.println(fu.getA()+" "+fu.getB()+"\n"+zi.getA());
    }
}
