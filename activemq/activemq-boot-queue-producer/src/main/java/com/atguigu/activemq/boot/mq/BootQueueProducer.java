package com.atguigu.activemq.boot.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.Queue;
import java.util.UUID;

/**
 * @author shucheng
 * @date 2022/7/5 8:05
 */
@Component
public class BootQueueProducer {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Queue queue;

    // 发送消息
    public void produceMessage() {
        String message = "****"
                + UUID.randomUUID().toString().substring(0, 6) + "springboot给queue发消息";
        jmsMessagingTemplate.convertAndSend(queue, message);
        logger.info("消息 {} 发送完成...", message);
    }

    /**
     * 定时任务。每3秒执行一次。非必须代码，仅为演示。
     */
    @Scheduled(fixedDelay = 3000)
    public void produceMessageScheduled() {
        logger.info("produceMessageScheduled被调用...");
        produceMessage();
    }
}
