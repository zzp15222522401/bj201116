package com.atguigu;


import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.exec.UDFArgumentLengthException;
import org.apache.hadoop.hive.ql.exec.UDFArgumentTypeException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDF;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;

import java.util.Arrays;

public class NewUDF extends GenericUDF {
    /**
     * 检测输入参数的类型和参数个数
     * @param arguments
     * @return
     * @throws UDFArgumentException
     */
    public ObjectInspector initialize(ObjectInspector[] arguments) throws UDFArgumentException {
        if(!arguments[0].getTypeName().equals("string")){
            throw new UDFArgumentTypeException(0,"参数的类型不匹配");
        }else if(arguments.length!=1){
            throw new UDFArgumentLengthException("参数个数只能是一个");
        }else
        return PrimitiveObjectInspectorFactory.javaStringObjectInspector;
    }

    /**
     * 将字符串的前三个字符替换成zzp
     * 自定义函数的方法，逻辑结构
     * @param arguments
     * @return
     * @throws HiveException
     */
    public Object evaluate(DeferredObject[] arguments) throws HiveException {
        //DeferredObject是懒加载，所以我们在使用其中的数据时需要提前把它加载
        DeferredObject argument = arguments[0];
        Object o = argument.get();
        if(arguments==null){
            return null;
        }else if(arguments.length==0){
            return "";
        }else
        return "zzp"+o.toString().substring(3);
    }

    public String getDisplayString(String[] children) {
        return Arrays.toString(children);
    }
}
