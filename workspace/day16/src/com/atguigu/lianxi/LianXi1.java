package com.atguigu.lianxi;

/**
 * 案例：
 * ​	1、创建一个银行账户类，
 * ​	（1）属性：账号，余额，
 * ​	（2）get/set，
 * ​	（3）toString()：返回：账户：xxx，余额：xxx
 * ​	2、创建一个丈夫类
 * ​		负责往里存钱，每次存款[0,10000)以内不等
 * ​	3、创建一个妻子类
 * ​		负责取钱，每次取款[0,10000)以内不等，如果余额不足，要等丈夫存够了才能取
 */
class wifetake extends  Thread{
    private  Bank bank;

    public wifetake( String name, Bank bank) {
        super(name);
        this.bank = bank;
}
    @Override
    public void run() {
        while(true){
            synchronized (Thread.class) {
                double a=Math.random()*10000;
                if(a>bank.getBalance()){
                    System.out.println("妻子想取钱"+a+"但是账户余额不足");
                    try {
                        Thread.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println(Thread.currentThread().getName()+"本次取钱："+a);
                double v = bank.getBalance() - a;
                bank.setBalance(v);
                System.out.println("账户余额："+bank.getBalance());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}

    class husbandput extends Thread{
    private  Bank bank;

    public husbandput( String name, Bank bank) {
        super(name);
        this.bank = bank;
    }
    @Override
    public void run() {
        while(true){
            synchronized (Thread.class) {
            double v = Math.random() * 10000;
            System.out.println(Thread.currentThread().getName()+"本次存钱："+v);
            bank.setBalance(bank.getBalance()+v);
            System.out.println("账户信息："+bank.getBalance());
           /* try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
                /*if (bank.getBalance()>0){
                    try {
                        Thread.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }*/
                Thread.class.notify();
            }
        }
    }
}
class Bank{
    private  String id;
    private double balance;

    public Bank() {
    }

    public Bank(String id, double balance) {
        this.id = id;
        this.balance = balance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "账户："+id+"\t余额："+balance;
    }
}
public class LianXi1 {
    public static void main(String[] args) {
        Bank bank=new Bank();
        husbandput a=new husbandput("丈夫",bank);
        wifetake b=new wifetake("妻子",bank);
        a.start();
        b.start();
    }
}
