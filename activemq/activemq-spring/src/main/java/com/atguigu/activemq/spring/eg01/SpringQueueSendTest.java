package com.atguigu.activemq.spring.eg01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * @author shucheng
 * @date 2022/7/1 7:55
 */
public class SpringQueueSendTest {

    public static final Logger LOGGER = LoggerFactory.getLogger(SpringQueueSendTest.class);

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("eg01/spring-queue-producer.xml");
        SpringQueueProducer producer = context.getBean(SpringQueueProducer.class);
        JmsTemplate template = producer.getTemplate();
        template.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                // 模拟业务处理
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return session.createTextMessage("哈哈哈，测试activemq和spring整合");
            }
        });
        LOGGER.info("消息发送成功");
    }
}
