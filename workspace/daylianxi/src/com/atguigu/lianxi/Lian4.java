package com.atguigu.lianxi;

import java.io.*;


public class Lian4 {
    public static void main3(String[] args) {
        /**
         *  1、声明一个Message类，包含：发送者、接收者、消息内容、发送时间
         * ​2、创建一个Message对象，并写到message.dat文件中，并再次读取显示    对象流（必须实现Serializable接口）
         */
        Message message=new Message("张三","李四","你好啊，很高兴认识你！","2020/12/15");
        ObjectOutputStream out=null;
        ObjectInputStream input=null;
        try {
             out=new ObjectOutputStream(new FileOutputStream("e:/aaa/bbb/message.dat"));//写出message，这个代码我们是看不懂的
             input=new ObjectInputStream(new FileInputStream("e:/aaa/bbb/message.dat"));//读回来显示，还是呈message格式
             out.writeObject(message);
            Object o = input.readObject();
            System.out.println(o);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if(out!=null)
                    out.close();
                if(input!=null)
                    input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main2(String[] args) {
        /**
         * 把今天的作业文件夹day22_homework下的《我想对你说.txt》字符编码为GBK，
         * 复制到当前项目的testIO文件夹下的《老师的话.txt》字符编码为UTF-8
         */
    InputStreamReader input=null;
    OutputStreamWriter out=null;
        try {
            input= new InputStreamReader(
                    new FileInputStream("E:\\shangguiguJava\\上课视频、笔记、代码\\教师资料\\day20\\day20_homework\\我想对你说.txt"),"GBK");
            out=new OutputStreamWriter(
                    new FileOutputStream("E:\\shangguiguJava\\上课视频、笔记、代码\\教师资料\\day20\\day20_homework\\teseIO\\我想对你说.txt"),"UTF-8");
            char[] c=new char[64];
            int count;
            while((count=input.read(c))!=-1){
                out.write(c,0,count);
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {//字符流不结束或者刷新，磁盘上不会写入，只会创建文件。
                if(out!=null)
                    out.close();
                if(input!=null)
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
    public static void main(String[] args) {
        /**
         *  1、把老师的word笔记文档《第14章 IO流.docx》，复制到当前项目的testIO文件夹下。
         * ​2、要求使用缓冲流和文件流一起实现
         */
        BufferedInputStream input=null;
        BufferedOutputStream out=null;
        try {
             input=
                    new BufferedInputStream(
                            new FileInputStream("E:\\shangguiguJava\\上课视频、笔记、代码\\教师资料\\day20\\day20_homework\\day20_课后练习.md"));
             File file=new File("E:./teseIO\\day20_课后练习复制版.md");
            if(!file.exists()){
                File parentFile = file.getParentFile();
                parentFile.mkdirs();
            }
            out =new BufferedOutputStream(
                    new FileOutputStream("e:\\shangguiguJava\\上课视频、笔记、代码\\教师资料\\day20\\day20_homework\\teseIO\\day20_课后练习复制版.md"));
            byte[] b=new byte[1024];
            int count;
            while((count=input.read(b))!=-1){
                out.write(b,0,count);
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(out!=null)
                out.close();
                if(input!=null)
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
class Message implements Serializable{
    private  String put;
    private  String out;
    private String content;
    private String time;

    @Override
    public String toString() {
        return "Message{" +
                "put='" + put + '\'' +
                ", out='" + out + '\'' +
                ", content='" + content + '\'' +
                ", time=" + time +
                '}';
    }

    public String getPut() {
        return put;
    }

    public void setPut(String put) {
        this.put = put;
    }

    public String getOut() {
        return out;
    }

    public void setOut(String out) {
        this.out = out;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Message() {
    }

    public Message(String put, String out, String content, String time) {
        this.put = put;
        this.out = out;
        this.content = content;
        this.time = time;
    }
}