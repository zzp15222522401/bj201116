package com.atguigu.util;

import com.alibaba.druid.pool.DruidDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 使用数据库连接池
 *          数据库连接池 ： 容器放的是数据库连接
 *  *      是一个第三方的产品   c3p0   dbcp  dbcp2
 *  *          导包：druid-1.1.10    阿里下的一个产品
 *  *     a. 创建数据库连接池的核心模板类对象
 *  *          DruidDataSource dataSource=new DruidDataSource();
 *  *     b. 设置参数
 *  *             dataSource.setDriverClassName(driver);
 *  *             dataSource.setUrl(url);
 *  *             dataSource.setUsername(username);
 *  *             dataSource.setPassword(password);
 *  *     c. 获得数据库连接
 *  *          Connection connection=dataSource.getConnection();
 */
public class Customer_util2 {
    private static DruidDataSource dataSource=new DruidDataSource();
    //在上一版本中，我们每次获得数据库时都会进行驱动的加载以及数据库的寻找操作，这个过程也很浪费时间，
    // 所以我们可以将其设置在静态代码块中（指挥在类加载的时候加载一次，之后就不会在加载），使得每次调用方法时不会在继续加载
    //减少时间
    static {
        //下个版本可以将其放入在文件中
        String driver="com.mysql.jdbc.Driver";
        String url="jdbc:mysql://localhost:3306/customer1";
        String username="root";
        String password="MySQL";
        dataSource.setPassword(driver);
        dataSource.setUsername(url);
        dataSource.setUrl(username);
        dataSource.setDriverClassName(password);
    }
    public Connection getconnection(){//我们已经将各种参数都设置完毕，只需要接收数据库就可以了
        //druid 当中有代码实现了 这其中的过程
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
