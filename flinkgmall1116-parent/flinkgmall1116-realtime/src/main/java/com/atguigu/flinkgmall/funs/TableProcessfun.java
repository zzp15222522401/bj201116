package com.atguigu.flinkgmall.funs;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.atguigu.flinkgmall.bean.TableProcess;
import com.atguigu.flinkgmall.common.GmallConfig;
import org.apache.flink.api.common.state.BroadcastState;
import org.apache.flink.api.common.state.MapStateDescriptor;
import org.apache.flink.api.common.state.ReadOnlyBroadcastState;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.co.BroadcastProcessFunction;
import org.apache.flink.util.Collector;
import org.apache.flink.util.OutputTag;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;


public class TableProcessfun extends BroadcastProcessFunction<JSONObject, String, JSONObject> {
    private OutputTag<JSONObject>cdctag;
    private MapStateDescriptor<String,TableProcess> mapStateDescriptor;
    private Connection connection;
    public TableProcessfun(OutputTag<JSONObject> cdctag, MapStateDescriptor mapStateDescriptor) {
        this.cdctag = cdctag;
        this.mapStateDescriptor = mapStateDescriptor;
    }

    @Override
    public void open(Configuration parameters) throws Exception {
        super.open(parameters);
        //获取phoenix的连接
        Class.forName("org.apache.phoenix.jdbc.PhoenixDriver");
        connection = DriverManager.getConnection(GmallConfig.PHOENIX_SERVER);
        connection.setAutoCommit(false);
        //Can't call commit when autocommit=true默认数据库的事务是自动回滚的，我们这里需要改为手动
    }

    @Override
    //对主流数据进行处理
    //根据主流的表名和操作类型 和配置表中的数据做对比 然后分流输出
public void processElement(JSONObject value, ReadOnlyContext ctx, Collector<JSONObject> out) throws Exception {
        //获取广播状态
        ReadOnlyBroadcastState<String,TableProcess> tableprocessState = ctx.getBroadcastState(mapStateDescriptor);
        //获取主流的数据（表名和type）
        String tablename = value.getString("table");
        String type = value.getString("type");
        //将历史数据做修改
        if("bootstrap-insert".equals(type)){
            type="insert";
            value.put("type",type);
        }
        String key=tablename+":"+type;
        //根据key在广播状态中寻找对应的数据
        TableProcess tableProcess = tableprocessState.get(key);
        //如果不为空，那就是找到对应数据
        if(tableProcess!=null){
            //查看对应输出类型是kafka还是hbase
            String sinkType = tableProcess.getSinkType();
            String sinkTable = tableProcess.getSinkTable();
            value.put("sink_table",sinkTable);
            JSONObject datajsonobj = value.getJSONObject("data");
            if(tableProcess.getSinkColumns()!=null&&tableProcess.getSinkColumns().length()>0){
                //对db数据库的数据做筛选，并不是所有的数据我们都需要，可以根据配置表做需求，包含字段的筛选出来，不含的过滤掉
                filtercolumns(datajsonobj,tableProcess.getSinkColumns());
            }
            //输出类型是hbase，那么就输出到侧输出流
            if(sinkType.equals(TableProcess.SINK_TYPE_HBASE)){
                ctx.output(cdctag,value);
                //实时数据发送到kafka
            }else if(sinkType.equals(TableProcess.SINK_TYPE_KAFKA)){
                out.collect(value);
            }
        }else{
            System.out.println("没有在配置表中找得到对应"+key+"的数据");
        }

    }
    //对数据做筛选
    private void filtercolumns(JSONObject datajsonobj, String sinkColumns) {
        String[] split = sinkColumns.split(",");
        List<String> lists = Arrays.asList(split);
        //将数据格式转换，方便获取数据的属性和value
        Set<Map.Entry<String, Object>> entries = datajsonobj.entrySet();
        //对所有的字段进行遍历
        Iterator<Map.Entry<String, Object>> entryIterator = entries.iterator();
        for(;entryIterator.hasNext();){
            Map.Entry<String, Object> next = entryIterator.next();
            //如果配置表中没有对应的字段和数据，那就把data中的这个数据过滤掉
            if(!lists.contains(next.getKey())){
                entryIterator.remove();
            }
        }
    }

    @Override
//对广播流数据进行处理
public void processBroadcastElement(String value, Context ctx, Collector<JSONObject> out) throws Exception {
    //将读取到FlinkCDC采集到的信息  由String->jsonObj
    // {"database":"gmall1116","table":"comment_info","type":"insert","data":{}}
    JSONObject parseObject = JSON.parseObject(value);
    String data = parseObject.getString("data");
    TableProcess tableobj = JSON.parseObject(data, TableProcess.class);
    //获取数据的操作类型
    String operateType = tableobj.getOperateType();
    //获取数据的变化字段
    String sinkColumns = tableobj.getSinkColumns();
    //获取数据的扩展信息
    String extend = tableobj.getSinkExtend();
    //获取数据的主键信息
    String pk = tableobj.getSinkPk();
    //获取数据的输出类型  kafka 或者hbase
    String type = tableobj.getSinkType();
    //获取输出的表名
    String tablename = tableobj.getSinkTable();
    //获取数据的来源表
    String sourceTable = tableobj.getSourceTable();

    //创建key
    String key=sourceTable+":"+operateType;
    //如果数据的输出类型是hbase那么我们就需要在hbase上建立相应表 updata和delete就不创建了
    if(type.equals(TableProcess.SINK_TYPE_HBASE)&&"insert".equals(operateType)){
        //建表需要的表名，字段，主键，拓展信息
        createtable(tablename,sinkColumns,pk,extend);
    }
    //获取广播状态
    BroadcastState<String,TableProcess> tableprocessState = ctx.getBroadcastState(mapStateDescriptor);
    //定义广播状态类型（key->表名+操作类型  value->数据）
        tableprocessState.put(key,tableobj);
}

    private void createtable(String tablename, String sinkColumns, String pk, String extend)  {
        if(pk==null){
            pk="id";//主键为空时，默认把id作为主键，在这之前我们已经确认所有的表中都有id列
        }
        if(extend==null){//拓展信息为空时，默认"";
            extend="";
        }
        StringBuilder createtbl=new StringBuilder();
        //建表语句
        createtbl.append(" create table if not exists "+GmallConfig.HBASE_SCHEMA+"."+tablename+" ( ");
        String[] splits = sinkColumns.split(",");
        for (int i = 0; i < splits.length; i++) {
            String column = splits[i];
            if(pk.equals(column)){
                createtbl.append(column+" varchar primary key ");
            }else{
                createtbl.append("info."+column+" varchar ");
            }if(i<splits.length-1){
                createtbl.append(",");
            }
        }
        createtbl.append(" ) "+extend);

        System.out.println("建表语句："+createtbl.toString());

        PreparedStatement preparedStatement=null;
        try {
            //获取phoenix操作数据库对象
            preparedStatement = connection.prepareStatement(createtbl.toString());
            //执行建表语句
            preparedStatement.execute();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //释放资源
            if(preparedStatement!=null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }


    }

}
