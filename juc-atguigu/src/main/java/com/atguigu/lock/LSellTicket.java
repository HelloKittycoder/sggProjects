package com.atguigu.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 多线程模拟售票案例
 * Created by shucheng on 2021/11/28 20:09
 */
// 第一步 创建资源类，定义属性和操作方法
class LTicket {
    private int number = 30;
    private Lock lock = new ReentrantLock(true);
    public void sell() {
        // 上锁
        lock.lock();
        try {
            // 判断：是否有票
            if (number > 0) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "==>卖出：" + (number--) + " 剩下：" + number);
            }
        } finally {
            // 解锁
            lock.unlock();
        }
    }
}

public class LSellTicket {
    // 第二步 创建多个线程，调用资源类的操作方法
    public static void main(String[] args) {
        // 创建LTicket对象
        LTicket ticket = new LTicket();
        // 创建三个线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 调用卖票方法
                for (int i = 0; i < 40; i++) {
                    ticket.sell();
                }
            }
        }, "AA").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                // 调用卖票方法
                for (int i = 0; i < 40; i++) {
                    ticket.sell();
                }
            }
        }, "BB").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                // 调用卖票方法
                for (int i = 0; i < 40; i++) {
                    ticket.sell();
                }
            }
        }, "CC").start();
    }
}
