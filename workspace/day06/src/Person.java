

 class Person {

   /* Person a=new Person();*/
/*

    String name;
    Phone[] phone;
*/


    //属性
    String name;
    String password="123456";
    int age;
    double score;
    Phone[] phone;
    //该属性用于存储人的多部手机,每个手机有多个属性。



    //方法
    //做一个简单的自我介绍    谁调用我我就获取谁的name/age/score
    void sayHello(){
        System.out.println("我的名字是："+name);
        System.out.println("我的号码是 ："+password);
        System.out.println("我的年龄是："+age);
        System.out.println("我的成绩是："+score);
    }



}
