package Test;
/*
* 在Count类中，声明如下方法：
1、public long  sum(int...  nums)：求0~n个整数的累加和，如果没有传参，就返回0
2、public int max(int a, int... others)：求1~n个整数中的最大值
3、public String concat(String...  strings)：求0~n个字符串的拼接结果
4、public boolean isEven(int... nums)：判断0~n个整数是否都是偶数，如果都是偶数，返回true，否则返回false
​	声明一个Test01测试类，并在main方法中调用测试
* */
public class Test {
    public static void main(String[] args) {
        Count a1=new Count();
        long a=a1.sum(1,2,3,4,5);
        System.out.println(a);
        int b=a1.max(12,15,64,1,98);
        System.out.println(b);
        String str=a1.concat("a","1","v");
        System.out.println(str);
        boolean flag=a1.isEven(2,3,4,6,4);
        System.out.println(flag);
    }

}
