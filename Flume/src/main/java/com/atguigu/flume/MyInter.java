package com.atguigu.flume;

import org.apache.flume.ChannelSelector;
import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

//拦截器 首字母是字母分一个  数字分一个 其他舍弃
public class MyInter implements Interceptor {
    private List<Event> events;

    @Override
    public void initialize() {

    }

    @Override
    public Event intercept(Event event) {
        byte[] body = event.getBody();
        Map<String, String> headers = event.getHeaders();
        String s = new String(body, StandardCharsets.UTF_8);
        char charAt = s.charAt(0);
        if(charAt>='a'&&charAt<='z'||charAt>='A'&&charAt<='Z'){
            headers.put("type","zifu");
        }else if(charAt>='0'&&charAt<='9'){
            headers.put("type","zimu");
        }else{
            return null;
        }

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
            return new MyInter();
        }

        @Override
        public void configure(Context context) {

        }
    }
}
