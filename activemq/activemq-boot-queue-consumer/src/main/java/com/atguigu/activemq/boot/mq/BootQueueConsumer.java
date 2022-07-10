package com.atguigu.activemq.boot.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.TextMessage;

/**
 * @author shucheng
 * @date 2022/7/5 22:56
 */
@Component
public class BootQueueConsumer {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @JmsListener(destination = "${myqueue}")
    public void receive(TextMessage textMessage) throws JMSException {
        logger.info("*** 消费者收到消息：{} ***", textMessage.getText());
    }
}
