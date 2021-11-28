package com.atguigu;

/**
 * 用户线程：自定义线程、
 * 主线程结束了，用户线程还在运行，jvm存活
 * 守护线程：如垃圾回收
 * 没有用户线程了，都是守护线程，jvm结束
 * Created by shucheng on 2021/11/28 15:25
 */
public class Main {
    public static void main(String[] args) {
        /*Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + " 线程状态：" + thread.isAlive());*/
        Thread aa = new Thread(()->{
            System.out.println(Thread.currentThread().getName() + "::" + Thread.currentThread().isDaemon());
            // System.out.println(thread.getName() + " 线程状态：" + thread.isAlive());
            while (true) {
            }
        }, "aa");
        // 设置成守护线程
        aa.setDaemon(true);
        aa.start();

        System.out.println(Thread.currentThread().getName() + " over");
    }
}
