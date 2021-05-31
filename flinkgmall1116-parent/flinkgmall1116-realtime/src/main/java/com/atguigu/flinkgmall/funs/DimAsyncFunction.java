package com.atguigu.flinkgmall.funs;

import com.alibaba.fastjson.JSONObject;
import com.atguigu.flinkgmall.utils.DimUtil;
import com.atguigu.flinkgmall.utils.ThreadPoolUtil;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.async.ResultFuture;
import org.apache.flink.streaming.api.functions.async.RichAsyncFunction;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

//异步维度关联
public abstract class DimAsyncFunction<T> extends RichAsyncFunction<T,T> implements DimJoinFunction<T> {
    private ExecutorService executorService;
    private String tablename;

    public DimAsyncFunction(String tablename) {
        this.tablename = tablename;
    }

    @Override
    public void open(Configuration parameters) throws Exception {
        super.open(parameters);
        //获取线程池对象
        executorService = ThreadPoolUtil.getInstance();
    }

    @Override//发送异步请求
    public void asyncInvoke(T input, ResultFuture<T> resultFuture) throws Exception {
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                //获取维度表的主键，没有方法就自定义方法
                String key=getkey(input);
                //使用工具类获得维度表的信息
                JSONObject jsonobj = DimUtil.getdiminfo(tablename, key);
                if(jsonobj!=null){
                    //将数据进行关联，没有关联的方法就自定义
                    join(input,jsonobj);
                }

                resultFuture.complete(Arrays.asList(input));
            }
        });
    }


}
