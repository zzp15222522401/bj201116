package com.atguigu.mapreduce.lianxi.lianxi2;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class ReduceJoinBean implements Writable {
    //reducejoinbean没有作为排序的key所以不需要将其实现接口compareable
    private  String id;
    private  String pid;
    private String pname;
    private Integer amout;
    private String tatol;

    public ReduceJoinBean() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public Integer getAmout() {
        return amout;
    }

    public void setAmout(Integer amout) {
        this.amout = amout;
    }

    public String getTatol() {
        return tatol;
    }

    public void setTatol(String tatol) {
        this.tatol = tatol;
    }

    @Override
    public String toString() {
        return id+"\t"+pname+"\t"+amout;
    }

    public void write(DataOutput out) throws IOException {
        out.writeUTF(id);
        out.writeUTF(pid);
        out.writeUTF(pname);
        out.writeInt(amout);
        out.writeUTF(tatol);


    }

    public void readFields(DataInput in) throws IOException {
        id=in.readUTF();
        pid=in.readUTF();
        pname=in.readUTF();
        amout=in.readInt();
        tatol=in.readUTF();
    }
}
