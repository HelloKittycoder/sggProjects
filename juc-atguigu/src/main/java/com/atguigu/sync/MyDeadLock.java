package com.atguigu.sync;

/**
 * 演示死锁（自己尝试写的）
 * Created by shucheng on 2021/12/4 12:23
 */
public class MyDeadLock {

    // 创建两个对象
    static Object lockA = new Object();
    static Object lockB = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            synchronized (lockA) {
                System.out.println(Thread.currentThread().getName() + "获取到a");
                System.out.println(Thread.currentThread().getName() + "准备获取b...");
                synchronized (lockB) {
                    System.out.println(Thread.currentThread().getName() + "获取到b了");
                }
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            synchronized (lockB) {
                System.out.println(t1.isAlive() + Thread.currentThread().getName() + "获取到b");
                System.out.println(Thread.currentThread().getName() + "准备获取a...");
                synchronized (lockA) {
                    System.out.println(Thread.currentThread().getName() + "获取到a了");
                }
            }
        }, "t2");

        t1.start();
        /*try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        t2.start();
    }
}
