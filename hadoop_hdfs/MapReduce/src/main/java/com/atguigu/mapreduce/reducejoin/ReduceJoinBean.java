package com.atguigu.mapreduce.reducejoin;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class ReduceJoinBean implements Writable {
    //定义每个表中的属性
    private String orderid;
    private String pid;
    private Integer amount;
    private String pname;
    //定义一个标志，判断是order表还是pd表
    private String flag;

    public ReduceJoinBean() {
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return orderid+"\t"+pname+"\t"+amount;
    }
    //序列化
    public void write(DataOutput out) throws IOException {
        out.writeUTF(orderid);
        out.writeUTF(pid);
        out.writeInt(amount);
        out.writeUTF(pname);
        out.writeUTF(flag);
    }
    //反序列化
    public void readFields(DataInput in) throws IOException {
        orderid=in.readUTF();
        pid=in.readUTF();
        amount=in.readInt();
        pname=in.readUTF();
        flag=in.readUTF();
    }
}
