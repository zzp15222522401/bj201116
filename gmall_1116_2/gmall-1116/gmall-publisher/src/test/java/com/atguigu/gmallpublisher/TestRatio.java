package com.atguigu.gmallpublisher;

public class TestRatio {
    public static void main(String[] args) {


        int low20Count=43;
        int total=97;
        int up30Count=21;
        Double low20Ratio = Math.round(low20Count * 100D / total * 10D) / 10D;
        Double up30Ratio = Math.round(up30Count * 100D / total * 10D) / 10D;
        Double up20AndLow30Ratio = 100D-(up30Ratio+low20Ratio);

        System.out.println(low20Ratio);
        System.out.println(up30Ratio);
        System.out.println(up20AndLow30Ratio);

    }
}
