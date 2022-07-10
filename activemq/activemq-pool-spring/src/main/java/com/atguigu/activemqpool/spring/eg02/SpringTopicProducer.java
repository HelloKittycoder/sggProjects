package com.atguigu.activemqpool.spring.eg02;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * @author shucheng
 * @date 2022/7/9 9:50
 */
@Service
public class SpringTopicProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringTopicProducer.class);

    @Autowired
    private JmsTemplate jmsTemplate;

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("eg02/spring-topic-config.xml");
        SpringTopicProducer springTopicProducer = (SpringTopicProducer) context.getBean("springTopicProducer");
        springTopicProducer.jmsTemplate.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage("发送测试消息给 spring-activemq-pool-topic");
            }
        });
        LOGGER.info("消息发送完毕");
    }
}
