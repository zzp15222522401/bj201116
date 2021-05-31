package com.atguigu.mapreduce.phonedata;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class FlowReduce extends Reducer<Text,FlowBean,Text,FlowBean> {
    @Override
    protected void reduce(Text key, Iterable<FlowBean> values, Context context) throws IOException, InterruptedException {
        int sumupFlow=0;
        int sumdownFlow=0;
        int sumFlow=0;
        for (FlowBean value : values) {
            sumupFlow+=value.getUpFlow();
            sumdownFlow+=value.getDownFlow();
            sumFlow+=value.getSumFlow();


        }
        FlowBean flowBean=new FlowBean();
        flowBean.setUpFlow(sumupFlow);
        flowBean.setDownFlow(sumdownFlow);
        flowBean.setSumFlow(sumFlow);

        context.write(key,flowBean);
        /*int sumUpFlow = 0;
        int sumDownFlow = 0;
        int sumTotalFlow = 0;
        // 遍历相同key对应的values
        for (FlowBean value : values) {
            sumUpFlow+=value.getUpFlow();
            sumDownFlow+=value.getDownFlow();
            sumTotalFlow+=value.getTotalFlow();
        }
        // 组装输出数据
        FlowBean flowBean = new FlowBean();
        flowBean.setUpFlow(sumUpFlow);
        flowBean.setDownFlow(sumDownFlow);
        flowBean.setTotalFlow(sumTotalFlow);
        // Reduce阶段输出数据
        context.write(key,flowBean);*/
    }
}
