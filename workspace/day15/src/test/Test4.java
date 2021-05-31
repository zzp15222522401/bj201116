package test;

/**
 * 创建和启动2个子线程，一个打印1-10之间奇数，一个打印1-10之间偶数，
 * （1）要求每个线程要么不打印，要么就连续打印5个数，每个数打印间隔500毫秒
 * （2）但两个线程不要求交替打印
 */
public class Test4 {
    public static void main(String[] args) {
        Thread10 a=new Thread10();
        Thread11 a1=new Thread11();
        a.start();
        a1.start();

    }
}
class Thread10 extends Thread{
    int num=1;
    boolean flag=true;
    @Override
    public void run() {
        while (flag) {
            synchronized (Thread.class) {

                for (int i = 1; i <= 5; i++) {
                    System.out.println(getName() + "打印奇数的线程" + num);
                    num += 2;
                    try {
                        Thread10.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            if(num>100)
                flag=false;
        }

    }
}
class Thread11 extends Thread{
    int num=2;
    boolean flag=true;
    @Override
    public void run() {
            while (flag){
            synchronized (Thread.class) {

                for (int i = 1; i <= 5; i++) {
                    System.out.println(getName() + "打印偶数的线程" + num);
                    num += 2;
                    try {
                        Thread11.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
                if (num > 100)
                    flag=false;
        }

    }
}