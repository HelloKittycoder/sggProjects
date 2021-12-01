package com.atguigu.lock;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by shucheng on 2021/12/1 21:53
 */
public class ThreadDemo4 {
    public static void main(String[] args) {
        // 创建ArrayList（直接用这个是线程不安全的）
        // List<String> list = new ArrayList<>();

        // Vector解决
        // List<String> list = new Vector<>();

        // Collections解决
        // List<String> list = Collections.synchronizedList(new ArrayList<>());

        // CopyOnWriteArrayList解决
        List<String> list = new CopyOnWriteArrayList<>();

        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                // 向集合添加内容
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(Thread.currentThread().getName() + "::" + list);
            }, String.valueOf(i)).start();
        }
    }
}
