package org.example.Concurrent3Features;

class MyData1 {
    int number = 0;
    /*实际业务逻辑方法中不可能只有只有number++这１行代码，上面可能还有n行代码逻辑。
    现在为了保证number的值是20000，
    就把整个方法都加锁了（其实另外那n行代码，完全可以由多线程同时执行的）。
     */
    public synchronized void add() {
        number++;
    }
}

public class TestAtomicityDemo {
    public static void main(String[] args) {
        MyData1 myData1 = new MyData1();

        // 启动20个线程，每个线程将myData的number值加1000次，那么理论上number值最终是20000
        for (int i=0; i<20; i++) {
            new Thread(() -> {
                for (int j=0; j<1000; j++) {
                    myData1.add();
                }
            }).start();
        }

        // 程序运行时，模型会有主线程和守护线程。如果超过２个，那就说明上面的２０个线程还有没执行完的，就需要等待
        while (Thread.activeCount()>2){
            Thread.yield();
        }

        System.out.println("number值加了20000次，此时number的实际值是：" + myData1.number);

    }
}
