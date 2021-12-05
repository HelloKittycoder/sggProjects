package com.atguigu.readwrite;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by shucheng on 2021/12/5 16:00
 */
// 演示读写锁降级
public class Demo1 {
    public static void main(String[] args) {
        // 可重入读写锁对象
        ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = rwLock.readLock(); // 读锁
        ReentrantReadWriteLock.WriteLock writeLock = rwLock.writeLock(); // 写锁

        /**
         * 锁降级（某个线程一开始获取了写锁，后面可以降级为读锁，即先1后2；
         * 但是如果一开始获取的是读锁，后面是不能升级为读锁的，即先2后1，
         * 只能说获取读锁、释放读锁，然后再获取写锁、释放写锁，即2、4、1、3）
         */
        // 1 获取写锁
        writeLock.lock();
        System.out.println("---write");

        // 2 获取读锁
        readLock.lock();
        System.out.println("---read");

        // 3 释放写锁
        writeLock.unlock();

        // 4 释放读锁
        readLock.unlock();
    }
}
