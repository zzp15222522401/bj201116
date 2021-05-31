package com.atguigu.flinkgmall.funs;

import com.alibaba.fastjson.JSONObject;
import com.atguigu.flinkgmall.common.GmallConfig;
import org.apache.commons.lang3.StringUtils;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;
import org.apache.phoenix.jdbc.PhoenixDriver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


//侧输出流使用phoenix写到hbase上
public class SideSink extends RichSinkFunction<JSONObject> {
    private Connection connection;
    @Override
    public void open(Configuration parameters) throws Exception {
        super.open(parameters);
        //连接phoenix
        Class.forName("org.apache.phoenix.jdbc.PhoenixDriver");
        connection= DriverManager.getConnection(GmallConfig.PHOENIX_SERVER);
        connection.setAutoCommit(false);
    }

    @Override //在hbase上建表，这里主要拼写数据写入sql语句
    public void invoke(JSONObject jsonobj, Context context) throws Exception {
        //获取表名，phoenix上尽量使用大写,表名的获取和Tableprocessfun有关
        String tablename = jsonobj.getString("sink_table").toUpperCase();
        //获取data数据（字段名个数值）
        JSONObject datajsonobj = jsonobj.getJSONObject("data");
        if(datajsonobj!=null&&datajsonobj.size()>0){
            String insertSQL=insertSQL(tablename,datajsonobj);//数据导入sql语句
            PreparedStatement preparedStatement=null;

            try {
                preparedStatement = connection.prepareStatement(insertSQL);
                preparedStatement.execute();
                connection.commit();//手动提交事务
                System.out.println("插入的sql语句："+insertSQL);
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if(preparedStatement!=null){
                    preparedStatement.close();
                }
            }

        }



    }

    //拼接phoenix写如hbase的sql语句
    private String insertSQL(String tablename, JSONObject datajsonobj) {
        String insert=" upsert into "+GmallConfig.HBASE_SCHEMA+"."+tablename
                +" ("+ StringUtils.join(datajsonobj.keySet(),",")+") "
                +"values(\'"+StringUtils.join(datajsonobj.values(),"\',\'")+"\')";
        return insert;
    }
}
