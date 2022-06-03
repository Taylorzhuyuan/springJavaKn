package org.example.Concurrent3Features;

import java.util.concurrent.atomic.AtomicInteger;

class MyData2 {
    volatile int number = 0;

    volatile AtomicInteger num = new AtomicInteger();

    public void add() {
        // 在n++上面可能还有n行代码进行逻辑处理
        number++;
        num.getAndIncrement();
    }
}

public class TestAtomicityDemo1 {
    public static void main(String[] args) {
        MyData2 myData2 = new MyData2();

        // 启动20个线程，每个线程将myData的number值加1000次，那么理论上number值最终是20000
        for (int i=0; i<20; i++) {
            new Thread(() -> {
                for (int j=0; j<1000; j++) {
                    myData2.add();
                }
            }).start();
        }

        // 程序运行时，模型会有主线程和守护线程。如果超过２个，那就说明上面的２０个线程还有没执行完的，就需要等待
        while (Thread.activeCount()>2){
            Thread.yield();
        }

        System.out.println("number值加了20000次，此时number的实际值是：" + myData2.number);
        System.out.println("num值加了20000次，此时number的实际值是：" + myData2.num);

    }
}