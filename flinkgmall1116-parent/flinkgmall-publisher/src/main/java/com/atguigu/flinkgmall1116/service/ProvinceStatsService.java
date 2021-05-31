package com.atguigu.flinkgmall1116.service;

import com.atguigu.flinkgmall1116.bean.ProvinceStats;

import java.util.List;

public interface ProvinceStatsService {

    List<ProvinceStats> getProvinceStats(Integer date);
}
