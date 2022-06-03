package org.example.JVMCheck;

/*
jps -l:生成虚拟机中所有线程的快照
jmap -heap pid:查看内存使用情况（堆内存配置信息，内存使用情况：年轻 代，年老代）
jmap -histo:live pid | more:查看活跃对象
jmap -dump:format=b,file=../tmp/dump.dat pid:将内存使用情况dump到文件中，然后对它进行分析，jmap也是支持dump到文件中的
jhat -port 端口号 ../tmp/dump.dat:将jvm的内存dump到文件中，这个文件是一个二进制的文件，不方便查看，这时我们可以借助于jhat工具进行查看
                                 打开浏览器进行访问：http://localhost:端口号/
jstack [options] pid:jstack命令的可选参数。如果没有指定这个参数，jstack命令会显示Java虚拟机当前时刻的线程快照信息，如下图
jstack -F pid:命令的可选参数。如果没有指定这个参数，jstack命令会显示Java虚拟机当前时刻的线程快照信息
jstack -l pid:除了方法栈帧以外，jstack命令还会显示关于锁的附加信息，比如属于java.util.concurrent的ownable synchronizers列表
jstack -m pid:jstack命令将显示混合的栈帧信息，除了Java方法栈帧以外，还有本地方法栈帧。本地方法栈帧是C或C++编写的虚拟机代码或JNI/native代码。
              在显示结果中，以星号为前缀的帧是Java方法栈帧，而不以星号为前缀的是本地方法栈帧
*/

public class DeadLockTest {

    public static void main(String[] args) {
        Thread a = new MyThreadA();
        Thread b = new MyThreadB();
        a.setName("线程A");
        a.start();
        b.setName("线程B");
        b.start();

    }
}

class MyThreadA extends Thread {
    @Override
    public void run() {
        System.out.println("================B===================");
        synchronized (A.A) {
            System.out.println("线程【" + Thread.currentThread().getName() + "】开始执行");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (B.B) {
            }
            System.out.println("线程【" + Thread.currentThread().getName() + "】执行结束。B.B = " + B.B + "; A.A = " + A.A);
        }
    }
}

class MyThreadB extends Thread {
    @Override
    public void run() {
        System.out.println("================B===================");
        synchronized (B.B) {
            System.out.println("线程【" + Thread.currentThread().getName() + "】开始执行");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (A.A) {
            }
            System.out.println("线程【" + Thread.currentThread().getName() + "】执行结束。B.B = " + B.B + "; A.A = " + A.A);
        }
    }
}

class A {
    static Integer A = new Integer(1);
}

class B {
    static Integer B = new Integer(1);
}

