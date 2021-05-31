package com.atguigu.util;

import java.sql.*;

/**
 * 提供一个数据库连接
 * 关闭资源
 */
public class Customer_util1 {
    public Connection getconnection(){//较util有所提高，我们还可以将加载驱动以及连接数据库这写操作放在
        Connection connection =null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url="jdbc:mysql://localhost:3306/customer1";
            String username="root";
            String password="MySQL";
            connection = DriverManager.getConnection(url, username, password);
            return connection;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void close(Connection connection,PreparedStatement psta, ResultSet rs){
        try {
            if(connection!=null)
                connection.close();
            if(psta!=null)
                psta.close();
            if(rs!=null)
                rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
