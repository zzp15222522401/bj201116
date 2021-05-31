package com.atguigu.flinkgmall.funs;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.ververica.cdc.debezium.DebeziumDeserializationSchema;
import com.atguigu.flinkgmall.bean.TableProcess;
import io.debezium.data.Envelope;
import org.apache.flink.api.common.typeinfo.BasicTypeInfo;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.util.Collector;
import org.apache.kafka.connect.data.Field;
import org.apache.kafka.connect.data.Struct;
import org.apache.kafka.connect.source.SourceRecord;

import java.util.List;

public class MyDebeziumDeserializationSchema implements DebeziumDeserializationSchema<String> {
    @Override
    public void deserialize(SourceRecord sourceRecord, Collector<String> collector) throws Exception {
        //获取sourceRecourd中的value的值
        Struct value = (Struct) sourceRecord.value();
        //根据value获取数据库和表名和data,以及数据改变类型
        Struct sourceStruct = value.getStruct("source");
        //获取数据库的名字
        String dbname = sourceStruct.getString("db");
        //获取表的名字
        String tablename = sourceStruct.getString("table");
        //获取数据的操作类型
        Envelope.Operation operation = Envelope.operationFor(sourceRecord);
        String aCase = operation.toString().toLowerCase();
        //将操作类型稍作转换
        if ("create".equals(aCase)) {
            aCase="insert";
        }
        //获取after后面的所有属性
        JSONObject datajsonobj = new JSONObject();
        Struct afterstruct = value.getStruct("after");
        //将after内的数据包装成jsonobj
        if(afterstruct!=null){
            List<Field> fields = afterstruct.schema().fields();
            for (Field field : fields) {
                datajsonobj.put(field.name(),afterstruct.get(field));
            }
        }
        //将数据库，表名，列和数据改变类型都包装成一个jsonobj
        JSONObject resultjsonobj = new JSONObject();
        resultjsonobj.put("database",dbname);
        resultjsonobj.put("tablename",tablename);
        resultjsonobj.put("type",aCase);
        resultjsonobj.put("data",datajsonobj);

        collector.collect(resultjsonobj.toJSONString());
    }

    @Override
    public TypeInformation<String> getProducedType() {
        return TypeInformation.of(String.class);
    }
}
