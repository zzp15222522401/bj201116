package com.atguigu.flinkgmall.funs;

import com.alibaba.fastjson.JSONObject;

public interface DimJoinFunction<T> {
    void join(T input, JSONObject jsonobj);

    String getkey(T input);
}
