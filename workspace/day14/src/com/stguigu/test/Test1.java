package com.stguigu.test;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Test1 {
    public static void main(String[] args) {
        //反转键盘录入的字符串123456
        Scanner input = new Scanner(System.in);
        System.out.print("输入想要反转的字符串:");
        String str = input.next();
        char[] chars = str.toCharArray();
        char[] char1=new char[chars.length];
        for (int i = 0; i < chars.length; i++) {
            char1[i] = chars[chars.length - 1 - i];
        }
        String s = String.copyValueOf(char1);
        System.out.println("反转后的字符串为："+s);
    }
        /**
         *  /*
         *     - 键盘录入QQ号码，验证格式的正确性。
         *     - 必须是5—12位数字。
         *     - 0不能开头。
         *     - 代码实现，参考效果如图所示
         *      */
        @Test
        public void Method(){
            Scanner input = new Scanner(System.in);
            System.out.println("请输入您的QQ号码：");
            String str = input.next();
            boolean matches = str.matches("[1-9][0-9]{4,11}");//正则表达式
            System.out.println(matches);
        }
    /**
     * - 键盘录入一个大字符串，再录入一个小字符串。
     * - 统计小字符串在大字符串中出现的次数。
     */
    //javabigdata
    @Test
        public void Method2(){
            Scanner input=new Scanner(System.in);
            System.out.print("大字符串为：");
            String str1=input.next();
            System.out.println("小字符串为：");
            String str2=input.next();
            int count=0;
            boolean b=true;
            while (b){
            if(str1.contains(str2)) {
                count++;
                str1 = str1.substring(str1.indexOf(str2) + 1);

            }
            else
                b=false;
        }
        System.out.println("出现的次数"+count);
    }
    /**
     * 替换某字符串中的某字符串。
     * - 键盘录入一个srcStr字符串，再录入一个delStr字符串。
     * - 删除该字srcStr符串中的所有delStr字符串。
     * - 并且统计delStr字符串在srcStr中出现的次数
     */
    @Test
    public void Method3(){
        Scanner input=new Scanner(System.in);
        System.out.println("srcStr字符串：");
        String srcStr=input.next();
        System.out.println("delStr字符串：");
        String delStr=input.next();
        int count=0;
        String s = srcStr.replaceAll(delStr, "");
        boolean b=true;
        while (b){
            if(srcStr.contains(delStr)) {
                count++;
                srcStr = srcStr.substring(srcStr.indexOf(delStr) + 1);

            }
            else
                b=false;
        }
        System.out.println(delStr+"出现的次数:"+count);
        System.out.println("删除后的字符串："+s);

    }
    /**
     * 生成一个随机100内小数，转换为保留两位小数的字符串，不考虑四舍五入的问题
     */
    @Test
    public void Method4(){
        Random random=new Random();
        double v = random.nextDouble() * 100;
        System.out.println(v);
        String string=v+"";
        System.out.println(string.substring(0,5));

    }

    /**
     *- 定义用户类，属性为用户名和密码。
     * - 使用数组存储多个用户对象。
     * - 录入用户和密码，对比用户信息，匹配成功登录成功，否则登录失败。
     * - 登录失败时，当用户名错误，提示没有该用户。
     * - 登录失败时，当密码错误时，提示密码有误。
     */
    @Test
    public void Method5(){
        Scanner input=new Scanner(System.in);
        System.out.println("输入密码:");
        String str=input.next();
        boolean matches = str.matches("[a-zA-Z0-9][0-9]+[a-z]+[A-Z]{2,},{7,}");
        System.out.println(matches);

    }

    /**
     * - 定义用户类，属性为用户名和密码。
     * - 使用数组存储多个用户对象。
     * - 录入用户和密码，对比用户信息，匹配成功登录成功，否则登录失败。
     * - 登录失败时，当用户名错误，提示没有该用户。
     * - 登录失败时，当密码错误时，提示密码有误。
     */
    public void method1(User[] users){
        int count=0;
        Scanner input=new Scanner(System.in);
        System.out.println("用户名：");
        String y=input.next();
        System.out.println("密码:");
        String m=input.next();
        User user=new User(y,m);
        for (int i = 0; i <users.length ; i++) {
            users[i]=user;
            if(count==users.length)
                Arrays.copyOf(users,count+1);
        }
    }
    public void method2(User[]users){
        Scanner input=new Scanner(System.in);
        System.out.println("输入登录账号：");
        String y1=input.next();
        System.out.println("输入登录密码：");
        String m1=input.next();
        User user1=new User(y1,m1);
        for (int i = 0; i <users.length ; i++) {
            if (user1.getPassword().equals(users[i].getPassword())&&
                    user1.getUsername().equals(users[i].getUsername())){
                System.out.println("登录成功");
                break;
            }
            else if(user1.getUsername().equals(users[i].getUsername())){
                if((user1.getPassword().equals(users[i].getPassword()))!=true){
                    System.out.println("密码有误");
                    break;
                }
            }else
                System.out.println("没有该用户");
                return;
        }
    }
    @Test
    public void Method6(){
        User []users=new User[5];
        int count=0;
        Scanner input =new Scanner(System.in);
        do {
            System.out.println("请选择\n1.注册\t2.登录\t3.退出");
            int a=input.nextInt();
            if(a==1){
                method1(users);
            }
            else if(a==2){
               method2(users);
            }
            if(a==3)
                return;
        }while(true);

    }
}

class User{
    private String username;
    private String password;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String toString(){
        return "用户名："+username+"用户密码："+password;
    }
}



