package xiangmu_mysql;

import com.atguigu.util.Customer_util;

import java.util.Scanner;

public class Demo1 {
        private Customer customer=new Customer();
        private Customer_util customerUtil=new Customer_util();//方法包
        public static void main(String[] args){
            Demo1 d=new Demo1();

            d.takemain();

        }
        public void takemain(){
            Scanner input=new Scanner(System.in);
            while(true){
                System.out.println("欢迎来到尚硅谷 1.登录\t 2.注册\t3.退出");
                int a=input.nextInt();
                Demo1 d=new Demo1();
                switch(a){
                    case 1:
                        d.case1();
                        break;
                    case 2:
                        d.case2();
                        break;
                    case 3:
                        System.out.println("是否退出（Y/N）");
                        char c=input.next().charAt(0);
                        if("y".equals(c)){
                            System.out.println("退出成功");
                            return;
                        }

                }
            }
        }

        public void case2(){
            Scanner input=new Scanner(System.in);
            System.out.print("请输入注册账号：");
            String a=input.next();
            System.out.print("请输入密码：");
            String b=input.next();
            Customer customer2=new Customer(a,b);
            customerUtil.method1(customer2);
        }
        public void case1() {
            Scanner input=new Scanner(System.in);
            System.out.print("请输入登录账号：");
            String a=input.next();
            System.out.print("请输入密码：");
            String b=input.next();
            customerUtil.method(a,b);

        }

}

