package com.atguigu.simple;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;

/**
 * @author shucheng
 * @date 2022/6/22 7:39
 */
public class JmsProducer_topic {

    private static final Logger LOG = LoggerFactory.getLogger(JmsProducer_topic.class);

    public static final String ACTIVEMQ_URL = "tcp://192.168.21.134:61616";
    public static final String TOPIC_NAME = "topic01";

    public static void main(String[] args) throws JMSException {
        // 1.按照给定的url创建连接工厂，这个构造器采用默认的用户名密码
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        // 2.通过连接工厂，获得连接connection，并启动访问
        Connection connection = connectionFactory.createConnection();
        connection.start();
        LOG.info("create connection {}", connection);
        // 3.创建会话session。第1个参数是是否开启事务，第2个参数是消息签收的方式
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 4.创建目的地（两种：队列/主题）
        Topic topic = session.createTopic(TOPIC_NAME);
        // 5.创建消息的生产者
        MessageProducer messageProducer = session.createProducer(topic);
        // 6.通过 messageProducer 生成3条消息发送到消息队列中
        for (int i = 1; i <= 6; i++) {
            // 7.创建消息
            TextMessage textMessage = session.createTextMessage("msg--" + i);
            // 8.通过 messageProducer 发送给mq
            messageProducer.send(textMessage);
        }
        // 9.关闭资源
        messageProducer.close();
        session.close();
        connection.close();
        System.out.println(" *** TOPIC_NAME消息发送到MQ完成 ***");
    }
}
