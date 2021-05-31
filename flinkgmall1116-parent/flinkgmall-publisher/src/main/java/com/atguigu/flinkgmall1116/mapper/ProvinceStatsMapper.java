package com.atguigu.flinkgmall1116.mapper;

import com.atguigu.flinkgmall1116.bean.ProvinceStats;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ProvinceStatsMapper {
    @Select("select province_id,province_name,sum(order_amount) order_amount from province_stats_1116 where toYYYYMMDD(stt)=#{date} group by province_id,province_name order by order_amount desc")
    List<ProvinceStats> selectProvinceStats(Integer date);
}
