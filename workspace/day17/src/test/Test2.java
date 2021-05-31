package test;

import java.util.Comparator;

public class Test2 {
    public static void main(String[] args) {
        Student[] student=new Student[5];
        student[0]=new Student("张1",1,98.5);
        student[1]=new Student("张2",100,110);
        student[2]=new Student("张3",58,60);
        student[3]=new Student("张4",38,36);
        student[4]=new Student("张5",76,70);
        Mysort.sort1(student);
        for (Student student1 : student) {
            System.out.println(student1);
        }
        System.out.println("********************");
        Mysort.sort2(student, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return (int)(o1.getScore()-o2.getScore());
            }
        });
        for (Student student1 : student) {
            System.out.println(student1);
        }

    }
}
class Mysort{//继承了Comparable接口后的方法重写，对数组排序
    public static <T extends Comparable> void sort1(T[] t){
        for (int i = 0; i <t.length-1 ; i++) {
            for (int j = 0; j <t.length-i-1 ; j++) {
                if (t[j].compareTo(t[j+1])>0) {
                    T temp=t[j];
                    t[j]=t[j+1];
                    t[j+1]=temp;
                }
            }
        }
    }
    //没有继承接口，任意数组的排序
    public static<A> void sort2(A[] ts, Comparator<A> comparator){
        for (int i = 0; i <ts.length-1 ; i++) {
            for (int j = 0; j <ts.length-i-1 ; j++) {
                if(comparator.compare(ts[j],ts[j+1])>0){
                    A temp=ts[j];
                    ts[j]=ts[j+1];
                    ts[j+1]=temp;
            }
        }
    }
}
}
class Student implements Comparable<Student>{
    private String name;
    private int ID;
    private double score;

    public Student() {
    }

    public Student(String name, int ID, double score) {
        this.name = name;
        this.ID = ID;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", ID=" + ID +
                ", score=" + score +
                '}';
    }

    @Override
    public int compareTo(Student o) {
        return this.ID-o.ID;
    }
}