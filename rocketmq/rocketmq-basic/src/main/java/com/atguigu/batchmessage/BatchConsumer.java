package com.atguigu.batchmessage;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 批量消息消费者
 * @author shucheng
 * @date 2022/8/3 22:21
 */
public class BatchConsumer {

    public static final Logger LOGGER = LoggerFactory.getLogger(BatchConsumer.class);

    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("batchConsumerGroup");
        consumer.setNamesrvAddr("192.168.21.135:9876");
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        consumer.subscribe("someTopic", "*");

        // 指定每次可以消费10条消息，默认为1
        consumer.setConsumeMessageBatchMaxSize(10);
        // 指定每次可以从Broker拉取40条消息，默认为32
        consumer.setPullBatchSize(40);

        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                LOGGER.info("收到{}条消息，准备消费...", msgs.size());
                for (MessageExt msg : msgs) {
                    LOGGER.info("读取消息：{}，消息内容为：{}", msg, new String(msg.getBody()));
                }

                LOGGER.info("{}条消息，消费完毕", msgs.size());
                // 消费成功时的返回结果
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                // 消费异常时的返回结果
                // return ConsumeConcurrentlyStatus.RECONSUME_LATER;
            }
        });

        consumer.start();
        LOGGER.info("Consumer Started");
    }
}
