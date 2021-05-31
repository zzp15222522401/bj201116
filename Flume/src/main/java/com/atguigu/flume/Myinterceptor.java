package com.atguigu.flume;

import org.apache.flume.Channel;
import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

/**
 * 自定义拦截器使得flume读取的数据科举需求进行不同的chanenal
 * 拦截器的功能是把take到的数据中含有zzp的分为一类，不含有的分为一类
 */


public class Myinterceptor implements Interceptor {
    //初始化我们的
    public void initialize() {

    }

    public Event intercept(Event event) {
        byte[] body = event.getBody();
        Map<String, String> headers = event.getHeaders();
        String line = new String(body, StandardCharsets.UTF_8);
        //把数据进行utf-8编码成字符串
        if(line.contains("zzp")){
            headers.put("datatype","hive");
        }else{
            headers.put("datatype","nohive");
        }
        return event;
    }
    //多个event，遍历循环每个进行上述的方法
    public List<Event> intercept(List<Event> list) {
        for (Event event : list) {
            intercept(event);
        }
        return list;
    }

    public void close() {

    }
    //
    public static class Builder implements Interceptor.Builder{
        public Interceptor build() {
            return new Myinterceptor();
        }
        //定义配置文件
        public void configure(Context context) {

        }
    }
}
