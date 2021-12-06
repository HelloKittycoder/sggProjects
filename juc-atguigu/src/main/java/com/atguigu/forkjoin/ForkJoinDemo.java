package com.atguigu.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * Created by shucheng on 2021/12/6 20:10
 */
class MyTask extends RecursiveTask<Integer> {

    // 拆分区间的端点差值不超过10
    public static final int VALUE = 10;
    private int begin; // 拆分开始值
    private int end; // 拆分结束值
    private int result; // 返回结果

    // 创建有参数构造
    public MyTask(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    // 拆分和合并过程
    @Override
    protected Integer compute() {
        // 判断需要做加法的区间的端点差值是否大于10
        // System.out.printf("begin:%s end:%s\n", begin, end);
        if ((end - begin) <= VALUE) {
            // 相加
            for (int i = begin; i <= end; i++) {
                result += i;
            }
        } else { // 进一步拆分
            // 获取中间值
            int mid = (begin + end) / 2;
            // 拆分左边
            MyTask task01 = new MyTask(begin, mid);
            // 拆分右边
            MyTask task02 = new MyTask(mid + 1, end);
            // 调用方法拆分
            task01.fork();
            task02.fork();
            // 合并结果
            result = task01.join() + task02.join();
        }
        return result;
    }
}

public class ForkJoinDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 创建MyTask对象
        MyTask myTask = new MyTask(0, 12);
        // 创建分支合并池对象
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Integer> forkJoinTask = forkJoinPool.submit(myTask);
        // 获取最终合并之后的结果
        Integer result = forkJoinTask.get();
        System.out.println(result);
        // 关闭池对象
        forkJoinPool.shutdown();
    }
}
