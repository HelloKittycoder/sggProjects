package com.atguigu.sync;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by shucheng on 2021/12/4 10:19
 */
// 可重入锁
public class SyncLockDemo {

    public synchronized void add() {
        add();
    }

    /**
     * 这个是自己另外弄的，为了看下最多能深入到多少层（这个和设置的虚拟机参数有关）
     * @param args
     */
    /*public synchronized void add(int depth) {
        if (depth > 5702) {
            return;
        }
        add(depth + 1);
        System.out.println(Thread.currentThread().getName() + " 第" + depth + "层");
    }*/

    public static void main(String[] args) {
        // Lock演示可重入锁（注意：lock和unlock要成对出现）
        Lock lock = new ReentrantLock();
        new Thread(() -> {
            // 上锁
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " 外层");
                lock.lock();
                try {
                    System.out.println(Thread.currentThread().getName() + " 内层");
                } finally {
                    lock.unlock();
                }
            } finally {
                // 解锁
                lock.unlock();
            }
        }, "t1").start();

        // 创建新线程
        new Thread(() -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " aaaa");
            } finally {
                lock.unlock();
            }
        }, "aa").start();

        // synchronized
        /*Object o = new Object();
        new Thread(() -> {
            synchronized (o) {
                System.out.println(Thread.currentThread().getName() + " 外层");

                synchronized (o) {
                    System.out.println(Thread.currentThread().getName() + " 中层");

                    synchronized (o) {
                        System.out.println(Thread.currentThread().getName() + " 内层");
                    }
                }
            }
        }, "t1").start();

        new SyncLockDemo().add();*/
        // new SyncLockDemo().add(0);
    }
}
