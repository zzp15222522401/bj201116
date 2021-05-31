package com.atguigu.util;

import xiangmu_mysql.Customer;

import java.sql.*;

public class Customer_util {
    /**
     * 每次用户注册或者登录都需要进行  1加载驱动   2连接数据库 3资源的关闭  效率很低
     * 在Utils1中我们将加载驱动和连接数据库以及关闭资源进行封装，减少代码量，增加代码的复用性
     * @param un
     * @param pw
     */
    public void method(String un,String pw){//检测登录的方法
        Connection connection=null;
        PreparedStatement preparedStatement =null;
        ResultSet resultSet = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/day03","root","MySQL");
            //"jdbc:mysql://localhost:3306/day03?rewriteBatchedStatements=true" 批处理需要改变url
            //preparedStatement.addBatch();
            //preparedStatement.executeBatch();
            String str="select * from customer1 where un=? and pw=?";
            preparedStatement = connection.prepareStatement(str);
            preparedStatement.setString(1,un);
            preparedStatement.setString(2,pw);

            try {
                resultSet =  preparedStatement.executeQuery(str);
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
                if(preparedStatement!=null)
                    preparedStatement.close();
                if(resultSet!=null)
                    resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void method1(Customer C){//检测用户注册的方法
        Connection connection=null;
        PreparedStatement preparedStatement =null;
        ResultSet resultSet = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/day03","root","MySQL");
            try {
                String str="select * from customer1 where un=?";
                preparedStatement = connection.prepareStatement(str);
                preparedStatement.setString(1,C.getUn());
                resultSet =  preparedStatement.executeQuery();
                if(resultSet.next()){
                    System.out.println("用户名已存在,请重新注册用户名");
                    return;
                }
                else {
                    String str1 = "insert into customer1 values(null,?,?)";
                    preparedStatement = connection.prepareStatement(str1);
                    preparedStatement.setString(1, C.getUn());
                    preparedStatement.setString(2, C.getPw());
                    int i = preparedStatement.executeUpdate();
                    System.out.println("注册成功");
                }
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
                if(preparedStatement!=null)
                    preparedStatement.close();
                if(resultSet!=null)
                    resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
