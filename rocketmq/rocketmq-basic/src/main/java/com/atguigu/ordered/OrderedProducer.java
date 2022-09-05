package com.atguigu.ordered;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author shucheng
 * @date 2022/7/31 14:51
 */
public class OrderedProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderedProducer.class);

    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("orderedProducerGroup");
        producer.setNamesrvAddr("192.168.21.135:9876");
        producer.start();

        for (int i = 0; i < 10; i++) {
            Integer orderId = i;
            byte[] body = ("Hi," + i).getBytes();
            Message msg = new Message("TopicA", "TagA", body);
            SendResult sendResult = producer.send(msg, new MessageQueueSelector() {
                @Override
                public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                    Integer id = (Integer) arg;
                    int index = id % mqs.size();
                    MessageQueue messageQueue = mqs.get(index);
                    LOGGER.info("选取消息进行发送：id {} index {} queue {}", id, index, messageQueue);
                    return messageQueue;
                }
            }, orderId);

            LOGGER.info("消息发送结果为：{}", sendResult);
            // System.out.println(sendResult);
        }
        producer.shutdown();
    }
}
