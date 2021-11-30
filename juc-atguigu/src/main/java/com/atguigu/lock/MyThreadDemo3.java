package com.atguigu.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 参照课程思路，自己写的
 * （这里只用了一个Condition，课程里的写法用了3个Condition）
 * Created by shucheng on 2021/11/30 21:06
 */
// 第一步 创建资源类
class MyShareResource {
    // 定义标志位
    private int flag = 1; // 1 AA；2 BB；3 CC
    // 创建Lock
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    // 打印5次，参数第几轮
    public void print5(int loop) throws InterruptedException {
        lock.lock();
        try {
            while (flag != 1) {
                condition.await();
            }
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + " :: " + i + " 轮数：" + loop);
            }
            //通知
            flag = 2; //修改标志位 2
            condition.signalAll(); //通知其他线程
        } finally {
            lock.unlock();
        }
    }

    // 打印10次，参数第几轮
    public void print10(int loop) throws InterruptedException {
        lock.lock();
        try {
            while (flag != 2) {
                condition.await();
            }
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName() + " :: " + i + " 轮数：" + loop);
            }
            flag = 3; //修改标志位 2
            condition.signalAll(); //通知其他线程
        } finally {
            lock.unlock();
        }
    }

    // 打印15次，参数第几轮
    public void print15(int loop) throws InterruptedException {
        lock.lock();
        try {
            while (flag != 3) {
                condition.await();
            }
            for (int i = 1; i <= 15; i++) {
                System.out.println(Thread.currentThread().getName() + " :: " + i + " 轮数：" + loop);
            }
            flag = 1; //修改标志位 2
            condition.signalAll(); //通知其他线程
        } finally {
            lock.unlock();
        }
    }
}
public class MyThreadDemo3 {

    public static void main(String[] args) {
        MyShareResource t = new MyShareResource();
        //创建线程
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    t.print5(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "AA").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    t.print10(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "BB").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    t.print15(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "CC").start();
    }
}
