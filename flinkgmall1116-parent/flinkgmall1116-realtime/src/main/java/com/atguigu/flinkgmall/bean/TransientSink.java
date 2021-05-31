package com.atguigu.flinkgmall.bean;

import lombok.Builder;
import lombok.Data;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
  * Author: Felix
  * Desc: 向ClickHouse写入数据的时候，如果有字段数据不需要传输，可以用该注解标记
 */
@Target(FIELD)
@Retention(RUNTIME) //在运行时有效
public @interface TransientSink {
}
