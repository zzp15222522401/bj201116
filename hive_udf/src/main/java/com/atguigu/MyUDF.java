package com.atguigu;

import org.apache.hadoop.hive.ql.exec.UDF;


/**
 * 自定义函数 将字符串前3个字符变成zzp
 */
public class MyUDF extends UDF {
    public String evaluate(String i){
        if(i==null){
            return null;
        }else if(i.length()==0)
            return "";
        else
            return "zzp"+i.substring(1,3);

    }
}
