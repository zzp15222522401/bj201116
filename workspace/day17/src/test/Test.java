package test;

/**
 * 1、声明一个Person类，包含姓名和伴侣属性，其中姓名是String类型，而伴侣的类型不确定，
 * 因为伴侣可以是Person，可以是Animal（例如：金刚），可以是Ghost鬼（例如：倩女幽魂），
 * 可以是Demon妖（例如：白娘子），可以是Robot机器人（例如：剪刀手爱德华）。。。
 * 2、在测试类中，创建Person对象，并为它指定伴侣，打印显示信息
 */
public class Test {
    public static void main(String[] args) {
        Person<Boolean>person=new Person<Boolean>("金刚",false);
        System.out.println(person);
    }
}
class Person<T>{
    private String name;
    private T ban;

    public Person() {
    }

    public Person(String name, T ban) {
        this.name = name;
        this.ban = ban;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public T getBan() {
        return ban;
    }

    public void setBan(T ban) {
        this.ban = ban;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", ban=" + ban +
                '}';
    }
}