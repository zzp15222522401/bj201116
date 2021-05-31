package com.atguigu.test;

/**
 * 有家餐馆的取餐口比较小，只能放10份快餐，厨师做完快餐放在取餐口的工作台上，
 * 服务员从这个工作台取出快餐给顾客。现在有多个厨师和多个服务员。
 */
public class Test2 {
    public static void main(String[] args) {
        Food food=new Food();
        waiter waiter1=new waiter(food);
        waiter waiter2=new waiter(food);
        chef chef1=new chef(food);
        chef chef2=new chef(food);
        Thread thread1=new Thread(waiter1,"服务员1");
        Thread thread2=new Thread(waiter2,"服务员2");
        Thread thread3=new Thread(chef1,"厨师1");
        Thread thread4=new Thread(chef2,"厨师2");
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }
}
class waiter implements Runnable{
    private Food food;
    public waiter(Food food) {
        this.food = food;
    }
    @Override
    public void run() {
        while (true)
            food.take();
    }
}
class chef implements Runnable{
    private Food food;
    public chef(Food food) {
        this.food = food;
    }
    @Override
    public void run() {
        while (true)
            food.make();
    }
}
class Food {
    private static final int fastfood=10;
    private int count;
    public synchronized void make(){

            if(count>=fastfood) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count++;
            System.out.println( Thread.currentThread().getName() + "制作了一份快餐" + "现在有" + count + "份快餐");
            this.notifyAll();//如果这里唤醒所有的线程，结果如何？
                            //可能会出现两个线程（这里是两个服务员）同时进入这个同步方法中，使得取餐数同时增加两个，超出10；


    }
    public synchronized  void take(){

            if (count <= 0) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count--;
            if(count>0)
            System.out.println( Thread.currentThread().getName() + "取走了一份快餐" + "现在有" + count + "份快餐");
            this.notifyAll();//如果这里时唤醒多有等待的线程，那么结果会是怎样？
                            //会出现11


    }

}
