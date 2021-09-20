package com.atguigu.case2;

import org.apache.zookeeper.KeeperException;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by shucheng on 2021/9/20 18:14
 */
public class DistributedLockTest {
    public static void main(String[] args) throws InterruptedException, IOException, KeeperException {
        DistributedLock lock1 = new DistributedLock();
        DistributedLock lock2 = new DistributedLock();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程1启动");
                lock1.lock();
                System.out.println("线程1获取到锁");
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock1.unlock();
                System.out.println("线程1 释放锁");
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程2启动");
                lock2.lock();
                System.out.println("线程2获取到锁");
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock2.unlock();
                System.out.println("线程2 释放锁");
            }
        }).start();
    }
}
