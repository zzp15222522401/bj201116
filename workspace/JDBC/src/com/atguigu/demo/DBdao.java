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

public class DBdao<T> {//最终版数据库的数据操作
    private static QueryRunner runner=new QueryRunner();
    public static void main(String[] args) {
        //查询单值数据
        DBdao dBdao=new DBdao();
        String str="select un from customer1 where id=?";
        Object object = dBdao.object(str, 3);
        System.out.println(object);

        //查询一条数据
        String str1="select * from customer1 where id=?";
        Object getbean = dBdao.getbean(str1, Customer.class, 2);
        System.out.println(getbean);

        //查询多条数据
        String str2="select * from customer1";
        List list = dBdao.getlistbean(str2, Customer.class);
        for (Object o : list) {
            System.out.println(o);
        }

        //对数据的增删改
        String str3="update customer1 set pw=? where id=?";
        boolean update = dBdao.update(str3, 9999999, 3);
        System.out.println(update);
    }
    public  Object object(String str,Object...params){//查询单值数据的方法
        Connection connection = JDBCUtils.getconnection();
        Object query = null;
        try {
            query = runner.query(connection, str, new ScalarHandler<Object>(), params);
            return query;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(connection,null,null);
        }
        return query;
    }
    public T getbean(String str,Class c,Object...params){//查询一条数据
        Connection connection = JDBCUtils.getconnection();
        T query = null;
        try {
            query = runner.query(connection, str, new BeanHandler<T>(c), params);
            return query;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(connection,null,null);
        }
        return null;
    }
    public List getlistbean(String str, Class c,Object...params){
        Connection connection = JDBCUtils.getconnection();
        try {
            List<List> list = runner.query(connection, str, new BeanListHandler<List>(c), params);
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(connection,null,null);
        }
        return null;
    }
    public boolean update(String str,Object...params){//对数据库的增删改
        Connection getconnection = JDBCUtils.getconnection();
        try {
            int update = runner.update(getconnection, str, params);//返回的是影响sql中的行数
            if(update>0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(getconnection,null,null);
        }
        return false;
    }
}
