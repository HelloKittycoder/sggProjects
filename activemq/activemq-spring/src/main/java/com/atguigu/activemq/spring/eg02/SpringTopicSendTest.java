package com.atguigu.activemq.spring.eg02;

import com.atguigu.activemq.spring.eg01.SpringQueueProducer;
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
 * @date 2022/7/4 22:32
 */
public class SpringTopicSendTest {

    public static final Logger LOGGER = LoggerFactory.getLogger(SpringTopicSendTest.class);

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("eg02/spring-topic-producer.xml");
        SpringTopicProducer producer = context.getBean(SpringTopicProducer.class);
        JmsTemplate template = producer.getTemplate();
        template.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return session.createTextMessage("spring整合activemq，给topic发送消息");
            }
        });
        LOGGER.info("消息发送完毕");
    }
}
