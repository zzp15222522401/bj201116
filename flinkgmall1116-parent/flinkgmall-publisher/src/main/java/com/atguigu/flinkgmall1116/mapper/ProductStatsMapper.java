package com.atguigu.flinkgmall1116.mapper;


import com.atguigu.flinkgmall1116.bean.ProductStats;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

public interface ProductStatsMapper {
    //从clickhouse获取商品交易总额数据
    @Select("select sum(order_amount) order_amount from product_stats_1116 where toYYYYMMDD(edt)=#{date}")
     BigDecimal selectGMV(Integer date);

    //品牌的销售总额
    @Select("select tm_id,tm_name,sum(order_amount) order_amount from product_stats_1116 where toYYYYMMDD(stt)=#{date} group by tm_id,tm_name order by order_amount desc limit #{limit}")
    List<ProductStats> selectTrademark(@Param("date") Integer date, @Param("limit") Integer limit);

    //品类的销售总额
    @Select("select category3_id,category3_name,sum(order_amount) order_amount from product_stats_1116 where toYYYYMMDD(stt)=#{date} group by category3_id,category3_name order by order_amount desc limit #{limit}")
    List<ProductStats> selectCategory(@Param("date") Integer date,@Param("limit") Integer limit);

    //商品排行
    @Select("select spu_id,spu_name,sum(order_amount) order_amount,sum(order_ct) order_ct from product_stats_1116 where toYYYYMMDD(stt)=#{date} group by spu_id,spu_name order by order_amount desc limit #{limit};")
    List<ProductStats> selectSpu(@Param("date")Integer date,@Param("limit")Integer limit);
}
