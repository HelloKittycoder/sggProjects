package com.atguigu.readwrite;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by shucheng on 2021/12/5 14:32
 */
// 资源类
class MyCache {
    // 创建map集合
    private volatile Map<String, Object> map = new HashMap<>();

    // 创建读写锁对象（读读共享，读写互斥，写写互斥）
    private ReadWriteLock rwLock = new ReentrantReadWriteLock();

    // 放数据
    public void put(String key, Object value) {
        // 添加写锁
        rwLock.writeLock().lock();

        try {
            System.out.println(Thread.currentThread().getName() + " 正在执行写操作，key=" + key);
            // 暂停一会
            Thread.sleep(300);
            // 放数据
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + " 写完了，key=" + key);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 释放写锁
            rwLock.writeLock().unlock();
        }
    }

    // 取数据
    public Object get(String key) {
        // 添加读锁
        rwLock.readLock().lock();

        Object result = null;
        try {
            System.out.println(Thread.currentThread().getName() + " 正在执行读取操作，key=" + key);
            // 暂停一会
            Thread.sleep(300);
            result = map.get(key);
            System.out.println(Thread.currentThread().getName() + " 取完了，key=" + key + "，value=" + result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 释放读锁
            rwLock.readLock().unlock();
        }
        return result;
    }
}

public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        // 创建线程放数据
        for (int i = 1; i <= 5; i++) {
            final int num = i;
            new Thread(() -> {
                myCache.put(num + "", num);
            }, String.valueOf(i)).start();
        }

        // 创建线程取数据
        for (int i = 1; i <= 5; i++) {
            final int num = i;
            new Thread(() -> {
                myCache.get(num + "");
            }, String.valueOf(i)).start();
        }
    }
}
