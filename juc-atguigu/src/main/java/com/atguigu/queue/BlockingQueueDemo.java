package com.atguigu.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by shucheng on 2021/12/5 16:53
 */
public class BlockingQueueDemo {

    public static void main(String[] args) throws InterruptedException {
        // 创建阻塞队列
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);

        // 第一组
        /*System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));
        // System.out.println(blockingQueue.element());
        // System.out.println(blockingQueue.add("w"));

        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        // System.out.println(blockingQueue.remove());*/

        // 第二组
        /*System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        System.out.println(blockingQueue.offer("w"));

        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());*/

        // 第三组
        /*blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c");
        // blockingQueue.put("w");

        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());*/

        // 第四组
        /*System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        System.out.println(blockingQueue.offer("w", 3L, TimeUnit.SECONDS));*/

        // 对课程中第三组举的例子简单做了下调整
        new Thread(() -> {
            try {
                blockingQueue.put("a");
                blockingQueue.put("b");
                blockingQueue.put("c");
                System.out.println(Thread.currentThread().getName() + "3秒后准备向blockingQueue中放一个元素...");
                Thread.sleep(3000);
                blockingQueue.put("w");
            } catch (InterruptedException e) {
            }
        }, "A").start();

        new Thread(() -> {
            try {
                System.out.println(blockingQueue.take());
                System.out.println(blockingQueue.take());
                System.out.println(blockingQueue.take());
                /**
                 * 前3个元素取出以后，此时队列为空，再调用take方法，此时线程B将会阻塞；
                 * 由于线程A在3秒后又往blockingQueue中放了一个元素，线程A正常结束，
                 * 此时线程B被唤醒，从blockingQueue中取到了一个元素，线程B正常结束
                 */
                System.out.println(Thread.currentThread().getName() + "即将被阻塞....");
                String take = blockingQueue.take();
                System.out.println(Thread.currentThread().getName() + "成功取到元素：" + take);
            } catch (InterruptedException e) {
            }
        }, "B").start();
    }
}
