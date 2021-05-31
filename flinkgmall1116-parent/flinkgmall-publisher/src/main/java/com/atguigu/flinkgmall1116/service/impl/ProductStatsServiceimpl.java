package com.atguigu.flinkgmall1116.service.impl;

import com.atguigu.flinkgmall1116.bean.ProductStats;
import com.atguigu.flinkgmall1116.mapper.ProductStatsMapper;
import com.atguigu.flinkgmall1116.service.ProductStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductStatsServiceimpl implements ProductStatsService {
    @Autowired
    ProductStatsMapper productStatsMapper;

    @Override
    public BigDecimal getGMV(Integer date) {
        return productStatsMapper.selectGMV(date);
    }

    @Override
    public List<ProductStats> getTrademark(Integer date, Integer limit) {
        return productStatsMapper.selectTrademark(date,limit);
    }

    @Override
    public List<ProductStats> getCategory(Integer date, Integer limit) {
        return productStatsMapper.selectCategory(date,limit);
    }

    @Override
    public List<ProductStats> getSpu(Integer date, Integer limit) {
        return productStatsMapper.selectSpu(date,limit);
    }
}
