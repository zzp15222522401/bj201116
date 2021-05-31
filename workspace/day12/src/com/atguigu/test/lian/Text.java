package com.atguigu.test.lian;

import java.util.Arrays;
 public class Text{
    public static void main(String[] args) {
        MyArryList list=new MyArryList();
        list.add("111111111");
        list.add("222222222");
        list.add("333333333");

        System.out.println("******************");
        list.remove(1);

        System.out.println("****************");
        list.set(1,"99999999");
        System.out.println(list.get(1));
        System.out.println("=======================");
        Selector selector=list.select();
        System.out.println(selector);
        while(selector.hasNext()){
            Object a=selector.next();
            System.out.println(a);
        }
        //System.out.println(list.select().hasNext());

    }
}
interface Selector{
    boolean hasNext();
    Object next();
}
interface Touchable{
    Selector select();
}
class MyArryList implements Touchable{
    private Object[] all=new Object[2];
    private int total;
    public void add(Object element){
        if(total==all.length)
            all= Arrays.copyOf(all,all.length*2);
        all[total++]=element;
    }

    public void remove(int index){
        if(index>total||index<0){
            System.out.println("没有这个元素");
            return;
        }
        else
            System.arraycopy(all,index+1,all,index,total-index-1);
        all[--total]=null;
    }
    public void set(int index,Object value){
        if(index>=total||index<0) {
            System.out.println("没有这个元素");
            return;
        }
        else
            all[index]=value;
    }
    public Object get(int index){
        if(index<0||index>=total) {
            System.out.println("没有这个元素");
            return null;
        }
        return all[index];
    }

    public Selector select() {
        MySelector mySelector=new MyArryList().new MySelector();
        return mySelector;
    }
    private class MySelector implements Selector{
        private int cursor;

        @Override
        public boolean hasNext() {

            return cursor!=total;
        }

        @Override
        public Object next() {
            return all[cursor++];
        }
    }
}
