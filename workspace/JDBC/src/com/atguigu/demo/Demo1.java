package com.atguigu.demo;

import java.sql.*;

public class Demo1 {
    public static void main(String[] args) {//数据库使用完都要关闭资源
        Connection connection =null;
        Statement statement =null;
        ResultSet resultSet =null;
        try {
            Class.forName("com.mysql.jdbc.Driver");//加载驱动
            connection = DriverManager.getConnection
                        ("jdbc:mysql://localhost:3306/bigdata1116", "root", "MySQL");
                       /* 连接数据库
                        drivermanager.getconnection(String url,String username,String password);
                        url 是一种标准格式 "jdbc:数据库类型（可以是mysql,oracle,db2等）://ip地址：端口号/哪个数据库"
                        */
                       //System.out.println(connection);输出无误表示连接好
             statement = connection.createStatement();//获得数据库的命令对象，用这个对象对数据库进行数据的增删改查

            /*String s="update user set car='劳斯莱斯' where salary>30000";
            String s1="insert into user (id,age,gender,salary,car) values(15,50,'男',50000,'红旗')";
            int i = statement.executeUpdate(s);
            int i1 = statement.executeUpdate(s1);*/

                    /*update就是增删改（）里是mysql的操作语句,字符串类型 例 "select * from bigdata1116.user";
                    返回值是一个int类型数据，表示的是mysql中的改变行数 (查询：select *from user LIMIT 0, 1000   返回了 8 行 )相当与8
                    */
            // System.out.println(i1);

            //数据库的查询(分3种)
               //a.查询结果只有一个数据
            String str1="select salary from user where id=15";
              // b.查询结果只有一行（一条）数据
            String str2="select * from user where id=6";
               //c.查询结果有N条
            String str="select * from user where salary>30000";


             //resultSet = statement.executeQuery(str);//Query表示的是查询方法 里面还是字符串（mysql的操作语句）
             resultSet = statement.executeQuery(str1);
             //返回的是结果集，和mysql中的结果集一样，都是临时的一个表
            //根据返回的结果集可以查询结果集中的任意某个数据 ，前提是需要有数据，我们要先进性判断
            if(resultSet.next()){
                System.out.println(resultSet.getDouble(1));
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
