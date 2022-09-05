package com.atguigu.simple;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author shucheng
 * @date 2022/7/30 21:37
 */
public class AsyncProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncProducer.class);

    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("asyncProducerGroup");
        producer.setNamesrvAddr("192.168.21.135:9876");
        // 指定异步发送失败后不进行重试发送
        producer.setRetryTimesWhenSendAsyncFailed(0);
        // 指定新创建的Topic的Queue数量为2，默认为4
        producer.setDefaultTopicQueueNums(2);

        producer.start();

        for (int i = 0; i < 100; i++) {
            byte[] body = ("Hi," + i).getBytes();
            Message msg = new Message("myTopicA", "myTag", body);
            producer.send(msg, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    LOGGER.info("消息发送结果为：{}", sendResult);
                    // System.out.println(sendResult);
                }

                @Override
                public void onException(Throwable e) {
                    LOGGER.info(e.getMessage(), e);
                    // e.printStackTrace();
                }
            });
        }

        System.in.read();
        producer.shutdown();
    }
}
