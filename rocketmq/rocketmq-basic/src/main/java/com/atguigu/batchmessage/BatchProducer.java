package com.atguigu.batchmessage;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 批量消息生产者
 * @author shucheng
 * @date 2022/8/3 22:13
 */
public class BatchProducer {

    public static final Logger LOGGER = LoggerFactory.getLogger(BatchProducer.class);

    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("batchProducerGroup");
        producer.setNamesrvAddr("192.168.21.135:9876");
        // 指定要发送的消息的最大大小，默认是4M
        // 不过，仅修改该属性是不行的，还需要同时修改broker加载的配置文件中的maxMessageSize属性
        // producer.setMaxMessageSize(8 * 1024 * 1024);
        producer.start();

        // 定义要发送的消息集合
        List<Message> messages = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            byte[] body = ("Hi," + i).getBytes();
            Message msg = new Message("someTopic", "someTag", body);
            messages.add(msg);
        }

        // 定义消息列表分割器，将消息列表分割为多个不超出4M大小的小列表
        MessageListenerSplitter splitter = new MessageListenerSplitter(messages);
        while (splitter.hasNext()) {
            try {
                List<Message> listItem = splitter.next();
                LOGGER.info("准备批量发送的消息数量为：{}", listItem.size());
                producer.send(listItem);
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
        producer.shutdown();
    }
}
