package com.atguigu.sync;

import java.util.concurrent.TimeUnit;

/**
 * 演示死锁
 *
 * 什么是死锁？两个或者两个以上进程在执行过程中，因为争夺资源而造成一种互相等待的现象，如果没有外力干涉，
 * 它们就无法再执行下去
 * Created by shucheng on 2021/12/4 14:00
 */
public class DeadLock {

    // 创建两个对象
    static Object a = new Object();
    static Object b = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (a) {
                System.out.println(Thread.currentThread().getName() + "持有锁a，试图获取锁b");
                /**
                 * 这里停1秒的原因：等线程B获取到锁b
                 * 疑问：会不会出现线程A在停1秒的过程中，线程B已经结束的情况呢？
                 * 答：不会，因为线程A在停1秒的时候，并不会释放锁a；同时线程B在持有锁b后，会接着获取锁a，
                 * 在没有获取到锁a的情况下，线程B是不会结束的
                 *
                 * 如果不停1秒的话，有可能：
                 * （1）死锁：线程A和线程B同时启动，然后线程A持有锁a，线程B持有锁b，之后这两个线程都想获取对方的锁，
                 * 都等着对方先释放，造成死锁
                 * （2）没出现死锁：线程A先启动，获得锁a，获得锁b，执行完毕；然后线程B启动，获得锁b，获得锁a，执行完毕
                 */
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (b) {
                    System.out.println(Thread.currentThread().getName() + "获取到锁b");
                }
            }
        }, "A").start();

        new Thread(() -> {
            synchronized (b) {
                System.out.println(Thread.currentThread().getName() + "持有锁b，试图获取锁a");
                synchronized (a) {
                    System.out.println(Thread.currentThread().getName() + "获取到锁a");
                }
            }
        }, "B").start();
    }
}
