package com.atguigu.simple;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 单向消息发送生产者
 * @author shucheng
 * @date 2022/7/30 22:03
 */
public class OnewayProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(OnewayProducer.class);

    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("onewayProducerGroup");
        producer.setNamesrvAddr("192.168.21.135:9876");
        producer.start();

        for (int i = 0; i < 10; i++) {
            byte[] body = ("Hi," + i).getBytes();
            Message msg = new Message("single", "someTag", body);
            // 单向发送
            producer.sendOneway(msg);
        }
        producer.shutdown();
        LOGGER.info("producer shutdown");
        // System.out.println("producer shutdown");
    }
}
