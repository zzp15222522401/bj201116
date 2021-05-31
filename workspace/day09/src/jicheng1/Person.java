package jicheng1;
//1、声明父类：Person类
//包含属性：姓名，年龄，性别
//属性私有化，get/set
//包含getInfo()方法：例如：姓名：张三，年龄：23，性别：男
//
//2、声明子类：Student类，继承Person类
//新增属性：score成绩
//属性私有化，get/set
//包含getInfo()方法：例如：姓名：张三，年龄：23，性别：男，成绩：89
//
//3、声明子类：Teacher类，继承Person类
//新增属性：salary薪资
//属性私有化，get/set
//包含getInfo()方法：例如：姓名：张三，年龄：23，性别：男，薪资：10000
public class Person {
    private String name;
    private int age;
    private char gender;

    public Person() {
    }

    public Person(String name, int age, char gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

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

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }
     String getinfo(){
        System.out.println("姓名"+name+"年龄"+age+"性别"+gender);
        return "";
    }
}
