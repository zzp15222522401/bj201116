package test;

/**
 * 多线程龟兔赛跑，乌龟和兔子进行1000米跑，兔子前进5米，乌龟前进1米，兔子每20米休息500毫秒，乌龟
 * 每100米休息500毫秒，谁先到1000谁获胜。
 */
public class Kwtest {
    public static void main(String[] args) {
        tu a=new tu();
        gui b=new gui();
        Thread a1=new Thread(a);
        Thread a2=new Thread(b);
        a1.start();
        a2.start();

    }
}
class tu implements Runnable{
    boolean flag=true;
    @Override
    public void run() {
        while(flag) {
            for (int i = 5; i <= 1000; i+=5) {
                if (i == 1000){
                    System.out.println("比赛结束tu获胜");
                        return;
                }
                if (i % 20 == 0) {
                    try {
                        Thread.sleep(500);
                        System.out.println("兔子跑了"+i+"米休息500毫秒");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
class gui implements Runnable{
    boolean flag=true;
    @Override
    public void run() {
        while(flag) {
            for (int i = 1; i <= 1000; i++) {
                if (i == 1000){
                    System.out.println("比赛结束wugui获胜");
                        return;
                    }
                if (i % 100 == 0) {
                    try {
                        Thread.sleep(500);
                        System.out.println("乌龟跑了"+i+"米休息500毫秒");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}