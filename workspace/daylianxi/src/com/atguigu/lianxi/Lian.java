package com.atguigu.lianxi;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Lian {
    public static void main(String[] args) {

    }
    public static void main4(String[] args) {
        /**
         * 13、写一段代码实现在遍历ArrayList时移除一个元素，例如：”java”？
         * 要想在遍历时删除集合中的元素，只有用Iterator方法。
         */
        List list=new ArrayList();
        list.add("java");
        list.add("mysql");
        list.add("orcal");
        list.add("python");
        Iterator i=list.iterator();
        while(i.hasNext()){
            Object next = i.next();
            if("java".equals(next))
                i.remove();
        }
        System.out.println(list);
    }
    public static void main3(String[] args) {
//String str = “123”;转成Number型
    /*    String str="123";
        Integer.parseInt(str);
        Integer a=new Integer(str);*/


    }
    public static void main2(String[] args) {
        //已知有一个路径 ：  String str="d:/aa/bb/cc/java.txt"    新建java.txt
        File file=new File("d:/aa/bb/cc/java.txt");
        if(!file.exists()){//判断是否存在，不存在就建
            File parentFile = file.getParentFile();//获得父级File的对象
            parentFile.mkdirs();//建立目录
            try {
                file.createNewFile();//建新文件
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    public static void main1(String[] args) {
        Collection collection=new ArrayList();
        collection.add("a");
        collection.add("a");
        collection.add("b");
        collection.add("c");
        collection.add("d");

        collection.toArray();

    }
    public void compare(int a, int b) {
            if (a == b) {
                System.out.println(true);
            } else {
                System.out.println(false);
            }

    }
    @Test
    public void method(){
        Lian a=new Lian();
        a.compare(3,5);
    }
    @Test
    public void method1(){
        String str="123";
        int i = Integer.parseInt(str);
        int []a=new int[]{1,2,3,4,5,6,7,8,9};
        int []b=new int[]{10,11,12,13,14,15,16};
        System.arraycopy(a,3,a,5,a.length-5);
        for (int i1 : a) {
            System.out.println(i1);
        }



    }
}

/**
 * 3、编写方法实现：求某年某月某日是这一年的第几天
 * 提示：闰年（1）能被4整除不能被100整除（2）能被400整除
 */
class Test1 {
    public static void main(String[] args) {
        Scanner input =new Scanner(System.in);

       // SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy年MM月dd日");
        while (true) {
            System.out.println("请输入时间(年)：");
            int year=input.nextInt();
            System.out.println("请输入时间(月)：");
            int month=input.nextInt();
            System.out.println("请输入时间(日)：");
            int day=input.nextInt();
            switch (month){

                case 1:
                    if(day>0&&day<=31)
                    System.out.println("是今年的第"+day+"天");
                    else{
                        System.out.println("输入有误");
                    }
                    break;
                case 2:
                    if(year%4==0&&year%100!=0||year%400==0){
                        if(day>0&&day<=29){
                            System.out.println("是今年的第"+(31+day)+"天");
                        }
                        else{
                            System.out.println("输入有误");
                        }
                    }
                    else{
                        if(day>0&&day<=28) {
                            System.out.println("是今年的第"+(31+day)+"天");
                        }
                    }

                    break;
                case 3:
                    if(day>0&&day<=31){
                            if(year%4==0&&year%100!=0||year%400==0){
                            System.out.println("是今年的第"+(60+day)+"天");
                        }
                        else{
                                System.out.println("是今年的第"+(59+day)+"天");
                        }
                    }
                    else
                        System.out.println("输入有误");

                    break;
                case 4:
                    if(day>0&&day<=30){
                        if(year%4==0&&year%100!=0||year%400==0){
                            System.out.println("是今年的第"+(91+day)+"天");
                        }
                        else{
                            System.out.println("是今年的第"+(90+day)+"天");
                        }
                    }
                    else
                        System.out.println("输入有误");
                    break;
                case 5:
                    if(day>0&&day<=31){
                        if(year%4==0&&year%100!=0||year%400==0){
                            System.out.println("是今年的第"+(121+day)+"天");
                        }
                        else{
                            System.out.println("是今年的第"+(120+day)+"天");
                        }
                    }
                    else
                        System.out.println("输入有误");
                    break;
                case 6:
                    if(day>0&&day<=30){
                        if(year%4==0&&year%100!=0||year%400==0){
                            System.out.println("是今年的第"+(152+day)+"天");
                        }
                        else{
                            System.out.println("是今年的第"+(151+day)+"天");
                        }
                    }
                    else
                        System.out.println("输入有误");
                    break;
                case 7:
                    if(day>0&&day<=31){
                        if(year%4==0&&year%100!=0||year%400==0){
                            System.out.println("是今年的第"+(182+day)+"天");
                        }
                        else{
                            System.out.println("是今年的第"+(181+day)+"天");
                        }
                    }
                    else
                        System.out.println("输入有误");
                    break;
                case 8:
                    if(day>0&&day<=31){
                        if(year%4==0&&year%100!=0||year%400==0){
                            System.out.println("是今年的第"+(213+day)+"天");
                        }
                        else{
                            System.out.println("是今年的第"+(212+day)+"天");
                        }
                    }
                    else
                        System.out.println("输入有误");
                    break;
                case 9:
                    if(day>0&&day<=30){
                        if(year%4==0&&year%100!=0||year%400==0){
                            System.out.println("是今年的第"+(244+day)+"天");
                        }
                        else{
                            System.out.println("是今年的第"+(243+day)+"天");
                        }
                    }
                    else
                        System.out.println("输入有误");
                    break;
                case 10:
                    if(day>0&&day<=31){
                        if(year%4==0&&year%100!=0||year%400==0){
                            System.out.println("是今年的第"+(274+day)+"天");
                        }
                        else{
                            System.out.println("是今年的第"+(273+day)+"天");
                        }
                    }
                    else
                        System.out.println("输入有误");
                    break;
                case 11:
                    if(day>0&&day<=30){
                        if(year%4==0&&year%100!=0||year%400==0){
                            System.out.println("是今年的第"+(305+day)+"天");
                        }
                        else{
                            System.out.println("是今年的第"+(304+day)+"天");
                        }
                    }
                    else
                        System.out.println("输入有误");
                    break;
                case 12:
                    if(day>0&&day<=31){
                        if(year%4==0&&year%100!=0||year%400==0){
                            System.out.println("是今年的第"+(335+day)+"天");
                        }
                        else{
                            System.out.println("是今年的第"+(334+day)+"天");
                        }
                    }
                    else
                        System.out.println("输入有误");
                    break;
            }
        }

    }
}

/**
 * 编写一个饿汉式单例设计模式
*/

 class Method1{
    public static void main(String[] args) {
        final String str4 = "2";
        final String str5 = new String("2");
        System.out.println("(3)"+ (str4 == str5));
    }
    public static void Long1(){
    }
}
class Ehan{//饿汉式
     private  static Ehan QQQ=new Ehan();

    private Ehan() {}
    public Ehan method(){
        return QQQ;
    }
}


