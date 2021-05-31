package com.atguigu.flinkgmall1116;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages ="com.atguigu.flinkgmall1116.mapper" )
public class FlinkgmallPublisherApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlinkgmallPublisherApplication.class, args);
    }

}
