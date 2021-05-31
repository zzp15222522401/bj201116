package com.atguigu.gmallpublisher.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //写属性方法（get/set）
@NoArgsConstructor //无参构造
@AllArgsConstructor//全参构造
public class Options {
private String name;
private Double value;
}
