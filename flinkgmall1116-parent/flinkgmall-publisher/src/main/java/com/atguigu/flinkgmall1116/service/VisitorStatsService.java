package com.atguigu.flinkgmall1116.service;

import com.atguigu.flinkgmall1116.bean.VisitorStats;

import java.util.List;

public interface VisitorStatsService {
    List<VisitorStats> getVisitorIsNew(Integer date);

    List<VisitorStats> getVisitorStatsByHour(Integer date);
}
