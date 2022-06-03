package org.example.SleepWaitYieldJoin;

public class ThreadJoinTest {
    private static final Object obj = new Object();
    private static int count = 0;

    public static void main(String[] args) {
        Thread t1 = new Thread(new MyThread1());
        Thread t2 = new Thread(new MyThread2());
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("count:" + count);
    }

    static class MyThread1 implements Runnable {

        @Override
        public void run() {
            synchronized (obj) {
                System.out.println("thread1 start");
                for (int i = 0; i < 10; i++){
                    count++;
                    System.out.println(Thread.currentThread().getName());
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread1 end");
            }
        }
    }

    static class MyThread2 implements Runnable {

        @Override
        public void run() {
            synchronized (obj) {
                System.out.println("thread2 start");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < 10; i++){
                    count++;
                    System.out.println(Thread.currentThread().getName());
                }
                System.out.println("thread2 end");
            }
        }
    }
}
