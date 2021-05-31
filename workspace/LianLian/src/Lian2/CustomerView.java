package Lian2;

import java.util.Arrays;
import java.util.Scanner;
//int id, String name, char gender, int age, String phone, String email
public class CustomerView {
    public static void main(String[] args) {
        //CustomerList customers=new CustomerList(10);
        Customer[] customers=new Customer[10];
        int count=0;
        Scanner input=new Scanner(System.in);
        do{
            System.out.println("-------------客户信息管理软件--------------\n\t\t\t\t1添 加 客 户\n\t\t\t\t2修 改 客 户\n\t\t\t\t" +
                    "3删 除 客 户\n\t\t\t\t4客 户 列 表\n\t\t\t\t5退       出\n\t\t\t\t请选择（1-5）：");
            int a=input.nextInt();
            if(a==1){
                System.out.print("姓名：");
                String name=input.next();
                System.out.print("性别：");
                char gender=input.next().charAt(0);
                System.out.print("年龄：");
                int age=input.nextInt();
                System.out.print("电话：");
                String phone=input.next();
                System.out.print("邮箱：");
                String email=input.next();
                Customer users=new Customer(name,gender,age,phone,email);
                if(count==customers.length){
                    Customer[] newuser=new Customer[count+1];
                    for (int i = 0; i <customers.length ; i++) {
                        newuser[i]=customers[i];
                    }
                    customers=newuser;
                    //Arrays.copyOf(user,count+1);
                }
                customers[count]=users;
                count++;

            }
           /* if(){}
            if(){}
            if(){}
            if(){}*/

        }
        while(true);



    }
}
