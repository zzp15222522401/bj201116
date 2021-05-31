package com.atguigu.flinkgmall1116.service.impl;

import com.atguigu.flinkgmall1116.bean.VisitorStats;
import com.atguigu.flinkgmall1116.mapper.VisitorStatsMapper;
import com.atguigu.flinkgmall1116.service.VisitorStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class VisitorStatsServiceimpl implements VisitorStatsService {
    @Autowired
    VisitorStatsMapper visitorStatsMapper;


    @Override
    public List<VisitorStats> getVisitorIsNew(Integer date) {
        return visitorStatsMapper.selectVisitorIsNew(date);
    }

    @Override
    public List<VisitorStats> getVisitorStatsByHour(Integer date) {
        return visitorStatsMapper.selectVisitorStatsByHour(date);
    }
}
