package com.atguigu.filter;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.MessageSelector;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 定义Tag过滤Consumer
 * 说明：该主题下实际只是消费了那些符合过滤条件的消息，但是实际上，就算消息不符合条件，该消息也是已消费状态
 *
 * 注意：
 * Tag过滤默认就支持，sql过滤需要手动开启（需要在broker.conf中添加 enablePropertyFilter=true 属性才能开启sql过滤）
 *
 * @author shucheng
 * @date 2022/8/4 7:07
 */
public class FilterBySQLConsumer {

    public static final Logger LOGGER = LoggerFactory.getLogger(FilterBySQLConsumer.class);

    public static void main(String[] args) throws Exception {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("filterConsumerGroup");
        consumer.setNamesrvAddr("192.168.21.135:9876");
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);

        // 只过滤age>=6 and age<=8的消息
        consumer.subscribe("filterSqlTopic", MessageSelector.bySql("age between 6 and 8"));
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                LOGGER.info("收到 {} 条消息，准备消费...", msgs.size());
                for (MessageExt msg : msgs) {
                    LOGGER.info("读取消息：{}，内容为：{}", msg, new String(msg.getBody()));
                }

                LOGGER.info("{}条消息，消费完毕", msgs.size());
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        consumer.start();
        LOGGER.info("Consumer Started");

        System.in.read();
        consumer.shutdown();
    }
}
