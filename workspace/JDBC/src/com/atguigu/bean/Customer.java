package com.atguigu.bean;

public class Customer {
    private int id;
    private  String un;
    private  String pw;

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", un='" + un + '\'' +
                ", pw='" + pw + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUn() {
        return un;
    }

    public void setUn(String un) {
        this.un = un;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public Customer() {
    }

    public Customer(int id, String un, String pw) {
        this.id = id;
        this.un = un;
        this.pw = pw;
    }
}
