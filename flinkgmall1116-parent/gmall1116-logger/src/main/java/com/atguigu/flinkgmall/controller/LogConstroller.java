package com.atguigu.flinkgmall.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
public class LogConstroller {
    @Autowired
    KafkaTemplate<String,String> kafkaTemplate;
    @RequestMapping("/applog")
    public String first(@RequestParam("param") String logstr){
        //打印到控制台
        //System.out.println(logstr);
        //落盘
        log.info(logstr);
        //写到kafka
        kafkaTemplate.send("ods_base_log",logstr);
        return "success";
    }
}
