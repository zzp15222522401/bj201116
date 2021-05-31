package com.atguigu.flinkgmall1116.service;

import com.atguigu.flinkgmall1116.bean.KeywordStats;

import java.util.List;

public interface KeywordStatsService {
    List<KeywordStats> getKeywordStats(Integer date, Integer limit);
}
