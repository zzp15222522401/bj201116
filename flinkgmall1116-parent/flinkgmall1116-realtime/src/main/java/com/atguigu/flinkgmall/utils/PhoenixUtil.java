package com.atguigu.flinkgmall.utils;

import com.alibaba.fastjson.JSONObject;
import com.atguigu.flinkgmall.common.GmallConfig;
import org.apache.commons.beanutils.BeanUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//使用phoenix工具从hbase中查询表的数据
public class PhoenixUtil {
    private static Connection conn;
    private static void getconnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.apache.phoenix.jdbc.PhoenixDriver");
        conn = DriverManager.getConnection(GmallConfig.PHOENIX_SERVER);
        conn.setSchema(GmallConfig.HBASE_SCHEMA);//设置连接的库

    }

    //查询的数据是多条，所以返回是集合，我们需要查询的sql语句，以及查询结果封装的类
    public static <T>List<T>querydata(String sql,Class<T> clz) {
        PreparedStatement ps = null;
        ResultSet queryresult = null;
        ArrayList<T> list = new ArrayList<>();
            try {
                //获取连接
                if (conn == null) {
                    getconnection();
                }
                //创建数据库操作对象
                ps = conn.prepareStatement(sql);
                //执行sql查询，得到查询结果集
                queryresult = ps.executeQuery();
                //获取结果集的元数据
                ResultSetMetaData metaData = queryresult.getMetaData();
                //循环遍历整个结果集
                while(queryresult.next()){
                    //根据传进来的类反射创建类的对象
                    T obj = clz.newInstance();
                    //可以根据元数据得到字段的个数，即表的字段的长度
                    for (int i = 1; i <= metaData.getColumnCount(); i++) {
                        //根据字段下标获取字段名
                        String name = metaData.getColumnName(i);
                        //在结果集中可以根据字段名查询到相应的数据value
                        Object value = queryresult.getObject(i);
                        //使用beanutils方法可以给类的对象的赋值
                        //类->类的属性(字段名)->属性值(对应字段下的数值)
                        BeanUtils.setProperty(obj,name,value);
                    }
                    //将对象添加到集合中去
                    list.add(obj);
                }

            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("使用phoenix查询数据失败");
            } finally {
                if (queryresult != null) {
                    try {
                        queryresult.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        return list;
    }
   /* //方法测试
    public static void main(String[] args) {
        String sql="select * from DIM_BASE_TRADEMARK";
        List<JSONObject> querydata = querydata(sql, JSONObject.class);
        System.out.println(querydata);
    }*/
}
