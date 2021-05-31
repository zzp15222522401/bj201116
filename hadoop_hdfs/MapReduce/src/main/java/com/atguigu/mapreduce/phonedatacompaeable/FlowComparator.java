package com.atguigu.mapreduce.phonedatacompaeable;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class FlowComparator extends WritableComparator {
    // 制定当前比较器对象给谁进行比较
    public FlowComparator(){
        super(FlowBean.class,true);
    }

    /**
     * 定义自己的比较规则：倒序
     * @param a
     * @param b
     * @return
     */
    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        // 将 WritableComparable 强转为FlowBean
        FlowBean abean = (FlowBean) a;
        FlowBean bbean = (FlowBean) b;
        Integer integer = Integer.valueOf(abean.getSumFlow());
        Integer integer1 = Integer.valueOf(bbean.getSumFlow());
        //只有对象才能调用方法，前面自己定义的的sumflow数据类型是int型，数据不能调用方法，转换成integer就可以了，或者
        //直接定义成integer类型的数据。
        //abean.getSumFlow().compareTo(bbean.getSumFlow());
        return -integer.compareTo(integer1);
    }
}
