package com.atguigu.gmallpublisher.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.atguigu.gmallpublisher.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@RestController
public class Controller {
    @Autowired private PublisherService publisherService;
    @RequestMapping("realtime-total")
    public String realtimeTotal(@RequestParam("date")String date){
        //创建list和map用来存储结果数据
        ArrayList<Map> result = new ArrayList<>();
        //从service中获取数据
        Integer dauTotal = publisherService.getDauTotal(date);
        Double gmvTotal = publisherService.getGmvTotal(date);

        //将数据封装到结果集合中
        //将新增日活数据放到Map中
        HashMap<String, Object> dauMap = new HashMap<>();
        dauMap.put("id","dau");
        dauMap.put("name","新增日活");
        dauMap.put("value",dauTotal);
        //将新增设备数据放到Map中
        HashMap<String, Object> devMap = new HashMap<>();
        devMap.put("id","new_id");
        devMap.put("name","新增设备");
        devMap.put("value",233);

        HashMap<String, Object> gmvMap = new HashMap<>();
        gmvMap.put("id","order_amount");
        gmvMap.put("name","新增交易额");
        gmvMap.put("value",gmvTotal);
        //将map集合放到list集合中
        result.add(dauMap);
        result.add(devMap);
        result.add(gmvMap);
        //返回
        //将list集合转换成JSON字符串返回
        return JSONObject.toJSONString(result);
    }
    @RequestMapping("realtime-hours")
    public String realtimeHours(@RequestParam("id")String id,@RequestParam("date") String date){

        Map todayMap;
        Map yesterdayMap;
        String yesterday = LocalDate.parse(date).plusDays(-1).toString();
        if("dau".equals(id)){
            //获取昨天和今天的数据
            todayMap = publisherService.getDauHour(date);
            yesterdayMap = publisherService.getDauHour(yesterday);
        }else{
            todayMap= publisherService.getGmvHour(date);
            yesterdayMap = publisherService.getGmvHour(yesterday);
        }

        //创建map存放结果数据
        HashMap<String, Map> result = new HashMap<>();

        //往结果集合中封装数据
        result.put("yesterday",yesterdayMap);
        result.put("today",todayMap);

        return JSONObject.toJSONString(result);
    }
    @RequestMapping("sale_detail")
    public String getSaleDetail(
            @RequestParam("date") String date,
            @RequestParam("startpage") Integer start,
            @RequestParam("size") Integer size,
            @RequestParam("keyword") String keyword
    ) throws IOException {
        return publisherService.getSaleDetail(date, start, size, keyword);
    }
}
