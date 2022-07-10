package com.atguigu.activemq.spring.eg02;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;

/**
 * @author shucheng
 * @date 2022/7/4 22:38
 */
public class SpringTopicReceiveTest {

    public static final Logger LOGGER = LoggerFactory.getLogger(SpringTopicReceiveTest.class);

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("eg02/spring-topic-consumer.xml");
        SpringTopicConsumer consumer = context.getBean(SpringTopicConsumer.class);
        JmsTemplate template = consumer.getTemplate();
        String message = (String) template.receiveAndConvert();
        LOGGER.info("收到消息：{}", message);
    }
}
