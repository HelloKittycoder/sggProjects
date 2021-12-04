package com.atguigu.juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 演示CountDownLatch
 * Created by shucheng on 2021/12/4 21:35
 */
public class CountDownLatchDemo {
    // 6个同学陆续离开教室之后，班长锁门
    /**
     * 版本1：最先想到的写法，这样是有问题的
     */
    /*public static void main(String[] args) {
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " 号同学离开了教室");
            }, String.valueOf(i)).start();
        }
        System.out.println(Thread.currentThread().getName() + " 班长锁门走人了");
    }*/

    /**
     * 版本2：实现了功能
     * 这里注意不要用int[]自己来做加法，这会有线程安全问题（classMateNum为6可能看不出来，调到100就会发现有问题）
     * 要用juc提供的AtomicInteger类来做加法
     */
    /*public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        // int[] k = new int[1];
        int classMateNum = 100;
        for (int i = 1; i <= classMateNum; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " 号同学离开了教室");
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                atomicInteger.incrementAndGet();
                // k[0]++;
            }, String.valueOf(i)).start();
        }
        while (atomicInteger.get() < classMateNum) {}
        // while (k[0] < classMateNum) {}
        System.out.println(Thread.currentThread().getName() + " 班长锁门走人了");
    }*/

    /**
     * 版本3：用CountDownLatch实现
     */
    public static void main(String[] args) {
        // 设置计数器的初始值为6
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " 号同学离开了教室");
                // 计数器减1
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }
        // 只要计数器的值不为0，则main线程处于等待状态
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " 班长锁门走人了");
    }
}
