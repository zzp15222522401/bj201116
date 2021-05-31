package com.atguigu.mapreduce.reducejoin;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ReduceJoinReduce extends Reducer<Text,ReduceJoinBean,ReduceJoinBean, NullWritable> {
    List<ReduceJoinBean> rjb=new ArrayList<ReduceJoinBean>();
    ReduceJoinBean rjbpd=new ReduceJoinBean();
    @Override
    protected void reduce(Text key, Iterable<ReduceJoinBean> values, Context context) throws IOException, InterruptedException {
        //我们需要将两个表中的数据分别区分开进行维护
        for (ReduceJoinBean value : values) {
            ReduceJoinBean rjborder = new ReduceJoinBean();//创建一个新的ReduceJoinBean来接收
            //Hadoop底层内存不会new对象，数据存储会产生覆盖，所以我们需要自己new

            //判断获取的数据来自哪个表
            if ("order".equals(value.getFlag())) {//此时的数据来源于order表

                try {
                    BeanUtils.copyProperties(rjborder, value);
                    rjb.add(rjborder);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            } else {
                try {//这里处理的是pd表的数据
                    BeanUtils.copyProperties(rjbpd, value);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
            for (ReduceJoinBean joinBean : rjb) {//进行join关联，
                joinBean.setPname(rjbpd.getPname());
                context.write(joinBean,NullWritable.get());
            }

            //进行数据的清理，每次只添加和写出一个对象，保证写出对象不会出现重复
            rjb.clear();

    }
}
