package com.atguigu.demo;
/**
 * 网络编程
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Demo5 {
    public static void main(String[] args) throws IOException {
       /* Scanner input=new Scanner(System.in);
        OutputStream out = null;
        InputStream input1 =null;
        try {
            ServerSocket serverSocket=new ServerSocket(8888);
            System.out.println("等待链接...");
            Socket accept = serverSocket.accept();
            out = accept.getOutputStream();
            System.out.println("请输入你想向客户端发送的消息");
            String s=input.next();
            out.write(s.getBytes());

            input1 = accept.getInputStream();
            byte[] b=new byte[1024];
            StringBuilder s1 = new StringBuilder();
            int count;
            while((count=input1.read(b))!=-1){
                s1.append(new String(b,0,count));
            }
            System.out.println(s1);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                input1.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
        //1、准备一个ServerSocket对象，并绑定8888端口
        ServerSocket server = null;
        try {
            server = new ServerSocket(8888);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("等待连接....");

        //2、在8888端口监听客户端的连接，该方法是个阻塞的方法，如果没有客户端连接，将一直等待
        Socket socket = null;
        try {
            socket = server.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("一个客户端连接成功！！");

        //3、获取输入流，用来接收该客户端发送给服务器的数据
        InputStream input = null;
        try {
            input = socket.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //接收数据
        byte[] data = new byte[1024];
        StringBuilder s = new StringBuilder();
        int len;
        while ((len = input.read(data)) != -1) {
            s.append(new String(data, 0, len));
        }
        System.out.println("客户端发送的消息是：" + s);

        //4、获取输出流，用来发送数据给该客户端
        OutputStream out = null;
        try {
            out = socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //发送数据
        try {
            out.write("欢迎登录".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //5、关闭socket，不再与该客户端通信
        //socket关闭，意味着InputStream和OutputStream也关闭了
        finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //6、如果不再接收任何客户端通信，可以关闭ServerSocket

    }
}
class Demo6{
    public static void main(String[] args) throws IOException {
        /*Scanner input2 =new Scanner(System.in);
        InputStream inputStream =null;
        OutputStream outputStream =null;
        try {
            Socket socket=new Socket("127.0.0.1",8888);
            inputStream = socket.getInputStream();
            byte[] b=new byte[1024];
            StringBuilder s = new StringBuilder();
            int count;
            while((count=inputStream.read(b))!=-1){
                s.append(new String(b,0,count));
            }
            System.out.println(s);


            System.out.println("你想向服务器发送的消息：");
            String string=input2.next();
            outputStream = socket.getOutputStream();
            outputStream.write(string.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                inputStream.close();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
        // 1、准备Socket，连接服务器，需要指定服务器的IP地址和端口号
        Socket socket = null;
        try {
            socket = new Socket("127.0.0.1", 8888);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 2、获取输出流，用来发送数据给服务器
        OutputStream out = null;
        try {
            out = socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 发送数据
        try {
            out.write("lalala".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        //会在流末尾写入一个“流的末尾”标记，对方才能读到-1，否则对方的读取方法会一致阻塞
        try {
            socket.shutdownOutput();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //3、获取输入流，用来接收服务器发送给该客户端的数据
        InputStream input = null;
        try {
            input = socket.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 接收数据
        byte[] data = new byte[1024];
        StringBuilder s = new StringBuilder();
        int len;
        while ((len = input.read(data)) != -1) {
            s.append(new String(data, 0, len));
        }
        System.out.println("服务器返回的消息是：" + s);

        //4、关闭socket，不再与服务器通信，即断开与服务器的连接
        //socket关闭，意味着InputStream和OutputStream也关闭了
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}