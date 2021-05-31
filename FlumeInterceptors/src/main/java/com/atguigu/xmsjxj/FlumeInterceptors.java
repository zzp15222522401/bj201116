package com.atguigu.xmsjxj;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.List;

/**
 * 此拦截器主要对采集数据的event进行判断，判断是否为完整的josn
 * 如果是就留下，不是就舍弃
 */
public class FlumeInterceptors implements Interceptor {
    private JsonParser parser;
    public void initialize() {//初始化
        parser=new JsonParser();
    }

    public Event intercept(Event event) {
        /**
         * 判断行数据是否能解析成josn
         */
        byte[] body = event.getBody();
        String line = new String(body, StandardCharsets.UTF_8);
        try {
            //判断每一行是否能解析成josn模式，不能的话进入catch
             parser.parse(line);
        }catch (JsonSyntaxException e){
            return null;
        }
        return event;
    }

    public List<Event> intercept(List<Event> events) {
        //删除不符合条件的event
        Iterator<Event> iterator = events.iterator();
        while(iterator.hasNext()){
            Event next = iterator.next();
            if(intercept(next)==null){
                iterator.remove();
            }
        }
        return events;
    }

    public void close() {

    }
    public static class Builder implements Interceptor.Builder{
        public Interceptor build() {
            return new FlumeInterceptors();
        }

        public void configure(Context context) {

        }
    }
}
