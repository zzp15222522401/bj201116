package Lian1;

import java.util.Set;

/*定义学生类Student
​	声明姓名和成绩实例变量，私有化，提供get/set
​	getInfo()方法：用于返回学生对象的信息
（2）测试类ObjectArrayTest的main中创建一个可以装3个学生对象的数组，并且按照学生成绩排序，显示学生信息
* */
public class Demo {
    public static void main(String[] args) {
        Student []arrs=new Student[3];                  //可以考虑不限制人数的学生进行登记排序（考虑arrs[]数组超限，进行扩容）
//        Student a=new Student();
        arrs[0]=new Student();
        arrs[0].setCorse(80);
        arrs[0].setName("张三");
        arrs[1]=new Student();
        arrs[1].setCorse(100);
        arrs[1].setName("李四");
        arrs[2]=new Student();
        arrs[2].setCorse(60);
        arrs[2].setName("赵五");
        for (int i = 0; i <arrs.length ; i++) {
            System.out.println(arrs[i].getCorse());//定义完先打印输出查看一下
        }
        System.out.println("*********************");
        for (int i = 0; i <arrs.length-1 ; i++) {
            for (int j = 0; j <arrs.length-1-i ; j++) {
                if(arrs[j].getCorse()<arrs[j+1].getCorse()){
                    Student a=arrs[j];
                    arrs[j]=arrs[j+1];
                    arrs[j+1]=a;
                }
            }

        }
        for (int i = 0; i <arrs.length ; i++) {
            System.out.println(arrs[i].getCorse());//成绩排序完看是否正确
        }
        System.out.println("+++++++++++++++++++++++");
        for (int i = 0; i <arrs.length ; i++) {
            System.out.println(arrs[i].getInfo());
        }
    }
}
class Student{
    private String name;
    private double score;
    void  setName(String name){
         this.name=name;
    }
    String getName(){
        return name;
    }
    void  setCorse(double score){
         this.score=score;
    }
    double getCorse(){
        return score;
    }
    String getInfo(){
      return "学生"+name+"的成绩为"+score;
  }

}
