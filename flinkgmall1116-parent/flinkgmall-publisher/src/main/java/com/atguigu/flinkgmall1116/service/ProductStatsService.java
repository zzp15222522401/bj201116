package com.atguigu.flinkgmall1116.service;

import com.atguigu.flinkgmall1116.bean.ProductStats;

import java.math.BigDecimal;
import java.util.List;

public interface ProductStatsService {
    BigDecimal getGMV(Integer date);

    List<ProductStats> getTrademark(Integer date,Integer limit);

    List<ProductStats> getCategory(Integer date,Integer limit);

    List<ProductStats> getSpu(Integer date,Integer limit);
}
