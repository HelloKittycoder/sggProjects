package com.atguigu.activemqpool.spring.eg03;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * 通过监听器来接收消息
 * @author shucheng
 * @date 2022/7/9 10:27
 */
@Component
public class SpringQueueListener implements MessageListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringQueueListener.class);

    @Override
    public void onMessage(Message message) {
        LOGGER.info("收到消息：{}", message);
    }
}
