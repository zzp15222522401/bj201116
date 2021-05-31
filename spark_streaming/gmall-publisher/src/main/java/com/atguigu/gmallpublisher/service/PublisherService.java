package com.atguigu.gmallpublisher.service;

import com.atguigu.gmallpublisher.bean.Stat;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface PublisherService {
    //日活数据总数接口=>service
    public Integer getDauTotal(String date);


    //日活分时数据抽样方法
    public Map<String,Long> getDauHour(String date);


    //
    public Double getGmvTotal(String date);

    //
    public Map<String,Double> getGmvHour(String date);

    //灵活分析抽象方法
    public String getSaleDetail(String date, Integer start, Integer size, String keyword) throws IOException;
}
