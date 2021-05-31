package com.atguigu.flinkgmall1116.mapper;

import com.atguigu.flinkgmall1116.bean.KeywordStats;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface KeywordStatsMapper {
    @Select("select keyword,sum(keyword_stats_1116.ct * multiIf(source='SEARCH',10,source='ORDER',3,source='CART',2,source='CLICK',1,0)) ct from keyword_stats_1116 where toYYYYMMDD(stt)=#{date} group by keyword order by sum(keyword_stats_1116.ct) desc limit #{limit}")
    List<KeywordStats> selectKeywordStats(@Param("date") Integer date, @Param("limit") Integer limit);
}
