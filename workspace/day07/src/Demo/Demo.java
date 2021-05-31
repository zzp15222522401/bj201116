package Demo;

public class Demo {
    /*int i=0;                  //声明成属性
    public static void main(String[] args) {
        // Demo demo=new Demo();
        new Demo().method();            等同于Demo demo=new Demo(); demo.method();
    }

    void method() {

        if (i<5) {
            i++;
            method();
            System.out.println("好好学习天天向上");
        }

    }*/

    public static void main(String[] args) {
        int i=0;            //声明成局部变量，需要在调用方法时添加参数
        // Demo demo=new Demo();
        new Demo().method(i);
    }
    void method(int i) {
        if (i<5) {
            method(i++);
            System.out.println("好好学习天天向上");
        }

}
}

