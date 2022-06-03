package org.example.Concurrent3Features;

public class SingletonDemo1 {
    private static SingletonDemo1 instance = null;

    private SingletonDemo1() {
        System.out.println(Thread.currentThread().getName() + "\t 执行构造方法SingletonDemo1()");
    }

    public static SingletonDemo1 getInstance() {
        //次方法不加锁
//        if (instance == null) {
//            instance = new SingletonDemo1();
//        }

        // DCL(Double Check Lock双端检锁机制,即加锁之前和之后都进行检查。)
        /*需要注意的是DCL版的单例模式依然不是100%准确的！！！

        是不是不太明白为什么DCL版单例模式不是100%准确的原因？
        是不是不太明白在讲完指令重排的简单理解后，为什么突然要讲多线程的单例模式？

        因为DCL版单例模式可能会由于指令重排而导致问题，虽然该问题出现的可能性可能是千万分之一，但是该代码依然不是100%准确的。如果要保证100%准确，那么需要添加volatile关键字，添加volatile可以禁止指令重排。

        接下来分析下，为什么DCL版单例模式不是100%准确？

        查看instance = new SingletonDemo1();编译后的指令，可以分为以下３步：
        １）分配对象内存空间：memory = allocate();
        ２）初始化对象：instance(memory);
        ３）设置instance指向分配的内存地址：instance = memory;

        由于步骤２和步骤３不存在数据依赖关系，因此可能出现执行132步骤的情况。
        比如线程1执行了步骤13，还没有执行步骤2，此时instance!=null，但是对象还没有初始化完成；
        如果此时线程2抢占到cpu，然后发现instance!=null，然后直接返回使用，就会发现instance为空，就会出现异常。

        这就是指令重排可能导致的问题，因此要想保证程序100%正确就需要加volatile禁止指令重排。*/


        /*接下来就介绍下volatile能保证禁止指令重排的原理。

    首先要了解一个概念：内存屏障（Memory Barrier），又称为内存栅栏。它是一个CPU指令，有２个作用：
    １）保证特定操作的执行顺序；
    ２）保证某些变量的内存可见性；

    由于编译器和处理器都能执行指令重排。如果在指令之间插入一条Memory Barrier则会告诉编译器和CPU，不管什么指令都不能和这条Memory Barrier指令重排序，也就是说，通过插入内存屏障，禁止在内存屏障前后的指令执行重排需优化。

    内存屏障的另一个作用是强制刷出各种CPU的缓存数据，因此任何CPU上的线程都能读取到这些数据的最新版本。*/
        if (instance == null) {  // a行
            synchronized (SingletonDemo1.class) {
                if (instance == null) {  // b行
                    instance = new SingletonDemo1();  // c行
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        // 单线程测试
//        System.out.println("单线程的情况测试开始");
//        System.out.println(SingletonDemo1.getInstance() == SingletonDemo1.getInstance());
//        System.out.println(SingletonDemo1.getInstance() == SingletonDemo1.getInstance());
//        System.out.println("单线程的情况测试结束\n");

        // 多线程测试
        System.out.println("多线程的情况测试开始");
        for (int i=1; i<=10; i++) {
            new Thread(() -> {
                SingletonDemo1.getInstance();
            }, String.valueOf(i)).start();
        }
    }
}
