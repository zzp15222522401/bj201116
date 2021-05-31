package com.atguigu.mapreduce.phonedatacompaeable;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class FlowPartition extends Partitioner<FlowBean, Text> {
    public int getPartition(FlowBean flowBean, Text text, int numPartitions) {
        String string = text.toString();
        int partitions;
        if(string.startsWith("136")){
            partitions=0;
        }else if(string.startsWith("137")){
            partitions=1;
        }else if(string.startsWith("138")){
            partitions=2;
        }else if(string.startsWith("139")){
            partitions=3;
        } else{
            partitions=4;
        }
        return partitions;
    }
}
