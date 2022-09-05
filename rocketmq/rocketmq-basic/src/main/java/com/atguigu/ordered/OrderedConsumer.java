package com.atguigu.ordered;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author shucheng
 * @date 2022/7/31 14:58
 */
public class OrderedConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderedConsumer.class);

    public static void main(String[] args) throws Exception {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("orderedConsumerGroupTest");
        consumer.setNamesrvAddr("192.168.21.135:9876");
        consumer.subscribe("someTopic", "*");

        /**
         * 下面这种是顺序消费（保证单个Consumer这边只有单个线程在消费），但是很奇怪：
         * （1）生产者发送消息后，需要等几分钟消费者才能消费到，原因还不清楚
         * （2）从日志来看，也不是同一时刻只有单个线程在消费
         */
        consumer.registerMessageListener(new MessageListenerOrderly() {
            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
                for (MessageExt msg : msgs) {
                    LOGGER.info("收到消息：{}，内容：{}", msg, new String(msg.getBody()));
                    /*try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }*/
                    // System.out.println(msg);
                }
                return null;
            }
        });

        consumer.start();
        LOGGER.info("Consumer Started");
        // System.out.println("Consumer Started");
    }
}
