package com.atguigu.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller  接受请求
 */
//@Controller
    @RestController
    //@RestController=@Controller+@ResponseBody 返回一个对象
public class ControllerTest {
    /**
     * 接收请求后进行处理
     *
     * @return
     */
    @RequestMapping("test")
//    @ResponseBody
    public String test(){
        System.out.println("123");
        return "success";
    }
    @RequestMapping("test2")
    public String test2(@RequestParam("name") String name,@RequestParam("age") int age){
        System.out.println("123");
        return name+":"+age;
    }
}
