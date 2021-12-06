package com.atguigu.pool;

import java.util.concurrent.*;

/**
 * Created by shucheng on 2021/12/6 12:08
 */
// 自定义线程池创建
public class ThreadPoolDemo2 {
    /**
     * 7大参数：
     * corePoolSize 核心线程数量（常驻线程）
     * maximumPoolSize 最大线程数量
     * keepAliveTime 存活时间（这里指的是临时创建出来的线程的存活时间）
     * unit 存活时间的时间单位
     * workQueue 工作队列
     * threadFactory 线程工厂（负责线程池中线程的创建）
     * handler 拒绝策略
     *
     * 工作流程说明：
     * 假设参数如下
     * corePoolSize：2
     * maximumPoolSize：5
     * workQueue：容量为3
     * handler：AbortPolicy
     * 假设1个线程1秒就处理1个请求
     *
     * 1秒内来了10个请求，先用corePoolSize那2个线程去处理2个请求，因为请求总数超过了核心线程数，
     * 多出来的请求会先进到workQueue，由于workQueue的容量为3，最多放3个请求，
     * 那么还剩下5个请求，由于maximumPoolSize为5，此时线程池会再创建3个线程来处理其中3个请求，
     *
     * 最后还剩下2个请求，此时workQueue已经满了，线程池的线程数量已经达到了maximumPoolSize，这时开始执行拒绝策略，
     * 由于拒绝策略是AbortPolicy，最后2个请求会被直接丢弃
     */
    public static void main(String[] args) {
        ExecutorService threadPool = new ThreadPoolExecutor(2,
                5,
                2L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());

        // 10个顾客请求
        try {
            for (int i = 1; i <= 10; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + " 办理业务");
                });
            }
        } finally {
            threadPool.shutdown();
        }
    }
}
