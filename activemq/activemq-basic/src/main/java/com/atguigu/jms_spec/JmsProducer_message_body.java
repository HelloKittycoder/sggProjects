package com.atguigu.jms_spec;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author shucheng
 * @date 2022/6/23 5:23
 */
public class JmsProducer_message_body {

    public static final String ACTIVEMQ_URL = "tcp://192.168.21.134:61616";
    public static final String QUEUE_NAME = "jms_queue_body";
    // public static final String TOPIC_NAME = "jms_topic";

    public static void main(String[] args) throws JMSException {
        // 1.创建连接工厂
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        // 2.获取连接
        Connection connection = connectionFactory.createConnection();
        connection.start();
        // 3.获取session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 4.创建消息目的地
        Destination destination = session.createQueue(QUEUE_NAME);
        // Destination destination = session.createTopic(TOPIC_NAME);
        // 5.创建生产者
        MessageProducer producer = session.createProducer(destination);
        // 6.创建消息
        TextMessage textMessage = session.createTextMessage("哈哈哈");
        producer.send(textMessage, Message.DEFAULT_DELIVERY_MODE, 2, Message.DEFAULT_TIME_TO_LIVE);
        MapMessage mapMessage = session.createMapMessage();
        mapMessage.setString("name", "张三");
        mapMessage.setInt("age", 18);
        producer.send(mapMessage, Message.DEFAULT_DELIVERY_MODE, 6, Message.DEFAULT_TIME_TO_LIVE);

        producer.close();
        session.close();
        connection.close();
    }
}
