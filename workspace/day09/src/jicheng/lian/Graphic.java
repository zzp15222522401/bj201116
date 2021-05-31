package jicheng.lian;

import jdk.nashorn.internal.runtime.options.LoggingOption;

//父类Graphic图形
//包含属性：name（图形名），属性私有化，不提供无参构造，只提供有参构造
//包含求面积getArea()：返回0.0
//求周长getPerimeter()方法：返回0.0
//显示信息getInfo()方法：返回图形名称、面积、周长
public class Graphic {
    private  String name;

    public Graphic() {
    }

    public Graphic(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    double getArea(){
        return 0.0;
    }
    double getPerimeter(){
        return 0.0;
    }
    String getInfo(){
        return "图形名称："+name+"图形周长:"+getPerimeter()+"面积:"+getArea();
    }
}
