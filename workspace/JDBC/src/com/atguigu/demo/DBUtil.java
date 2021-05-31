package com.atguigu.demo;

import com.atguigu.util.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import xiangmu_mysql.Customer;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DBUtil {
    public static void main(String[] args) {//查询单值数据 new ScalarHandler<Object>()
        Connection getconnection = JDBCUtils.getconnection();
        QueryRunner runner=new QueryRunner();
        String str="select pw from customer1 where id=?";
        try {
            Object query = runner.query(getconnection, str, new ScalarHandler<Object>(), 4);
            System.out.println(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void main3(String[] args) {//查询多条数据  new BeanListHandler<Customer>(Customer.class)
        QueryRunner runner=new QueryRunner();
        Connection connection = JDBCUtils.getconnection();
        String str="select * from customer1";
        //ResultSetHandler handler=new BeanListHandler( );

        try {
            List<Customer> query = runner.query(connection, str, new BeanListHandler<Customer>(Customer.class));
            for (Customer customer : query) {
                System.out.println(customer);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public static void main2(String[] args) {//查询结果为一条数据  new BeanHandler<Customer>(Customer.class)
        QueryRunner runner=new QueryRunner();
        Connection get= JDBCUtils.getconnection();
        String str="select * from customer1 where id=?";
        //ResultSetHandler handler=new BeanHandler<Customer>(Customer.class);
        try {
            Customer query = runner.query(get, str,new BeanHandler<Customer>(Customer.class), 3);
            System.out.println(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void main1(String[] args) {
        //利用DButils增删改数据
        QueryRunner runner=new QueryRunner();
        Connection getconnection = JDBCUtils.getconnection();
        String str="insert into customer1 values(null,?,?)";
        try {
            int update = runner.update(getconnection, str, "651651", "asffaf");
            System.out.println(update);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {

            JDBCUtils.close(getconnection,null,null);
        }
    }
}
