package com.atguigu.flinkgmall.utils;


import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class KeywordUtil {
    //分词后的结果集不确定，所以是list
    public static List<String> analyze(String text){
        Reader reader = new StringReader(text);
        //使用ik分词器，将每一条数据进行分词
        IKSegmenter ikSegmenter = new IKSegmenter(reader,true);
        Lexeme lexeme=null;
        ArrayList<String> list = new ArrayList<>();
        try {
           while((lexeme=ikSegmenter.next())!=null){
               //如果分词后有数据，获取分词后的每一个数据
               String lexemeText = lexeme.getLexemeText();
               list.add(lexemeText);
           }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return  list;
    }
    //测试自定义分词器的效果
//    public static void main(String[] args) {
//        String text = "Apple iPhoneXSMax (A2104) 256GB 深空灰色 移动联通电信4G手机 双卡双待";
//        System.out.println(KeywordUtil.analyze(text));
//    }

}
