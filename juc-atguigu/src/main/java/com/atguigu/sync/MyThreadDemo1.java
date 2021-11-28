package com.atguigu.sync;

/**
 * 线程间通信-Synchronized实现案例
 * 自己尝试写的
 * Created by shucheng on 2021/11/28 21:15
 */
public class MyThreadDemo1 {

    public static final Object LOCK = new Object();

    private static int a = 0;

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (LOCK) {
                while (true) {
                    if (a == 0) {
                        a++;
                        System.out.println(Thread.currentThread().getName() + " a的值为：" + a); // 1
                        LOCK.notify();
                    } else {
                        try {
                            LOCK.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, "AA").start();

        new Thread(() -> {
            synchronized (LOCK) {
                while (true) {
                    if (a == 1) {
                        a--;
                        System.out.println(Thread.currentThread().getName() + " a的值为：" + a); // 0
                        LOCK.notify();
                    } else {
                        try {
                            LOCK.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, "BB").start();;
    }
}
