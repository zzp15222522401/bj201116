package com.atguigu.flinkgmall.utils;


import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

//TODO 自定义时间工具类  使用DateTimeFormatter不会出现线程安全问题
//使用new SimpleDateFormat这种格式会有线程安全为题
public class DateTimeUtil {
    private static DateTimeFormatter dtf=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static long ToTS(String datestr){
        LocalDateTime localDateTime = LocalDateTime.parse(datestr, dtf);
        Instant instant = localDateTime.toInstant(ZoneOffset.of("+8"));
        return instant.toEpochMilli();
    }
    public static String toYMDHms(Date date){
        LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        String format = localDateTime.format(dtf);
        return format;
    }
}
