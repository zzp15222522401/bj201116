package test;

/**
 * 电影院要卖票，我们模拟电影院的卖票过程。假设要播放的电影是 “葫芦娃大战奥特曼”，本次电影的座位共100个
 * (本场电影只能卖100张票)。
 * 我们来模拟电影院的售票窗口，实现多个窗口同时卖 “葫芦娃大战奥特曼”这场电影票(多个窗口一起卖这100张票)
 * 这个练习的线程存在不安全性,通过synchronized(){}可以解决。（同步代码块）
 * 当线程运行共享资源的时候就会出现这种问题
 */
public class Test1 {
    public static void main(String[] args) {
        Ticket1 a1 = new Ticket1("买票窗口1");
        Ticket1 a2 = new Ticket1( "买票窗口2");
        Ticket1 a3 = new Ticket1( "买票窗口3");
        a1.start();
        a2.start();
        a3.start();
        //synchronized (this.getClass()) 可以解决此类线程安全问题，可以增大卖票数就能看出多个线程同时运行的效果。(数量越大效果越明显)


        /*Ticket t = new Ticket();
        Thread a1 = new Thread(t, "买票窗口1");
        Thread a2 = new Thread(t, "买票窗口2");
        Thread a3 = new Thread(t, "买票窗口3");
        a1.start();
        a2.start();
        a3.start();*/
        //synchronized (this)
    }
}

    class Ticket1 extends Thread {
        public Ticket1(String name) {
            super(name);
        }

        private static int num = 100;
        @Override
        public void run() {
            boolean flag = true;
            while (flag) {
                synchronized (this.getClass()) {
                    if(num>0){
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                        System.out.println( Thread.currentThread().getName()+"卖出去了一张票\t"+"剩余票数：" + (--num));
                    }
                }
            }
        }
    }
class Ticket implements Runnable {
    private int num = 500;

    @Override
    public void run() {
        boolean flag = true;
        while (flag) {
            synchronized (this) {
                if (num > 0) {
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "卖出去了一张票\t" + "剩余票数：" + (--num));
                }
            }

        }
        }
    }
