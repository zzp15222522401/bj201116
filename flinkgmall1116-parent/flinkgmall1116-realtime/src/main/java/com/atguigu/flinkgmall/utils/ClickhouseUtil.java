package com.atguigu.flinkgmall.utils;

import com.atguigu.flinkgmall.bean.TransientSink;
import com.atguigu.flinkgmall.common.GmallConfig;
import lombok.Builder;
import lombok.val;
import org.apache.flink.connector.jdbc.JdbcStatementBuilder;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;
import org.apache.flink.connector.jdbc.JdbcConnectionOptions;
import org.apache.flink.connector.jdbc.JdbcExecutionOptions;
import org.apache.flink.connector.jdbc.JdbcSink;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.SQLException;


 /* 定义操作clickhouse工具类

  jdbcsink方法中的参数表示：
 /** @param  sql             sql语句
 * @param  statementBuilder  sql语句中的的字段和字段对应的value是什么
 * @param  <T>               //statementBuilder中accept方法的数据类型
 * @param  executionOptions     //批的参数，例如批的大小和批的重试次数
 * @param  connectionOptions    //连接的参数 JDBC URL
 */



public class ClickhouseUtil {
    //TODO 使用flink的jdbcsink向对应表写入
    public static <T>SinkFunction<T> getjdbcsink(String sql){
        SinkFunction<T> sink = JdbcSink.<T>sink(
                sql,
                new JdbcStatementBuilder<T>() {
                    @Override
                    public void accept(PreparedStatement preparedStatement, T obj) throws SQLException {
                        //在这里写操作clickhouse的主代码
                        //                        //insert into *** values(?,?,?,?,?,?,?),怎么知道向哪个表中插入数据，插入的value又怎么一一对应
                        //                        //根据反射获取对象的所有属性
                        int TransientSinknum=0;
                        Field[] Fields = obj.getClass().getDeclaredFields(); //反射创建类的对象，获取所有属性的对象
                        for (int i = 0; i < Fields.length; i++) {
                            Field field = Fields[i];//获取每个属性的对象
                            field.setAccessible(true);//破解原属性，原属性是私有的也可以在这里修改访问

                            //如果原数据中有我们不需要插入的某个属性，我们为其标记TransientSink  然后跳过这个属性
                            //field.getAnnotation(TransientSink.class) 获取这个属性的TransientSink注解
                            if(field.getAnnotation(TransientSink.class)!=null){
                            //说明这个属性被标记为TransientSink，我们不需要插入这个数据
                                TransientSinknum++;
                                continue;


                            }
                            try {
                                //获取属性对应的值
                                Object value = field.get(obj);
                                //给表中对应的列插入值,插入数据时从1开始，对应位置需要减去不插入的数量
                                preparedStatement.setObject(i+1-TransientSinknum,value);
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                },
                new JdbcExecutionOptions.Builder().withBatchSize(5).build(),
                new JdbcConnectionOptions.JdbcConnectionOptionsBuilder()
                    .withDriverName("ru.yandex.clickhouse.ClickHouseDriver")
                    .withUrl(GmallConfig.CLICKHOUSE_URL)
                    .build()
        );
        return sink;
    }
}
