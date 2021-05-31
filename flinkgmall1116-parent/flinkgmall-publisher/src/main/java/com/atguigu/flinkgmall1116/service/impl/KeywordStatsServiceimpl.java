package com.atguigu.flinkgmall1116.service.impl;

import com.atguigu.flinkgmall1116.bean.KeywordStats;
import com.atguigu.flinkgmall1116.mapper.KeywordStatsMapper;
import com.atguigu.flinkgmall1116.service.KeywordStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class KeywordStatsServiceimpl implements KeywordStatsService {
    @Autowired
    KeywordStatsMapper keywordStatsMapper;
    @Override
    public List<KeywordStats> getKeywordStats(Integer date, Integer limit) {
        return keywordStatsMapper.selectKeywordStats(date,limit);
    }
}
