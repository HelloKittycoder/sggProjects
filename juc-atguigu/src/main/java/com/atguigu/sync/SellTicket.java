package com.atguigu.sync;

/**
 * 多线程模拟售票案例
 * Created by shucheng on 2021/11/28 19:07
 */
// 第一步 创建资源类，定义属性和操作方法
class Ticket {
    private int number = 30;
    public synchronized void sell() {
        // 判断：是否有票
        if (number > 0) {
            /**
             * 课程里没有这一段（这段我另外加的，主要是为了制造多个线程之间的竞争）
             * 不加下面sleep的话，这里就算没有synchronized，也会发现能正常运行，没出现超卖的情况
             */
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "==>卖出：" + (number--) + " 剩下：" + number);
        }
    }
}

public class SellTicket {
    // 第二步 创建多个线程，调用资源类的操作方法
    public static void main(String[] args) {
        // 创建Ticket对象
        Ticket ticket = new Ticket();
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
