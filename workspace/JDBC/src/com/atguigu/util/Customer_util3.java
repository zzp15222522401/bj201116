package com.atguigu.util;

import com.alibaba.druid.pool.DruidDataSource;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 上一个版本中我们实现了代码的优化，但是这只是对我们程序员，对于客户来说有可能看不懂这其中的代码
 *         String driver="com.mysql.jdbc.Driver";
 *         String url="jdbc:mysql://localhost:3306/customer1";
 *         String username="root";
 *         String password="MySQL";
 *         这些也只是我们配置自己的数据库用的，如果换成其他的数据库，我们可以考虑将其放置在某个文件之中
 *         让用户在这个文件中进行修改，我们的代码进行读取，这样代码的优化程度更高。
 *         配置到文件中的时候不需要写双引号 也不需要分号 只需要key=value的形式就可以。
 */
public class Customer_util3 {
    private  static DruidDataSource dataSource=new DruidDataSource();
    static {
        Properties properties=new Properties();
        try {
            properties.load(new FileInputStream("./JDBC/src/db.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String driver=properties.getProperty("driver");
        String url=properties.getProperty("url");
        String username=properties.getProperty("username");
        String password=properties.getProperty("password");
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
    }
    public Connection getconnection(){
        try {
           return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void close(Connection connection, PreparedStatement psta, ResultSet rs){
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
