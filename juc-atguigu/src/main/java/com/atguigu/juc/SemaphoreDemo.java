package com.atguigu.juc;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Created by shucheng on 2021/12/5 12:21
 */
public class SemaphoreDemo {

    // 6辆汽车，停3个车位
    /**
     * 2进入车位
     * 1进入车位
     * 4进入车位
     * 2----离开了车位
     * 5进入车位
     * 5----离开了车位
     * 3进入车位
     * 4----离开了车位
     * 6进入车位
     * 6----离开了车位
     * 1----离开了车位
     * 3----离开了车位
     *
     * 解释：
     * 车位A  2--5--3
     * 车位B  1
     * 车位C  4--6
     */
    public static void main(String[] args) {
        // 创建Semaphore，设置许可数量
        Semaphore semaphore = new Semaphore(3);
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                try {
                    // 抢占
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "进入车位");
                    // 设置随机停车时间
                    TimeUnit.SECONDS.sleep(new Random().nextInt(5));
                    System.out.println(Thread.currentThread().getName() + "----离开了车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // 释放
                    semaphore.release();
                }
            }, String.valueOf(i)).start();
        }
    }
}
