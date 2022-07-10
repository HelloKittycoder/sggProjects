package com.atguigu.activemq.spring.eg01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;

/**
 * @author shucheng
 * @date 2022/7/4 21:51
 */
public class SpringQueueReceiveTest {

    public static final Logger LOGGER = LoggerFactory.getLogger(SpringQueueReceiveTest.class);

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("eg01/spring-queue-consumer.xml");
        SpringQueueConsumer consumer = context.getBean(SpringQueueConsumer.class);
        JmsTemplate template = consumer.getTemplate();
        String message = (String) template.receiveAndConvert();
        LOGGER.info("消费者收到消息：{}", message);
    }
}
