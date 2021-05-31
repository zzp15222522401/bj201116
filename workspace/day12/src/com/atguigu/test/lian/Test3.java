package com.atguigu.test.lian;

public class Test3 {
    public static void main(String[] args) {
        Payment[] payments=Payment.values();
        for (int i = 0; i < payments.length; i++) {
            payments[i].pay();
        }
    }
}
interface Payable{
    void pay();
}
enum Payment implements Payable{
    ALYPAY{
        public void pay() {
            System.out.println("扫码支付");
        }
    },
    WECHAT{
        public void pay() {
            System.out.println("扫码支付");
        }
    },
    CREDIT_CARD{
        public void pay() {
            System.out.println("输入卡号支付");
        }
    },
    DEPOSIT_CARD{
        public void pay() {
            System.out.println("输入卡号支付");
        }
    };
}
