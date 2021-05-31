package com.atguigu.maven;

import org.junit.Test;

public class MakeFriendTest {
    @Test
    public void MakeFriendTest(){
        HelloFriend helloFriend=new HelloFriend();
        String s= helloFriend.sayHelloToFriend("你好");
        System.out.println(s);
    }
}
