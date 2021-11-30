package com.atguigu.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 需要实现的功能说明：
 * 使用多线程实现如下的打印（记作P），先打印AA 5次，然后打印BB 10次，最后打印CC 15次
 * 上面的P的操作要重复10次
 *
 * 课程里的写法
 * Created by shucheng on 2021/11/30 22:29
 */
// 第一步 创建资源类
class ShareResource {
    // 定义标志位
    private int flag = 1; // 1 AA；2 BB；3 CC
    // 创建Lock
    private Lock lock = new ReentrantLock();
    // 创建三个Condition
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    // 打印5次，参数第几轮
    public void print5(int loop) throws InterruptedException {
        // 上锁
        lock.lock();
        try {
            while (flag != 1) {
                // 等待
                c1.await();
            }
            // 干活
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + " :: " + i + " 轮数：" + loop);
            }
            // 通知
            flag = 2; //修改标志位 2
            c2.signal(); //通知BB线程
        } finally {
            // 释放锁
            lock.unlock();
        }
    }

    // 打印10次，参数第几轮
    public void print10(int loop) throws InterruptedException {
        // 上锁
        lock.lock();
        try {
            while (flag != 2) {
                // 等待
                c2.await();
            }
            // 干活
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName() + " :: " + i + " 轮数：" + loop);
            }
            // 通知
            flag = 3; //修改标志位 2
            c3.signal(); //通知CC线程
        } finally {
            // 释放锁
            lock.unlock();
        }
    }

    // 打印10次，参数第几轮
    public void print15(int loop) throws InterruptedException {
        // 上锁
        lock.lock();
        try {
            while (flag != 3) {
                // 等待
                c3.await();
            }
            // 干活
            for (int i = 1; i <= 15; i++) {
                System.out.println(Thread.currentThread().getName() + " :: " + i + " 轮数：" + loop);
            }
            // 通知
            flag = 1; //修改标志位 2
            c1.signal(); //通知AA线程
        } finally {
            // 释放锁
            lock.unlock();
        }
    }
}

public class ThreadDemo3 {
    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();
        //创建线程
        new Thread(()->{
            for (int i = 1; i <= 10; i++) {
                try {
                    shareResource.print5(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "AA").start();

        new Thread(()->{
            for (int i = 1; i <= 10; i++) {
                try {
                    shareResource.print10(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "BB").start();

        new Thread(()->{
            for (int i = 1; i <= 10; i++) {
                try {
                    shareResource.print15(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "CC").start();
    }
}
