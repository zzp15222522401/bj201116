
/*
  实现一个基于文本界面的登录注册，可以注册多个用户(用户名和密码)
  登录功能采用哪个用户名和密码都能登录
  多个用户需要用到数组
  登录检测每个用户名和密码是否一一对应		//两个数组对应索引位需要相对应
  本题开始显示最多注册5个用户，如需不限制注册用户名额，需要将数组扩容。-->(本程序最终扩大了)
  是否需要扩容要看输入的数量是否超过数组容量，在case1中的user和pass赋值前进行检测数组容量。
  */
import java.util.Scanner;
public class Home{
    public static void main(String[] args){
        int [] user=new int[1];
        int [] pass=new int[1];
        int count=0;
        Scanner input=new Scanner(System.in);
        boolean flag=true;
        while(flag){
            System.out.println("欢迎来到尚硅谷教育系统	1 注册	2 登录	3 退出");
            System.out.println("请选择：");

            int a=input.nextInt();
            switch(a){
                case 1:
                    System.out.println("注册账号：");
                    int num1=input.nextInt();
                    while(count==user.length){
                        int[] newuser=new int[count+1];		//当输入的用户名数超过原数组的容量时，定义新数组（容量为原数组容量+1）,
                        //循环后每次超出原数组后都会自动扩容，且只增加1，不占用过多内存。
                        for(int b=0;b<count;b++){
                            newuser[b]=user[b];
                        }
                        user=newuser;
                    }
                    user[count]=num1;
                    System.out.println("注册密码：");
                    int pass1=input.nextInt();
                    while(count==pass.length){
                        int[] newpass=new int[count+1];		//当输入的用户名密码数超过原数组的容量时，定义新数组（容量为原数组容量+1），
                        //循环后每次超出原数组后都会自动扩容，且只增加1，不占用过多内存。
                        for(int c=0;c<count;c++){
                            newpass[c]=pass[c];
                        }
                        pass=newpass;
                    }
                    pass[count]=pass1;

                    count++;
                    System.out.println("注册完成");
                    break;
                case 2:
                    System.out.println("登陆账号：");
                    int user2=input.nextInt();
                    System.out.println("登陆密码：");
                    int pass2=input.nextInt();
                    boolean flag1=false;
                    for(int i=0;i<count;i++) {
                        if (user2 == user[i] && pass2 == pass[i]) {
                            flag1 = true;
                            break;
                        }
                    }

                            if (flag1) {
                                System.out.println("登陆成功");
                            }
                            else {
                                System.out.println("登陆失败");
                                    break;
                                }
                    break;
                case 3:
                    System.out.println("是否要退出(是：y/否：n)?");
                    String y1=input.next();
                    if(y1.equals("y")||y1.equals("Y")){
                        System.out.println("退出成功");
                        flag=false;
                    }
                    else if(y1.equals("n")||y1.equals("N")){
                        System.out.println();
                    }
                    else{
                        System.out.println("输入错误!");
                    }
                    break;

            }
            if(a==-1){				//可以显示已经注册过的账户和密码
                for(int i=0;i<count;i++){
                    System.out.println("已注册的账号名和密码\n"+user[i]+"\n"+pass[i]);
                }
            }
            if(a==-2){
                flag=false;
            }
        }


    }

}