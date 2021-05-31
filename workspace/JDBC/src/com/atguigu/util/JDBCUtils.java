package com.atguigu.util;

import com.alibaba.druid.pool.DruidDataSource;

import java.sql.*;


/**
 *  Class.forName("com.mysql.jdbc.Driver");
 *             connection = DriverManager.getConnection
 *                     ("jdbc:mysql://localhost:3306/day03","root","MySQL");
 */
public class JDBCUtils {
    private static DruidDataSource ds=new DruidDataSource();
    static {
         String driver="com.mysql.jdbc.Driver";
         String url="jdbc:mysql://localhost:3306/day03";
         String username="root";
         String password="MySQL";
        ds.setDriverClassName(driver);
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
    }

    public static void main(String[] args) throws SQLException {//测试
        Connection getconnection = JDBCUtils.getconnection();
        PreparedStatement preparedStatement =null;
        String str="update customer1 set un=? where id=?";
        try {
            preparedStatement= getconnection.prepareStatement(str);
            preparedStatement.setString(1,"abcde");
            int i = preparedStatement.executeUpdate();
            System.out.println(i);
        } finally {
            JDBCUtils.close(getconnection,preparedStatement,null);
        }
    }
    public static Connection getconnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url="jdbc:mysql://localhost:3306/day03";
            String username="root";
            String password="MySQL";
            Connection connection = DriverManager.getConnection(url, username, password);
            return connection;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void close(Connection connection, PreparedStatement pst, ResultSet resultSet){
        try {
            if(connection!=null)
                connection.close();
            if(pst!=null)
                pst.close();
            if(resultSet!=null)
                resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
