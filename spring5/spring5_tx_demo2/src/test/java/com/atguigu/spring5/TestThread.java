package com.atguigu.spring5;

/**
 * 多线程join的使用
 * 参考链接：https://blog.csdn.net/weixin_42516005/article/details/114097870
 * Created by shucheng on 2021/8/1 20:27
 */
public class TestThread {

    public static void main(String[] args) {
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "正在执行...");
            }
        }, "t2");
        Thread t1 = new ThreadJoinDemo("t1", t2);
        t1.start();
        t2.start();
    }

    static class ThreadJoinDemo extends Thread {
        private Thread anotherThread;

        public ThreadJoinDemo(String name, Thread anotherThread) {
            this.anotherThread = anotherThread;
            super.setName(name);
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "正在执行");
            System.out.println(Thread.currentThread().getName() + "等待" + anotherThread.getName());
            try {
                anotherThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "继续执行");
        }
    }
}
