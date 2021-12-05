package com.atguigu.juc;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by shucheng on 2021/12/4 23:13
 */
public class CyclicBarrierDemo {
    public static final int NUMBER = 7;

    //集齐7颗龙珠就可以召唤神龙
    /**
     * 版本1：不用CyclicBarrier如何实现
     */
    /*static ReentrantLock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();
    static int count = NUMBER;

    public static void main(String[] args) {
        for (int i = 1; i <= 14; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "星龙被收集到了");
                await();
            }, String.valueOf(i)).start();
        }
    }

    public static void await() {
        lock.lock();
        try {
            count--;
            if (count == 0) {
                // 只要等待线程数量达到NUMBER个，就执行下面的打印，然后唤醒所有线程执行下一轮操作
                System.out.println("*****集齐7颗龙珠就可以召唤神龙");
                Thread.sleep(100);
                condition.signalAll();

                // 重置计数
                count = NUMBER;
            } else {
                condition.await();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }*/

    /**
     * CyclicBarrier主要就是封装了ReentrantLock的lock、unlock、newCondition的使用
     * @param args
     */
    public static void main(String[] args) {
        // 创建CyclicBarrier
        CyclicBarrier cyclicBarrier = new CyclicBarrier(NUMBER, () -> System.out.println("*****集齐7颗龙珠就可以召唤神龙"));
        for (int i = 1; i <= 14; i++) { // 14（用这个值可以明白Cyclic的含义）
            new Thread(() -> {
                 System.out.println(Thread.currentThread().getName() + "星龙被收集到了");
                try {
                    cyclicBarrier.await();
                    // System.out.println(Thread.currentThread().getName() + "跨越Barrier了");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }
}
