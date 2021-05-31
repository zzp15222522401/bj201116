package com.atguigu.mapreduce.lianxi.lianxi2;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class ReduceJoinReducer extends Reducer<Text,ReduceJoinBean,ReduceJoinBean, NullWritable> {

    private List<ReduceJoinBean> list=new ArrayList();

    @Override
    protected void reduce(Text key, Iterable<ReduceJoinBean> values, Context context) throws IOException, InterruptedException {
        ReduceJoinBean rjb1=new ReduceJoinBean();
        for (ReduceJoinBean value : values) {
            if("order".equals(value.getTatol())){
                ReduceJoinBean rjb=new ReduceJoinBean();
                try {
                    BeanUtils.copyProperties(rjb,value);
                    list.add(rjb);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }else{
                try {
                    BeanUtils.copyProperties(rjb1,value);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        for (ReduceJoinBean joinBean : list) {
            joinBean.setPname(rjb1.getPname());
            context.write(joinBean,NullWritable.get());
        }
        list.clear();
    }
}
