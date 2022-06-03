package org.example.SleepWaitYieldJoin;

public class ThreadYieldTest {
    private static final Object obj = new Object();

    public static void main(String[] args) {
        Thread t3 = new Thread(new MyThread3());
        Thread t1 = new Thread(new MyThread1());
        Thread t2 = new Thread(new MyThread2());
        t2.start();
        t3.start();
        t1.start();

    }

    static class MyThread1 implements Runnable {

        @Override
        public void run() {
            synchronized (obj) {
                System.out.println("thread1 start");
                Thread.yield();
                System.out.println("thread1 end");
            }
        }
    }

    static class MyThread2 implements Runnable {

        @Override
        public void run() {
            synchronized (obj) {
                System.out.println("thread2 start");
                System.out.println("thread2 end");
            }
        }
    }

    static class MyThread3 implements Runnable {

        @Override
        public void run() {
            System.out.println("thread3 start");
            Thread.yield();
            System.out.println("thread3 end");
        }
    }
}
