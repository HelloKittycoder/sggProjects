package com.atguigu.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * Created by shucheng on 2021/12/4 15:40
 */
// 比较两个接口
// 实现Runnable接口
class MyThread1 implements Runnable {
    @Override
    public void run() {
    }
}

// 实现Callable接口
class MyThread2 implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName() + " enter callable");
        return 200;
    }
}

public class Demo1 {
    public static void main(String[] args) throws Exception {
        // Runnable接口创建线程
        // new Thread(new MyThread1(), "AA").start();

        /*// Callable接口创建线程，报错
        new Thread(new MyThread2(), "BB").start();*/

        FutureTask<Integer> futureTask1 = new FutureTask<>(new MyThread2());
        // lambda表达式写法
        FutureTask<Integer> futureTask2 = new FutureTask<>(()->{
            System.out.println(Thread.currentThread().getName() + " enter callable");
            return 1024;
        });

        // 创建线程
        new Thread(futureTask1, "mary").start();
        new Thread(futureTask2, "lucy").start();

        // 中途可以检查任务是否完成，如果没完成，可以做别的事
        /*while (!futureTask1.isDone()) {
            System.out.println("wait...");
        }*/

        // 获取执行结果
        System.out.println(futureTask1.get());
        System.out.println(futureTask2.get());

        System.out.println(Thread.currentThread().getName() + " is over");
    }
}
