package com.atguigu.flinkgmall.app.dws;

import com.atguigu.flinkgmall.bean.ProvinceStats;
import com.atguigu.flinkgmall.utils.ClickhouseUtil;
import com.atguigu.flinkgmall.utils.MyKafkaUtil;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;

//使用flinksql编写地区主题代码
public class ProvinceStatsSqlApp {
    public static void main(String[] args) throws Exception {
        //获取流环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(4);
        //获取表环境

        StreamTableEnvironment tableEnv = StreamTableEnvironment.create(env);

        //根据表环境创建动态表
        String topic="dwm_order_wide";
        String groupid="province_stats_group";

        tableEnv.executeSql("CREATE TABLE ORDER_WIDE (province_id BIGINT, " +
                "province_name STRING,province_area_code STRING" +
                ",province_iso_code STRING,province_3166_2_code STRING,order_id STRING, " +
                "split_total_amount DOUBLE,create_time STRING,rowtime AS TO_TIMESTAMP(create_time) ," +
                "WATERMARK FOR  rowtime  AS rowtime)" +
                " WITH (" + MyKafkaUtil.getKafkaDDL(topic,groupid) + ")");//建表语句

        //根据创建的表进行数据查询
        Table table = tableEnv.sqlQuery("select " +
                "DATE_FORMAT(TUMBLE_START(rowtime, INTERVAL '10' SECOND ),'yyyy-MM-dd HH:mm:ss') stt, " +
                "DATE_FORMAT(TUMBLE_END(rowtime, INTERVAL '10' SECOND ),'yyyy-MM-dd HH:mm:ss') edt , " +
                " province_id,province_name,province_area_code area_code," +
                "province_iso_code iso_code ,province_3166_2_code iso_3166_2 ," +
                "COUNT( DISTINCT  order_id) order_count, sum(split_total_amount) order_amount," +
                "UNIX_TIMESTAMP()*1000 ts "+
                "from  ORDER_WIDE group by  TUMBLE(rowtime, INTERVAL '10' SECOND )," +
                " province_id,province_name,province_area_code,province_iso_code,province_3166_2_code ");//查询数据的sql语句
        DataStream<ProvinceStats> provinceStatsDS = tableEnv.toAppendStream(table, ProvinceStats.class);

        //将数据流写入到clickhouse
        provinceStatsDS.print(">>>>>");
        provinceStatsDS.addSink(ClickhouseUtil.getjdbcsink("insert into  province_stats_1116  values(?,?,?,?,?,?,?,?,?,?)"));
        env.execute();
    }
}
