package com.atguigu.maven;

public class MakeFriend {
    public void Makefriend(){
        HelloFriend helloFriend=new HelloFriend();
        String s= helloFriend.sayHelloToFriend(new Hello().sayHello("你好"));
        System.out.println(s);
    }
}
