package com.atguigu.sync;

/**
 * Created by shucheng on 2021/11/28 22:27
 */
// 第一步 创建资源类，定义属性和操作方法
class Share {
    // 初始值
    private int number = 0;

    // +1的方法
    public synchronized void increment() throws InterruptedException {
        // 第二步 判断 干活 通知
        if (number != 0) { // 判断number值是否为0，如果不为0，则等待
            this.wait();
        }
        // 如果number值为0，就+1
        number++;
        System.out.println(Thread.currentThread().getName() + " :: " + number);
        // 通知其他线程
        this.notifyAll();
    }

    // -1的方法
    public synchronized void decrement() throws InterruptedException {
        // 第二步 判断 干活 通知
        if (number != 1) {
            this.wait();
        }
        // 如果number值为0，就-1
        number--;
        System.out.println(Thread.currentThread().getName() + " :: " + number);
        // 通知其他线程
        this.notifyAll();
    }
}

public class ThreadDemo1 {
    //第三步 创建多个线程，调用资源类的操作方法
    public static void main(String[] args) {
        Share share = new Share();
        //创建线程
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    share.increment(); // +1
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "AA").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    share.decrement(); // -1
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "BB").start();
    }
}
