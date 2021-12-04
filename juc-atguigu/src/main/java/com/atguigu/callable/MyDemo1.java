package com.atguigu.callable;

import java.util.Random;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * FutureTask总结：（课程中举的例子可以忽略，基本都没说清楚）
 * 1.为什么要用FutureTask？见下面main方法的两种写法
 * 原因就是：用起来更方便，进行多线程开发时不用自己操作相对底层的api
 *
 * 2.FutureTask和Callable什么关系？Callable是创建FutureTask对象时要传的一个参数，
 * 该参数就是提供具体计算过程及返回值的
 * FutureTask是Runnable接口的实现类，它的功能比Runnable接口更多，如：Runnable接口不支持返回值，FutureTask支持返回值
 *
 * 3.get()和get(long,TimeUnit)：这两个方法都会让main线程处于等待中，这里等待是通过无限for循环实现的，
 * 这两个方法的区别是，一个是无限等待，直到有计算结果，另一个是只等待特定时间，如果超过了时间范围，则报超时异常（TimeoutException）；
 * 另外注意一点，如果调用1次get方法得到结果了，后面再调用的时候，就不会再计算了，而是直接返回结果（因为计算线程已经结束了）
 *
 * 4.isDone()：直接实时返回该futureTask是否完成，不会产生等待
 *
 * Created by shucheng on 2021/12/4 20:40
 */
public class MyDemo1 {
    /**
     * 下面表示有1项计算任务，执行时间不确定，可能的时间范围为 [0,5)，单位为秒
     * @throws Exception
     */
    public int calcuate() {
        Random random = new Random();
        try {
            TimeUnit.SECONDS.sleep(random.nextInt(5));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 1;
    }

    /**
     * 不用FutureTask时如何实现
     * 这里要解决两个问题：1.Runnable本身是不支持返回值的，如何让main线程能拿到返回值（用数组）
     * 2.新开的线程t执行时间不确定，如何让main线程在获取线程t计算任务执行结果时能够等待该计算任务结束（用join）
     * @param args
     */
    /*public static void main(String[] args) {
        MyDemo1 md = new MyDemo1();
        int[] result = new int[1];

        // 开启一个线程t执行计算任务
        Thread t = new Thread(() -> {
            result[0] = md.calcuate();
        }, "t");
        t.start();

        // 获取计算结果
        // 让main线程等待线程t执行结束
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 打印计算结果
        System.out.println(result[0]);
    }*/

    /**
     * 使用FutureTask如何实现
     * 上面的两个问题FutureTask是如何解决的？
     * 1.FutureTask是Runnable的实现，它增强了Runnable的功能，原来Runnable是不支持返回值的，
     * FutureTask现在通过传入Callable来支持返回值
     * 2.调用FutureTask对象的get()方法，会让main线程处于无限等待中，直到线程t计算完成
     */
    public static void main(String[] args) throws Exception {
        MyDemo1 md = new MyDemo1();
        // 定义一个计算任务
        FutureTask<Integer> futureTask = new FutureTask<>(() -> md.calcuate());

        // 开启一个线程t执行计算任务
        Thread t = new Thread(futureTask, "t");
        t.start();

        // 中途可以检查任务是否完成，如果没完成，可以做别的事
        /*while (!futureTask.isDone()) {
            System.out.println("wait...");
        }*/

        // 获取计算结果
        Integer result = futureTask.get();

        // 打印计算结果
        System.out.println(result);
        // System.out.println(futureTask.get());
    }
}
