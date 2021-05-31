package com.atguigu.test;

public class Test1 {
    public static void main(String[] args) {
        Person person= null;
        Person person3=new Person();
        try {
            person = new Person("张三",-20);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(person);
        Person person1=new Person();
        try {
            person1.setLifeValue(100);
            person1.setLifeValue(-50);

        } catch (NoLifeValueException e) {
            e.printStackTrace();
        }
        System.out.println(person1);

    }
}
class NoLifeValueException extends RuntimeException{
    public NoLifeValueException() {
    }

    public NoLifeValueException(int lifeValue) {
        super("生命值不能为负："+lifeValue);
    }
}
class Person{
    static String name;
    private int lifeValue;

    public Person() {
    }

    public Person(String name, int lifeValue) {
        this.name = name;
        this.lifeValue = lifeValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLifeValue() {
        return lifeValue;
    }

    public void setLifeValue(int lifeValue)throws NoLifeValueException {
        if(lifeValue<0)
            throw new NoLifeValueException(lifeValue);
        this.lifeValue = lifeValue;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", lifeValue=" + lifeValue +
                '}';
    }

}abstract class A{
    public abstract void method();
}