package duixiangshuzu;

import javax.swing.plaf.FontUIResource;
import java.util.Scanner;

public class Demo2 {
    public static void main(String[] args) {
        User[] users=new User[5];//定义一个数组用来存储用户数量，这里只规定5个；题中有考虑超界问题的处理，不会限制用户数量
        int count=0;
        Scanner input=new Scanner(System.in);
        do{
            System.out.println("欢迎来到尚硅谷\n1.注册2.登录3.退出");
            System.out.println("请选择：");
            int a=input.nextInt();
            if(a==1){
                System.out.println("欢迎来到注册界面");
                System.out.println("注册账号为：");
                String id=input.next();
                System.out.println("注册密码为：");
                String password=input.next();
                System.out.println("昵称为：");
                String username=input.next();
                System.out.println("性别为：");
                String gender=input.next();
                //将这些数据保存起来
                //1. 将用户的注册信息创建一个对象，设置为其属性值
                User user=new User();
                user.id=id;
                user.pass=password;
                user.name=username;
                user.gender=gender;
                if(count==users.length){                          //此方法对数组进行扩容，可以保证数组不会越界，在本题中注册用户就不会有限制
                   User [] newusers= new User[count+1];
                    for (int i = 0; i <users.length ; i++) {
                        newusers[i]=users[i];
                    }
                   users=newusers;
                }
                users[count]=user;
                count++;//可以采取调用方法来做
            }
            else if(a==2){
                System.out.println("欢迎来到登录界面");
                System.out.println("登陆账号为：");
                String username=input.next();
                System.out.println("登陆密码为：");
                String password=input.next();
                //进行账号和密码的比对（数据查询）
                /*User x=null;
                for (int i = 0; i <count ; i++) {
                        if(username.equals(users[i].id)&&password.equals(users[i].pass)) {
                        // 如果输入为null的话会出现空指针现象，所以要判断输入是否为null值才能判断登录是否成功。
                        //   System.out.println("登陆成功");
                            x=users[i];
                            break;
                        }
                }       */
                User x=Util.putin(username,password);{
//                for循环可以调用方法(User一个变量接收)
                        if(x!=null){
                            System.out.println("登陆成功");
                            System.out.println("欢迎"+x.name);
                        }else{
                            System.out.println("登陆失败");
                        }
                }
            }

            else if(a==3){
                System.out.println("是否退出（y/n）");
                String b=input.next();
                if(b.equals("y")){
                    break;
                }
            }
        }while (true);

    }
}
