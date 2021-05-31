package Lian1.student;

import java.util.Arrays;
import java.util.Comparator;

public class Test {
    public static void main(String[] args) {
        Student[]students=new Student[5];
        students[0]=new Student(20,"张三",88,18);
        students[1]=new Student(50,"李四",90,19);
        students[2]=new Student(18,"王五",110,18);
        students[3]=new Student(9,"刘三",65,20);
        students[4]=new Student(46,"吴三",48,21);


        /*Arrays.sort(students);*/
       /* Mysort.method(students);
        for (Student student : students) {
            System.out.println(student);
        }*/
        /*Arrays.sort(students,new Compare());
        for (Student student : students) {
            System.out.println(student);
        }*/
        Comparator <Student>comparator=new Comparator<Student>() {
            @Override//按名字排序
            public int compare(Student o1, Student o2) {
               return o1.getName().compareTo(o2.getName());
            }
        };
        Arrays.sort(students,comparator);
        for (Student student : students) {
            System.out.println(student);
        }
    }
}
class Mysort{//重写Arrays.sort方法，查看compareTo方法的使用
    public static void method(Object[] objects){
        for (int i = 0; i <objects.length-1 ; i++) {
            for (int j = 0; j <objects.length-1-i ; j++) {
                Comparable a1=(Comparable)objects[j];
                Comparable a2=(Comparable)objects[j+1];
                if(a1.compareTo(a2)>0){
                    Object temp=objects[j];
                    objects[j]=objects[j+1];
                    objects[j+1]=temp;
                }

            }
        }
    }
}
class Compare implements Comparator{

    @Override
    public int compare(Object o1, Object o2) {
        Student a1=(Student) o1;
        Student a2=(Student) o2;
        if(a1.getAge()>a2.getAge())
            return 1;
        else if(a1.getAge()<a2.getAge())
            return -1;
        else
        return 0;
    }
}