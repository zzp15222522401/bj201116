package com.atguigu.flinkgmall.funs;

import com.atguigu.flinkgmall.utils.KeywordUtil;
import org.apache.flink.table.annotation.DataTypeHint;
import org.apache.flink.table.annotation.FunctionHint;
import org.apache.flink.table.functions.TableFunction;
import org.apache.flink.types.Row;

import java.util.List;

/**
 * Author: Felix
 * Desc: 自定义UDTF函数实现分词功能
 */
@FunctionHint(output = @DataTypeHint("ROW<s STRING>"))
//自定义函数（功能，将文本数据拆分成单词）
public class KeywordUDTF  extends TableFunction<Row> {
    public void eval(String value){
        //根据定义的分词器获取分词后的集合
        List<String> analyze = KeywordUtil.analyze(value);
        //遍历获取每一个词
        for (String s : analyze) {
            //将每一个词转换成ROW 类型向下游输出
            collect(Row.of(s));
        }
    }
}
