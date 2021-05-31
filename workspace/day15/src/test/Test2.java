package test;

public class Test2 {
    public static void main(String[] args) {
        Thread31 t=new Thread31();
        t.start();
        //当main方法中的i==4的时候，就让t结束
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
           /* if(i==4){
                //t结束
                //t.stop();
                t.setFlag(true);
            }*/
            if(i==5){
                try {
                    t.join(1500);//让t进行插队，插队到执行join方法的线程前面
                    //插队线程      调用join方法的线程
                    //被插队线程    join方法的所在位置的线程
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("main--"+i);
        }
    }
}
class Thread31 extends Thread{
    private boolean flag=false;
    @Override
    public void run() {
        for (int i = 0; i <10 ; i++) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("test1---"+i);
            if(flag)
                return;
        }
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}

