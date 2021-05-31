package com.atguigu.mapreduce.lianxi.lianxi6;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class PhoneBean implements Writable {

    private Integer updata;
    private Integer downdata;
    private Integer sumdata;


    public void write(DataOutput out) throws IOException {
        out.writeInt(updata);
        out.writeInt(downdata);
        out.writeInt(sumdata);

    }


    public void readFields(DataInput in) throws IOException {
        this.updata=in.readInt();
        this.downdata=in.readInt();
        this.sumdata=in.readInt();
    }
    public void sumdata() {
        this.sumdata = this.updata+this.downdata;
    }

    public PhoneBean() {
    }


    public Integer getUpdata() {
        return updata;
    }

    public void setUpdata(Integer updata) {
        this.updata = updata;
    }

    public Integer getDowndata() {
        return downdata;
    }

    public void setDowndata(Integer downdata) {
        this.downdata = downdata;
    }

    public Integer getSumdata() {
        return sumdata;
    }

    public void setSumdata(Integer sumdata) {
        this.sumdata = sumdata;
    }

    @Override
    public String toString() {
        return updata+"\t"+downdata+"\t"+sumdata;
    }

}
