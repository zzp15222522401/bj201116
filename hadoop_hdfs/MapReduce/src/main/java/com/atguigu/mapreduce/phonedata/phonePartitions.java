package com.atguigu.mapreduce.phonedata;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;


public class phonePartitions extends Partitioner<Text,FlowBean> {
    /**
     * 自定义一个分区类
     * 创建一个类继承partition类，重写get partition方法，数据类型是根据mapper
     * 中的输出数据类型进行分区的
      * @param text
     * @param flowBean
     * @param numPartitions
     * @return
     */
    @Override
    public int getPartition(Text text, FlowBean flowBean, int numPartitions) {
        /**
         * 这里text表示手机号
         * flowbean表示对应的上下行数据对象
         */
        int partition;
        String phonenumber = text.toString();
        if(phonenumber.startsWith("136")){
            partition=0;
        }else if(phonenumber.startsWith("137")){
            partition=1;
        }else if(phonenumber.startsWith("138")){
            partition=2;
        }else if(phonenumber.startsWith("139")){
            partition=3;
        }else{
            partition=4;
        }
        return partition;
    }
}
