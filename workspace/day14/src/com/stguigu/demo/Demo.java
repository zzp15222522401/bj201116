package com.stguigu.demo;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Random;

public class Demo {
    public static void main(String[] args) {

        //大整形
        int i = Integer.parseInt("50");
        BigInteger bigInteger = new BigInteger("65684856");
        BigInteger bigInteger1 = new BigInteger("6621321");
        BigInteger multiply = bigInteger.multiply(bigInteger1);
        System.out.println(multiply);

        //大浮点
        BigDecimal bigDecimal=new BigDecimal("100.0");
        BigDecimal bigDecimal1=new BigDecimal("30.3");
        BigDecimal divide = bigDecimal.divide(bigDecimal1,BigDecimal.ROUND_HALF_UP);
        System.out.println(divide);
        BigDecimal divide1 = bigDecimal.divide(bigDecimal1, 50, BigDecimal.ROUND_HALF_UP);
        System.out.println(divide1);
        Random random=new Random();
        System.out.println(random.nextInt(1000));;

    }
}