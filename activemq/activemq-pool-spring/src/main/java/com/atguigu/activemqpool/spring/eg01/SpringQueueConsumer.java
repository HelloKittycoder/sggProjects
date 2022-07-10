package com.atguigu.activemqpool.spring.eg01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

/**
 * @author shucheng
 * @date 2022/7/9 10:27
 */
@Service
public class SpringQueueConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringQueueConsumer.class);

    @Autowired
    private JmsTemplate jmsTemplate;

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("eg01/spring-queue-config.xml");
        SpringQueueConsumer springQueueConsumer = (SpringQueueConsumer) context.getBean("springQueueConsumer");
        String message = (String) springQueueConsumer.jmsTemplate.receiveAndConvert();
        LOGGER.info("收到消息：{}", message);
    }
}
