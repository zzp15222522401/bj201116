package com.atguigu.demo;

import java.io.IOException;
import java.net.*;

public class Demo {
    public static void main(String[] args) {

        //UDP协议网络编程
        DatagramSocket my=null;
        try {
            my=new DatagramSocket();
            InetAddress ip=InetAddress.getLocalHost();
            String s="你好";
            byte[] b=s.getBytes();
            DatagramPacket packet=new DatagramPacket(b,b.length,ip,10000);
            my.send(packet);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            my.close();
        }
    }
}
class Demo1_1{
    public static void main(String[] args) {
        DatagramSocket socket=null;
        try {
            socket=new DatagramSocket(10000);
            byte []b=new byte[1024];
            DatagramPacket datagramPacket=new DatagramPacket(b,b.length);
            socket.receive(datagramPacket);
            System.out.println(new String(b,0,datagramPacket.getLength()));



        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            socket.close();
        }
    }
    }