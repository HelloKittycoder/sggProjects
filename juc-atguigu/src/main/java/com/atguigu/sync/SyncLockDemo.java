package com.atguigu.sync;

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
        // synchronized
        Object o = new Object();
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

        new SyncLockDemo().add();
        // new SyncLockDemo().add(0);
    }
}
