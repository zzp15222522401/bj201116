package xiangmu_mysql;

import java.sql.*;
import java.util.Scanner;

public class Demo {
    Customer customer=new Customer();
    public static void main(String[] args){
        Demo d=new Demo();

            d.takemain();

    }
    public void takemain(){
            Scanner input=new Scanner(System.in);
        while(true){
            System.out.println("欢迎来到尚硅谷 1.登录\t 2.注册\t3.退出");
            int a=input.nextInt();
            Demo d=new Demo();
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
        String str="select * from customer1 where un='"+a+"'";
        String str1 = "insert into customer1 values('" + a + "','" + b + "') ";
        new Demo().method1(str,str1);
    }
    public void case1() {
        Scanner input=new Scanner(System.in);
        System.out.print("请输入登录账号：");
        String a=input.next();
        System.out.print("请输入密码：");
        String b=input.next();
        String str="select * from customer1 where un='"+a+"' and pw='"+b+"'";
        new Demo().method(str);
    }
    public void method(String str){
        Connection connection=null;
        Statement statement=null;
        ResultSet resultSet = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/day03","root","MySQL");
            statement = connection.createStatement();
            try {
                resultSet =  statement.executeQuery(str);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if(resultSet.next()){
                    System.out.println("登录成功");
                }
                else
                    System.out.println("登录失败");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if(connection!=null)
                    connection.close();
                if(statement!=null)
                    statement.close();
                if(resultSet!=null)
                    resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void method1(String str,String str1){
        Connection connection=null;
        Statement statement=null;
        ResultSet resultSet = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/day03","root","MySQL");
            statement = connection.createStatement();

            try {
                resultSet =  statement.executeQuery(str);
                if(resultSet.next()){
                    System.out.println("用户名已存在,请重新注册用户名");
                    return;
                }
                else
                    statement.executeUpdate(str1);
                System.out.println("注册成功");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try {
                if(connection!=null)
                    connection.close();
                if(statement!=null)
                    statement.close();
                if(resultSet!=null)
                    resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}