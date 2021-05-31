package com.atguigu.gmallpublisher.mapper;

import java.util.List;
import java.util.Map;

public interface DauMapper {
    //查询日活总数的抽样方法
    public Integer selectDauTotal(String date);
    //查询日活分时数据的抽样方法
    public List<Map> selectDauTotalHourMap(String date);
}
