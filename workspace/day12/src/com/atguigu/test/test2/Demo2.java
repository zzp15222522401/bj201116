package com.atguigu.test.test2;

public class Demo2  {
    public static void main(String[] args) {
        /*Father father=new Father(){
            @Override
            public void method() {
                System.out.println("hello baby");
            }
        };
        father.method();*/


        Triangle[] triangles=new Triangle[3];
        triangles[0]=new Triangle(3,4,5);
        triangles[1]=new Triangle(3,4,4);
        triangles[2]=new Triangle(5,4,5);

    }
}
