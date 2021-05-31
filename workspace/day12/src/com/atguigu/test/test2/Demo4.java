package com.atguigu.test.test2;

public class Demo4 {
    public static void main(String[] args) {
        Pay p1=Pay.Cash;
        Pay p2=Pay.WeChatPay;
        Pay p3=Pay.Alipay;
        Pay p4=Pay.BankCard;
        Pay p5=Pay.CreditCard;
        System.out.println(p1.getName());
        System.out.println(p2.getName());
        System.out.println(p3.getName());
        System.out.println(p4.getName());
        System.out.println(p5.getName());
    }
}
enum Pay{
    Cash("现金"),WeChatPay("微信"),Alipay("支付宝"),BankCard("银行卡"),CreditCard("信用卡");
    private  String name;

    Pay() {
    }

    Pay(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
