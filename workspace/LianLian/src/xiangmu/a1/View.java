package xiangmu.a1;

import java.util.ArrayList;
import java.util.Scanner;

public class View {
    ArrayList arrayList=new ArrayList();
    User user=new User();
    Scanner input =new Scanner(System.in);

    public static void main(String[] args) {
        View.catchmain();

    }
    public static void catchmain(){
        View view=new View();
        while(true){
            System.out.println("---------------客户信息管理软件--------------\n\t\t\t" +
                    "1.添加客户\n\t\t\t2.修改客户\n\t\t\t" +
                    "3.删除客户\n\t\t\t4.客户列表\n\t\t\t5.退出\n\t\t\t请选择（1-5）");
            System.out.println("-----------------------------");
            char c=Bean.readMenuSelection();
        switch(c){
            case '1':
                view.add();
                break;
            case '2':
                view.replce();
                break;
            case '3':
                view.delete();
                break;
            case '4':
                view.seeall();
                break;
            case '5':
                view.exit();
                break;
        }
        }
    }
    public void add(){
        System.out.println("姓名：");
        String name=Bean.readString(30);
        System.out.println("年龄：");
        int age=Bean.readInt();
        System.out.println("电话：");
        String phone=Bean.readString(11);
        System.out.println("邮箱：");
        String email=Bean.readString(20);
        User user1=new User(name,age,phone,email);
        arrayList.add(user1);
        System.out.println("添加成功");
    }
    public void replce(){
        int a;
        while(true) {
            System.out.println("输入您想要修改的客户id(-1退出)");
             a=Bean.readInt();
            if (a == -1) {
                return;
            } else {
                if (arrayList.get(a - 1) != null)
                    break;
            }
        }
        System.out.println(arrayList.get(a - 1));
        System.out.println("新的姓名：");
        String name=Bean.readString(30);
        System.out.println("新的年龄：");
        int age=Bean.readInt();
        System.out.println("新的电话：");
        String phone=Bean.readString(11);
        System.out.println("新的邮箱：");
        String email=Bean.readString(20);
        User user1=new User(a,name,age,phone,email);
        arrayList.set(a-1,user1);
        System.out.println("修改成功");
    }
    public void delete(){
        int a;
        char c;
        while(true) {
            System.out.println("输入您想要删除的客户id(-1退出)");
            a=Bean.readInt();
            if (a == -1) {
                return;
            } else {
                if (arrayList.get(a - 1) != null)
                    break;
            }
        }
        if(arrayList.get(a-1)==null){
            System.out.println("用户不存在");
            return;
        }
        System.out.println("是否确认和删除（Y/N）");
        c=Bean.readConfirmSelection();
        if(c=='Y') {
            arrayList.remove(a - 1);
            System.out.println("删除成功");
        }
    }
    public void seeall(){
        for (Object o : arrayList) {
            System.out.println(o);
        }
    }
    public void exit(){
        System.out.println("是否选择退出（Y/N）");
        char a=Bean.readConfirmSelection();
        if(a=='Y'){
            System.out.println("退出成功");
            return;
        }
    }
}
