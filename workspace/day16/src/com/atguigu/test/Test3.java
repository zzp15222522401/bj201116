package com.atguigu.test;

public class Test3 {

        public static void main(String[] args) {
            Workbench bench = new Workbench();
            Cook c1 = new Cook("大拿", bench);
            Cook c2 = new Cook("吉祥", bench);
            Waiter w1 = new Waiter("翠花", bench);
            Waiter w2 = new Waiter("如意", bench);

            c1.start();
            c2.start();
            w1.start();
            w2.start();
        }

    }

    class Workbench {
        private static final int MAX_VALUE = 10;
        private int num;

        public synchronized void put() {
            while (num >= MAX_VALUE) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                Thread.sleep(100);
                //加入睡眠时间是放大问题现象，去掉同步和wait等，可观察问题
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            num++;
            System.out.println(Thread.currentThread().getName() + "厨师制作了一份快餐，现在工作台上有：" + num + "份快餐");
            this.notifyAll();
        }

        public synchronized void take() {
            while (num <= 0) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                Thread.sleep(100);
                //加入睡眠时间是放大问题现象，去掉同步和wait等，可观察问题
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            num--;
            System.out.println(Thread.currentThread().getName() + "服务员取走了一份快餐，现在工作台上有：" + num + "份快餐");
            this.notifyAll();
        }
    }

    class Waiter extends Thread {
        private Workbench workbench;

        public Waiter(String name, Workbench workbench) {
            super(name);
            this.workbench = workbench;
        }

        public void run() {
            for (int i = 1; i <= 10; i++) {
                workbench.take();
            }
        }
    }

    class Cook extends Thread {
        private Workbench workbench;

        public Cook(String name, Workbench workbench) {
            super(name);
            this.workbench = workbench;
        }

        public void run() {
            for (int i = 1; i <= 10; i++) {
                workbench.put();
            }
        }
    }


