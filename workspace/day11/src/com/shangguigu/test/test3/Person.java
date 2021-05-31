package com.shangguigu.test.test3;

public  class Person {
    public  void eat(){
        System.out.println("吃饭");
    };
    public  void toilet(){
        System.out.println("上洗手间");
    };
}
class Man extends Person{
    @Override
    public void eat() {
        System.out.println("狼吞虎咽");
    }

    @Override
    public void toilet() {
        System.out.println("站着上");
    }
    public void smoke(){
        System.out.println("抽烟");
    }
}
class Woman extends Person{
    @Override
    public void eat() {
        System.out.println("细嚼慢咽");
    }

    @Override
    public void toilet() {
        System.out.println("蹲着上");
    }
    public void makeup(){
        System.out.println("化妆");
    }
}
class Test15{
    public static void main(String[] args) {
        Person [] person=new Person[4];
        person[0]=new Woman();
        person[1]=new Woman();
        person[2]=new Man();
        person[3]=new Man();
        for (int i = 0; i <person.length ; i++) {
            meeting(person[i]);
        }
    }
    public static void meeting(Person person){
        person.eat();
        person.toilet();
        Person woman=new Woman();
        Person man=new Man();
        Woman woman1=(Woman)woman;
        Man man2=(Man)man;
        Person man1=(Man)man;/*先假设man1是Man中的，然后在判断是否正确*/
        if(man1 instanceof Man){
            man2.smoke();
        }else
            woman1.makeup();
    }
}
