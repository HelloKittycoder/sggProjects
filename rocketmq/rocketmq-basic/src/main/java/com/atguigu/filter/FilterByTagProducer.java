package com.atguigu.filter;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 定义Tag过滤Producer
 * @author shucheng
 * @date 2022/8/4 7:06
 */
public class FilterByTagProducer {

    public static final Logger LOGGER = LoggerFactory.getLogger(FilterByTagProducer.class);

    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("filterProducerGroup");
        producer.setNamesrvAddr("192.168.21.135:9876");
        producer.start();

        String[] tags = new String[]{"myTagA", "myTagB", "myTagC"};
        for (int i = 0; i < 10; i++) {
            byte[] body = ("Hi," + i).getBytes();
            String tag = tags[i % tags.length];
            Message message = new Message("filterTagTopic", tag, body);
            SendResult sendResult = producer.send(message);
            LOGGER.info("消息发送结果为：{}", sendResult);
        }
        producer.shutdown();
    }
}
