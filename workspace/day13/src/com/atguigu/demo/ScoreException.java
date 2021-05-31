package com.atguigu.demo;

public class ScoreException extends RuntimeException {
    public ScoreException() {
    }

    public ScoreException(double score) {
        super(score+"登记的分数不对(请输入0-100)");
    }
}
class Test4{
    public static void main(String[] args) {
        Student students=new Student();
        try {
            students.setScore(-5);
        } catch (ScoreException e) {
            e.printStackTrace();
        }
        System.out.println(students);;
    }
}
class Student{
    private String name;
    private int age;
    private double score;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) throws ScoreException{
        if(score>100||score<0)
            throw new ScoreException(score);
        this.score = score;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", score=" + score +
                '}';
    }
}