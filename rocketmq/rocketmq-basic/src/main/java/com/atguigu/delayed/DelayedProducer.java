package com.atguigu.delayed;

import com.atguigu.ordered.OrderedConsumer;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author shucheng
 * @date 2022/7/31 22:35
 */
public class DelayedProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderedConsumer.class);

    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("delayedProducerGroup");
        producer.setNamesrvAddr("192.168.21.135:9876");
        producer.start();

        for (int i = 0; i < 1; i++) {
            byte[] body = ("Hi," + i).getBytes();
            Message msg = new Message("TopicB", "someTag", body);
            // 指定消息延时等级为3，即延迟10s
            // msg.setDelayTimeLevel(3);

            SendResult sendResult = producer.send(msg);
            LOGGER.info("消息发送结果为：{}", sendResult);
        }
        producer.shutdown();
    }
}
