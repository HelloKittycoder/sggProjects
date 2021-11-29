package com.atguigu.sync;

/**
 * 需要实现的效果：
 * number初始值为0，
 * 多个线程对number进行操作，如果number为0，则对其+1；如果number为1，则对其-1
 *
 * 说明：
 * 为什么increment和decrement用if来写会出问题？
 * 1.AA线程获得锁，先执行过increment，number变成1了，AA通知其他所有线程
 * 2.AA获得锁，执行increment，此时因为number为1，进入wait，释放锁
 * 3.CC获得锁，执行increment，此时因为number为1，进入wait，释放锁
 * 4.BB获得锁，执行decrement，number变成0，BB通知其他所有线程
 * 假如AA被唤醒，获得锁，AA继续执行number++，打印1，AA通知其他所有线程，又唤醒了CC
 * CC被唤醒，获得锁，继续执行number++，打印2（这时就已经不符合要求了）
 *
 * 如果用while来写，上面会是什么情况呢？
 * 1.AA线程获得锁，先执行过increment，number变成1了，AA通知其他所有线程
 * 2.AA获得锁，执行increment，此时因为number为1，进入wait，释放锁
 * 3.CC获得锁，执行increment，此时因为number为1，进入wait，释放锁
 * 4.BB获得锁，执行decrement，number变成0，BB通知其他所有线程
 * 假如AA被唤醒，获得锁，AA再次检查条件发现number已经是0了，继续执行number++，打印1，AA通知其他所有线程，又唤醒了CC
 * CC被唤醒，获得锁，再次检查条件发现number不是0，还需要再wait
 *
 * Created by shucheng on 2021/11/28 22:27
 */
// 第一步 创建资源类，定义属性和操作方法
class Share {
    // 初始值
    private int number = 0;

    // +1的方法
    public synchronized void increment() throws InterruptedException {
        // 第二步 判断 干活 通知
        while (number != 0) { // 判断number值是否为0，如果不为0，则等待
            this.wait(); //在哪里睡，就在哪里醒
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
        while (number != 1) {
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

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    share.increment(); // -1
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "CC").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    share.decrement(); // -1
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "DD").start();
    }
}
