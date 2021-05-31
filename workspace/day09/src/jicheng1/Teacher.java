package jicheng1;

import sun.applet.Main;

/*
* 声明子类：Teacher类，继承Person类
//新增属性：salary薪资
//属性私有化，get/set
//包含getInfo()方法：例如：姓名：张三，年龄：23，性别：男，薪资：10000*/
public class Teacher extends Person {
    private double salsry;

    public Teacher() {
    }

    public Teacher(String name, int age, char gender, double salsry) {
        super(name, age, gender);
        this.salsry = salsry;
    }

    public double getSalsry() {
        return salsry;
    }

    public void setSalsry(double salsry) {
        this.salsry = salsry;
    }
    String getinfo(){

        return super.getinfo()+salsry;
    }
}
