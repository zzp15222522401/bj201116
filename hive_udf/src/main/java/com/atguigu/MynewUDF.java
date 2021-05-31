package com.atguigu;

import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.exec.UDFArgumentLengthException;
import org.apache.hadoop.hive.ql.exec.UDFArgumentTypeException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDF;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.PrimitiveObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;

import java.util.Arrays;


/**
 * 新的UDF类
 */
public class MynewUDF extends GenericUDF {
    /**
     * 将一个字符串的前三个字符换成zzp
     * 检验输入的数据类型和输入的参数个数
     * @param arguments 输入输入的元数据
     * @return 输出结果的元数据
     * @throws UDFArgumentException
     */
    public ObjectInspector initialize(ObjectInspector[] arguments) throws UDFArgumentException {
        if(arguments.length!=1){
            throw new UDFArgumentLengthException("参数的个数必须是一个");
        }
        else if (!arguments[0].getTypeName().equals("string")){
            throw new UDFArgumentTypeException(0,"数据类型必须是String类型 ");
        }
        //用工厂模式返回String类型的数据
        return PrimitiveObjectInspectorFactory.javaStringObjectInspector;
    }

    public Object evaluate(DeferredObject[] arguments) throws HiveException {
        //因为DeferredObject是懒加载机制，所以我们需要先获取，让它先进行加载
        DeferredObject argument = arguments[0];
        Object o = argument.get();
        String input = o.toString();
        if(arguments.length==0){
            return "";
        }else if(arguments==null){
            return null;
        }else
        return "zzp"+input.substring(1,3);
    }

    public String getDisplayString(String[] children) {
        return Arrays.toString(children);//直接返回就行，没什么关系
    }
}
