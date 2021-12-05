package com.atguigu.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by shucheng on 2021/12/5 22:12
 */
// 演示线程池三种常用分类
public class ThreadPoolDemo1 {
    public static void main(String[] args) {
        // 一池五线程
        // ExecutorService threadPool1 = Executors.newFixedThreadPool(5); // 5个窗口

        // 一池一线程
        // ExecutorService threadPool2 = Executors.newSingleThreadExecutor();// 1个窗口

        // 一池可扩容线程
        ExecutorService threadPool3 = Executors.newCachedThreadPool();

        // 10个顾客请求
        try {
            for (int i = 1; i <= 10; i++) {
                // 执行
                threadPool3.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + " 办理业务");
                });
            }
        } finally {
            // 关闭
            threadPool3.shutdown();
        }
    }
}
