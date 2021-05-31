package com.atguigu.flinkgmall1116.mapper;

import com.atguigu.flinkgmall1116.bean.VisitorStats;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface VisitorStatsMapper {
    @Select("select is_new,sum(uv_ct) uv_ct,sum(pv_ct) pv_ct,sum(sv_ct) sv_ct,sum(uj_ct) uj_ct,sum(dur_sum) dur_sum from visitor_stats_1116 where toYYYYMMDD(stt)=#{date} group by is_new")

    List<VisitorStats> selectVisitorIsNew(Integer date);

    //访客分时统计查询
    @Select("select sum(if(is_new='1', visitor_stats_1116.uv_ct,0)) new_uv,toHour(stt) hr,sum(uv_ct) uv_ct, sum(pv_ct) pv_ct, sum(uj_ct) uj_ct  from visitor_stats_1116 where toYYYYMMDD(stt)=#{date} group by toHour(stt)")
    List<VisitorStats> selectVisitorStatsByHour(Integer date);
}
