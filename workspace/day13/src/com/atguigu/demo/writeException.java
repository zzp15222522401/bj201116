package com.atguigu.demo;


public  class writeException extends RuntimeException{
        public writeException() {
        }

        public writeException(String message) {
            super(message);
        }
    }

class Test3{
   private static String[] name={"张三","李四","王五"};

    public static void main(String[] args) {

            boolean zhangsan = checkname("张三");
            if(zhangsan)
                System.out.println("注册成功");
        for (String s : name) {
            System.out.println(s);
        }
    }
    public static boolean checkname(String name1)throws RuntimeException{
        for (int i = 0; i <name.length ; i++) {
            if(name[i].equals(name1)){
                throw new writeException(name1+"=名字已经被注册");
            }
        }
        return true;
    }
}
