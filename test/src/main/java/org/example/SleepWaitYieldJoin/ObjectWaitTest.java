package org.example.SleepWaitYieldJoin;

public class ObjectWaitTest {
    private static final Object obj = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(new MyThread1());
        Thread t2 = new Thread(new MyThread2());
        Thread t3 = new Thread(new MyThread3());
        t1.start();
        t2.start();
        t3.start();
    }

    static class MyThread1 implements Runnable {

        @Override
        public void run() {
            synchronized (obj) {
                System.out.println("thread1 start");
                try {
                    obj.wait();
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
//                obj.notify();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread2 end");
            }
        }
    }

    static class MyThread3 implements Runnable {

        @Override
        public void run() {
            synchronized (obj) {
                System.out.println("thread3 start");
                obj.notify(); //若不调用notify，线程3执行完后不会释放锁，线程1不会被唤醒
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread3 end");
            }
        }
    }
}
