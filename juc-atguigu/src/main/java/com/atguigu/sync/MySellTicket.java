package com.atguigu.sync;

/**
 * 自己尝试写的
 * 这里有可能出现两个人买同一张票的情况
 * Created by shucheng on 2021/11/28 16:15
 */
public class MySellTicket {
    private int ticketNum = 100;

    public synchronized void sell() {
        if (ticketNum > 0) {
            try {
                Thread.sleep(100);
                System.out.println(Thread.currentThread().getName() + "买了第" + ticketNum + "张票");
                ticketNum--;
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) {
        MySellTicket s = new MySellTicket();
        int threadNum = 10000;
        Thread[] threads = new Thread[threadNum];
        for (int i = 0; i < threadNum; i++) {
            threads[i] = new Thread(()->{
                for (int j = 0; j < 100; j++) {
                    s.sell();
                }
            });
        }

        for (int i = 0; i < threadNum; i++) {
            threads[i].start();
        }
        // System.out.println(s.ticketNum);
    }
}
