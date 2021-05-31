package com.shangguigu.demo.demo1;

public class Plant implements LiveAble {
    @Override
    public void eat() {
        System.out.println("吸收营养");
    }

    @Override
    public void breathe() {
        System.out.println("吸入二氧化碳呼出氧气");
    }
    /*默认的方法实现类可以不用实现。
    * */
}
