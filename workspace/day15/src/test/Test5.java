package test;

/**
 * 创建和启动2个子线程，一个打印奇数，一个打印偶数，
 * （1）要求实现交替打印。
 * （2）每个数打印间隔1秒
 */
public class Test5 {
    public static void main(String[] args) {
        new Thread20().start();
        new Thread21().start();
    }
}
class Thread20 extends Thread{
    int num=1;
    boolean flag=true;
    @Override
    public void run() {
        while(flag){

                System.out.println("打印奇数" + num);
                num += 2;
                try {
                    Thread20.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

        }
    }
}
class Thread21 extends Thread{
    int num=0;
    boolean flag=true;
    @Override
    public void run() {
        while(flag){

                System.out.println("打印偶数" + num);
                num += 2;
                try {
                    Thread20.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

        }
    }
}