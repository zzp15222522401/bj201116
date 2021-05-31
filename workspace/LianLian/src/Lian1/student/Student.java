package Lian1.student;

public class Student /*implements Comparable*/ {
    private int id;
    private String name;
    private double score;
    private int age;

    public Student(int id, String name, double score, int age) {
        this.id = id;
        this.name = name;
        this.score = score;
        this.age = age;
    }

    public Student() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "ID"+id+"姓名"+name+"成绩"+score+"年龄"+age;
    }

    /*@Override
    public int compareTo(Object o) {
        Student student = (Student) o;
            if (this.score > student.score)
                return 1;

            else if (this.score < student.score)
                return -1;

            else
                return 0;
    }*/
}
