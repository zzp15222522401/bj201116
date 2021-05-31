package com.atguigu.mapreduce.lianxi.lianxi6;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class Partition extends Partitioner<Text,PhoneBean> {
    public int getPartition(Text text, PhoneBean phoneBean, int numPartitions) {
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
