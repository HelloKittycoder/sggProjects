package com.atguigu.filter;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 定义SQL过滤Producer
 * @author shucheng
 * @date 2022/8/4 7:06
 */
public class FilterBySQLProducer {

    public static final Logger LOGGER = LoggerFactory.getLogger(FilterBySQLProducer.class);

    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("filterProducerGroup");
        producer.setNamesrvAddr("192.168.21.135:9876");
        producer.start();

        for (int i = 0; i < 10; i++) {
            byte[] body = ("Hi," + i).getBytes();
            Message message = new Message("filterSqlTopic", "myTag", body);
            message.putUserProperty("age", i + "");
            SendResult sendResult = producer.send(message);
            LOGGER.info("消息发送结果为：{}", sendResult);
        }
        producer.shutdown();
    }
}
