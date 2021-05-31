/*



*/




public class Demo1 {
    public static void main(String[] args) {
        Person person=new Person();
       /* person.isMarried=false;
        Person person1=new Person();
        person.display="1";
        person.name="zhangsan";
        person1.name="lisi";
        System.out.println(person.name+person.isMarried);
        System.out.println(person1.name);*/
        /*Scanner input=new Scanner(System.in);
        System.out.println("名字：");
         person.a.name=input.next();
        System.out.println("数量：");
        person.a.i=input.nextInt();
       person.a.name="张三";
       person.a.i=10;
        System.out.println(person.a.x());*/
        person.name="张志鹏";
        System.out.println(person.name);
        Phone phone1=new Phone();
        Phone phone2=new Phone();
        phone1.name="huawei";
        phone1.size=256;
        phone2.name="huawei";
        phone2.size=512;
        Phone [] phones={phone1,phone2};
        person.phone=phones;
        for (int j = 0; j <phones.length ; j++) {
            System.out.println(phones[j].name+" "+phones[j].size);//回来在看看
        }


       /* Util util=new Util();
        //util.util();



        int[]arrs={10,15,94,35,4,100};
        util.paixu(arrs,"升序");
        for (int i = 0; i <arrs.length ; i++) {
            System.out.println(arrs[i]);
        }*/




      /* int num=util.num();
        System.out.println(num);*/

       /* int[]arr={10,3,51,20,34,17};
        int nummber=util.shuzu(arrs);
        System.out.println(nummber);*/
       /* Person newperson= new Person();
        newperson.sayHello();*/

/**
 Person类中，手机可能是多个，通过程序设计，将每个人的信息都存储起来
 */
        /*Person person1=new Person();
        person1.name="张志鹏";


        Phone phone1=new Phone();
        phone1.name="小米";
        phone1.size=256;

        Phone phone2=new Phone();
        phone2.name="华为";
        phone2.size=512;

        Phone[] phones={phone1,phone2};

        person1.phone=phones;
        System.out.println(person1.name+"的手机信息");
        for (int i = 0; i <phones.length; i++) {
            System.out.println(phones[i].name+phones[i].size);
        }*/

    }
}
