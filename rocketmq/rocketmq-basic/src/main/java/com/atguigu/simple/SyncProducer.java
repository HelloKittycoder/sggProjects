package com.atguigu.simple;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 同步消息发送生产者
 * @author shucheng
 * @date 2022/7/30 19:54
 */
public class SyncProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(SyncProducer.class);

    public static void main(String[] args) throws Exception {
        // 创建一个producer，参数为Producer Group的名称
        DefaultMQProducer producer = new DefaultMQProducer("syncProducerGroup");
        // 指定namesrvAddr地址
        producer.setNamesrvAddr("192.168.21.135:9876");
        // 设置当发送失败时重试发送的次数，默认为2次
        producer.setRetryTimesWhenSendFailed(3);
        // 设置发送超时时限为5s，默认3s
        producer.setSendMsgTimeout(5000);

        // 开启生产者
        producer.start();

        // 生产并发送100条消息
        for (int i = 0; i < 100; i++) {
            byte[] body = ("Hi," + i).getBytes();
            Message msg = new Message("someTopic", "someTag", body);
            // 为消息指定key
            msg.setKeys("key-" + i);

            // 发送消息
            SendResult sendResult = producer.send(msg);
            LOGGER.info("消息发送结果为：{}", sendResult);
            System.out.println(sendResult);
        }
        // 关闭producer
        producer.shutdown();
    }
}
