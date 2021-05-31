package com.atguigu.mapreduce.lianxi.lianxi6;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class Partitionreducer extends Reducer<Text,PhoneBean,Text,PhoneBean> {
    Text outk=new Text();
    PhoneBean outv=new PhoneBean();
    @Override
    protected void reduce(Text key, Iterable<PhoneBean> values, Context context) throws IOException, InterruptedException {
        int updata=0;
        int downdata=0;
        int sumdata=0;
        for (PhoneBean value : values) {
            updata+=value.getUpdata();
            downdata+=value.getDowndata();
            sumdata+=value.getSumdata();
        }
        outv.setUpdata(updata);
        outv.setDowndata(downdata);
        outv.setSumdata(sumdata);

        context.write(key,outv);
    }
}
