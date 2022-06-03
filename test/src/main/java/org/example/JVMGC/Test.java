package org.example.JVMGC;

public class Test {
    public static void main(String[] args) {
        System.out.println("aaaa");
        //创建对象
        Person p=new Person();
        //怎么把Person 对象变成垃圾
        p=null;
        //建议启动垃圾回收
        System.gc();

        //多造些垃圾

        // for (int i = 0; i < 1000000; i++) {
        //    Person p=new Person();
        //    p=null;

        // }

    }
}

//项目开发中有这样的业务需求：
//所有对象在JVM被释放的时候，请记录一下释放的时间!!!
//记录对象被释放的时间点，这个负责记录的代码写在哪里
//写到finalize()方法中。
class Person{
    //重写finalize()
    //Person类型的对象被垃圾回收器回收的时候，垃圾回收器负责调用：p.finalize()
    protected void finalize() throws Throwable{
        System.out.println("即将被销毁");
    }
}
