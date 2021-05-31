package com.atguigu.flinkgmall.app.dws;

import com.atguigu.flinkgmall.bean.GmallConstant;
import com.atguigu.flinkgmall.bean.KeywordStats;
import com.atguigu.flinkgmall.funs.KeywordUDTF;
import com.atguigu.flinkgmall.utils.ClickhouseUtil;
import com.atguigu.flinkgmall.utils.MyKafkaUtil;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;

/**
 * flinksql编写关键词api
 */
public class KeywordStatsApp {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(4);

        //检查点
        //创建表环境
        StreamTableEnvironment tableEnv = StreamTableEnvironment.create(env);
        //创建临时表
        String topic="dwd_page_log";
        String groupid="keyword_group";
        tableEnv.executeSql("CREATE TABLE page_view(" +
                "    common MAP<STRING,STRING>," +
                "    page MAP<STRING,STRING>," +
                "    ts BIGINT," +
                "    row_time AS TO_TIMESTAMP(FROM_UNIXTIME(ts/1000))," +
                "    WATERMARK FOR row_time AS row_time - INTERVAL '5' SECOND " +
                ") WITH (" + MyKafkaUtil.getKafkaDDL(topic,groupid)+ ")");

        //注册函数
        tableEnv.createTemporaryFunction("ik_analyze", KeywordUDTF.class);
        //
        Table temptable = tableEnv.sqlQuery("select page['item'] fullword ," +
                "row_time from page_view " +
                "where page['page_id']='good_list' "+
                "and page['item'] IS NOT NULL ");

        //
        Table tableView = tableEnv.sqlQuery("select keyword,row_time from " + temptable + "," + " LATERAL TABLE(ik_analyze(fullword)) as T(keyword)");
        //
        Table table = tableEnv.sqlQuery("select keyword,count(*) ct, '"
                + GmallConstant.KEYWORD_SEARCH + "' source ," +
                "DATE_FORMAT(TUMBLE_START(row_time, INTERVAL '10' SECOND),'yyyy-MM-dd HH:mm:ss') stt," +
                "DATE_FORMAT(TUMBLE_END(row_time, INTERVAL '10' SECOND),'yyyy-MM-dd HH:mm:ss') edt," +
                "UNIX_TIMESTAMP()*1000 ts from   " + tableView
                + " GROUP BY TUMBLE(row_time, INTERVAL '10' SECOND ),keyword");
        //将表转换成流
        DataStream<KeywordStats> keywordStatsDS = tableEnv.toAppendStream(table, KeywordStats.class);
        keywordStatsDS.print(">>>>>");

        //写入到clickhouse
        keywordStatsDS.addSink(ClickhouseUtil.getjdbcsink("insert into keyword_stats_1116(keyword,ct,source,stt,edt,ts)" +
                " values(?,?,?,?,?,?)"));
        env.execute();
    }
}
