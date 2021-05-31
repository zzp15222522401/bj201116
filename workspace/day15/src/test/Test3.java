package test;
//在子线程中输出1-100之间的偶数，主线程输出1-100之间的奇数。
public class Test3 {
    public static void main(String[] args) {
        Thread1 a=new Thread1();
        a.start();
        for (int i = 1; i <100 ; i++) {
            if (i % 2 != 0)
                System.out.println("主线程" + i);
        }
        }
    }

class Thread1 extends Thread{
    @Override
    public void run() {
        for (int i = 1; i <100 ; i++) {
            if (i % 2 == 0)
                System.out.println("子线程" + i);
        }
    }
}
