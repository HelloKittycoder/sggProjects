package com.atguigu.activemq.boot.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.Topic;
import java.util.UUID;

/**
 * @author shucheng
 * @date 2022/7/5 23:17
 */
@Component
public class BootTopicProducer {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Topic topic;

    // 发送消息
    public void produceMessage() {
        String message = "****"
                + UUID.randomUUID().toString().substring(0, 6) + "springboot给topic发消息";
        jmsMessagingTemplate.convertAndSend(topic, message);
        logger.info("消息 {} 发送完成...", message);
    }

    /**
     * 定时任务。每3秒执行一次。非必须代码，仅为演示。
     */
    @Scheduled(initialDelay = 3000, fixedDelay = 3000)
    public void produceMessageScheduled() {
        logger.info("produceMessageScheduled被调用...");
        produceMessage();
    }
}
