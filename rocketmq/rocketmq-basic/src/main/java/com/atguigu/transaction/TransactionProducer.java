package com.atguigu.transaction;

import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.common.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * 事务消息生产者
 * @author shucheng
 * @date 2022/8/1 23:21
 */
public class TransactionProducer {

    public static final Logger LOGGER = LoggerFactory.getLogger(TransactionProducer.class);

    public static void main(String[] args) throws Exception {
        TransactionMQProducer producer = new TransactionMQProducer("transactionMQProducerGroup");
        producer.setNamesrvAddr("192.168.21.135:9876");

        ExecutorService executorService = new ThreadPoolExecutor(2, 5, 100,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(2000),
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        Thread thread = new Thread(r);
                        thread.setName("client-transaction-msg-check-thread");
                        return thread;
                    }
                });

        // 为生产者指定一个线程池
        producer.setExecutorService(executorService);
        // 为生产者添加事务监听器
        producer.setTransactionListener(new ICBCTransactionListener());

        producer.start();

        String[] tags = {"TAGA", "TAGB", "TAGC"};
        for (int i = 0; i < 3; i++) {
            byte[] body = ("Hi," + i).getBytes();
            Message msg = new Message("TransactionTopic", tags[i], body);
            // 发送事务消息（第二个参数用于指定在执行本地事务时要使用的业务参数）
            TransactionSendResult sendResult = producer.sendMessageInTransaction(msg, null);
            LOGGER.info("发送结果为：{}", sendResult.getSendStatus());
        }
    }
}
