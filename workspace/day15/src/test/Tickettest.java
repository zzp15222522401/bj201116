package test;

/**
 * synchronized方法解决卖票问题（同步方法）
 */
public class Tickettest {
    public static void main(String[] args) {
        /*Ticket2 a1=new Ticket2("窗口1");
        Ticket2 a2=new Ticket2("窗口2");
        Ticket2 a3=new Ticket2("窗口3");
        a1.start();
        a2.start();
        a3.start();*/
        Ticket3 a=new Ticket3();
        Thread thread=new Thread(a,"窗口1");
        Thread thread1=new Thread(a,"窗口2");
        Thread thread2=new Thread(a,"窗口3");
        thread.start();
        thread1.start();
        thread2.start();
    }
}
class Ticket2 extends Thread{
    private static int num=100;

    public Ticket2(String name) {
        super(name);
    }

    @Override
    public void run() {
        while(true) {
            boolean test = test();
            if (test)
                return;
        }
    }
    public synchronized static boolean test() {
        try {//这个睡眠只是为了展示一下卖票的时间，没别的意思
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (num <= 0)
            return true;
        System.out.println(Thread.currentThread().getName() + "剩余票数" + --num);
        return false;
    }
}
class Ticket3 implements Runnable {
    private int num = 100;
    @Override
    public void run() {
        boolean flag = true;
        while (flag) {
            boolean test = test();
            if(test)
                return;
        }
    }
    public synchronized boolean test(){
        if (num > 0) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "卖出去了一张票\t" + "剩余票数：" + (--num));
            return false;
        }
        return true;
    }
}