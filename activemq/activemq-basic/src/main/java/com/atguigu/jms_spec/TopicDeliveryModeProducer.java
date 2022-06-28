package com.atguigu.jms_spec;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author shucheng
 * @date 2022/6/26 21:42
 */
public class TopicDeliveryModeProducer {

    public static final String ACTIVEMQ_URL = "tcp://192.168.21.134:61616";
    public static final String TOPIC_NAME = "topic_persist";

    public static void main(String[] args) throws JMSException {
        // 1.创建连接工厂
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        // 2.获取连接
        Connection connection = connectionFactory.createConnection();
        // 3.获取session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 4.创建消息目的地
        Destination destination = session.createTopic(TOPIC_NAME);
        // 5.创建生产者
        MessageProducer producer = session.createProducer(destination);
        /**
         * NON_PERSISTENT 非持久：宕机的话，消息就找不到了
         * PERSISTENT 非持久：宕机的话，消息还能找到
         *
         * TOPIC：这个要持久化的话，需要生产者和消费者同时配置
         * 生产者默认是非持久的（如果消费者在生产者之后启动，则消费者收不到消息）
         * 在设置成持久化后，还需要消费者先于生产者注册到mq上，如果在这之后消费者不在线，但是生产者发了消息，消费者上线就能收到消息
         */
        producer.setDeliveryMode(DeliveryMode.PERSISTENT);
        connection.start();
        // 6.创建消息
        for (int i = 1; i <= 3; i++) {
            TextMessage textMessage = session.createTextMessage("哈哈哈，topic" + i);
            producer.send(textMessage);
        }

        producer.close();
        session.close();
        connection.close();
    }
}
