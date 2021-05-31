package com.atguigu.flinkgmall1116.service.impl;

import com.atguigu.flinkgmall1116.bean.ProvinceStats;
import com.atguigu.flinkgmall1116.mapper.ProvinceStatsMapper;
import com.atguigu.flinkgmall1116.service.ProvinceStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProvinceStatsServiceimpl implements ProvinceStatsService {
    @Autowired
    ProvinceStatsMapper provinceStatsMapper;
    @Override
    public List<ProvinceStats> getProvinceStats(Integer date) {
        return provinceStatsMapper.selectProvinceStats(date);
    }
}
