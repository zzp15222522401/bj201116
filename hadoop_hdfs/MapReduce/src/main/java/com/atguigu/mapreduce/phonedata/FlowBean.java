package com.atguigu.mapreduce.phonedata;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class FlowBean implements Writable {
    private int upFlow;
    private int downFlow;
    private int sumFlow;


    public void setSumFlow() {
        this.sumFlow=this.upFlow+this.downFlow;
    }


    public int getUpFlow() {
        return upFlow;
    }

    public void setUpFlow(int upFlow) {
        this.upFlow = upFlow;
    }

    public int getDownFlow() {
        return downFlow;
    }

    public void setDownFlow(int downFlow) {
        this.downFlow = downFlow;
    }

    public int getSumFlow() {
        return sumFlow;
    }

    public void setSumFlow(int sumFlow) {
        this.sumFlow = sumFlow;
    }

    @Override
    public String toString() {
        return
                 this.upFlow +
                "\t" + this.downFlow +
                "\t" + this.sumFlow
                ;
    }

    public void write(DataOutput out) throws IOException {
        //这里的写入表示map阶段最开始输入的数据
        //序列化
        out.writeInt(this.upFlow);
        out.writeInt(this.downFlow);
        out.writeInt(this.sumFlow);
    }
    //反序列化
    public void readFields(DataInput in) throws IOException {
        this.upFlow=in.readInt();
        this.downFlow=in.readInt();
        this.sumFlow=in.readInt();
    }
   /* private Integer upFlow;
    private Integer downFlow;
    private Integer totalFlow;

    public Integer getUpFlow() {
        return upFlow;
    }

    public void setUpFlow(Integer upFlow) {
        this.upFlow = upFlow;
    }

    public Integer getDownFlow() {
        return downFlow;
    }

    public void setDownFlow(Integer downFlow) {
        this.downFlow = downFlow;
    }

    public Integer getTotalFlow() {
        return totalFlow;
    }

    public void setTotalFlow(Integer totalFlow) {
        this.totalFlow = totalFlow;
    }

    @Override
    public String toString() {
        return this.upFlow + "\t" + this.downFlow + "\t" + this.totalFlow;
    }

    *//**
     * 完成序列化方法
     * @param out
     * @throws IOException
     *//*
    public void write(DataOutput out) throws IOException {
        out.writeInt(this.upFlow);
        out.writeInt(this.downFlow);
        out.writeInt(this.totalFlow);
    }

    *//**
     * 反序列化方法
     * @param in
     * @throws IOException
     *//*
    public void readFields(DataInput in) throws IOException {
        this.upFlow = in.readInt();
        this.downFlow = in.readInt();
        this.totalFlow = in.readInt();
    }

    public void setTotalFlow() {
        this.totalFlow = this.upFlow + this.downFlow;
    }*/
}
