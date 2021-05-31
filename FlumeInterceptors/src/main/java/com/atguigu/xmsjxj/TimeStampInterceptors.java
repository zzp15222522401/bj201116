package com.atguigu.xmsjxj;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * 此拦截器为event添加时间戳
 * kafka-->HDFS 按照数据产生时间分文件夹
 */
public class TimeStampInterceptors implements Interceptor {
    private JsonParser parser;

    @Override
    public void initialize() {
        parser=new JsonParser();
    }

    @Override
    public Event intercept(Event event) {
        String line = new String(event.getBody(), StandardCharsets.UTF_8);
        JsonElement parse = parser.parse(line);
        //从这个json元素中获取时间戳
        JsonObject asJsonObject = parse.getAsJsonObject();
        long timestamp = asJsonObject.get("ts").getAsLong();
        //应该判断是否能获取到json类型的对象，不过我们之前已经全部把数据弄成json了  所以这里就不用判断了
        event.getHeaders().put("timestamp", Long.toString(timestamp));
        return event;
    }

    @Override
    public List<Event> intercept(List<Event> events) {
        for (Event event : events) {
            intercept(event);
        }
        return events;
    }

    @Override
    public void close() {

    }
    public static class Builder implements Interceptor.Builder{
        @Override
        public Interceptor build() {
            return new TimeStampInterceptors();
        }

        @Override
        public void configure(Context context) {

        }
    }
}
