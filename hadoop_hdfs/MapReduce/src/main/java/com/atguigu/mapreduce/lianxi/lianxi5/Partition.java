package com.atguigu.mapreduce.lianxi.lianxi5;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * 分区是在map后reduce前  所以partition的输入数据类型要和map输出阶段的数据类型相同
 * 谨记  不然就会报数据类型和io不匹配
 */
public class Partition extends Partitioner<PhoneBean,Text> {
    public int getPartition(PhoneBean phoneBean,Text text, int numPartitions) {
        String phone = text.toString();
        int partition;
        if (phone.contains("136")) {
            partition = 0;
        } else if (phone.contains("137")) {
            partition = 1;
        } else if (phone.contains("138")) {
            partition = 2;
        } else if (phone.contains("139")) {
            partition = 3;
        }else{
            partition=4;
        }
        return partition;
    }
}
