package org.example.Concurrent3Features;

import java.util.concurrent.TimeUnit;

class MyData {
    volatile int number = 0;
//    int number = 0;

    public void add10() {
        this.number += 10;
    }
}

public class VolatileVisibilityDemo {
    public static void main(String[] args) {
        MyData myData = new MyData();

        // 启动一个线程修改myData的number，将number的值加10
        new Thread(
                () -> {
                    System.out.println("线程" + Thread.currentThread().getName()+"\t 正在执行");
                    try{
                        TimeUnit.SECONDS.sleep(3);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    myData.add10();
                    System.out.println("线程" + Thread.currentThread().getName()+"\t 更新后，number的值为" + myData.number);
                }
        ).start();
        System.out.println("-------------" + myData.number);
        // 看一下主线程能否保持可见性
        while (myData.number == 0) {
            // 当上面的线程将number加10后，如果有可见性的话，那么就会跳出循环；
            // 如果没有可见性的话，就会一直在循环里执行
        }

        System.out.println("具有可见性！" + myData.number);
    }
}

