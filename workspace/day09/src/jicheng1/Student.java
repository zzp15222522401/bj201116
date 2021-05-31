package jicheng1;

import javax.naming.Name;

/*
* 声明子类：Student类，继承Person类
//新增属性：score成绩
//属性私有化，get/set
//包含getInfo()方法：例如：姓名：张三，年龄：23，性别：男，成绩：89*/
public class Student extends Person {
    private double score;

    public Student() {
    }

    public Student(String name, int age, char gender, double score) {
        super(name, age, gender);
        this.score = score;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
    public String getinfo(){
        return super.getinfo()+score;
    }
}
