package com.atguigu.jms_spec;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author shucheng
 * @date 2022/6/23 7:56
 */
public class QueueDeliveryModeProducer {

    public static final String ACTIVEMQ_URL = "tcp://192.168.21.134:61616";
    public static final String QUEUE_NAME = "jms_delivery_mode";

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
        // 5.创建生产者
        MessageProducer producer = session.createProducer(destination);
        /**
         * NON_PERSISTENT 非持久：宕机的话，消息就找不到了
         * PERSISTENT 持久：宕机的话，消息还能找到
         *
         * Queue默认是持久化的（这个只要生产者配置即可）
         */
        producer.setDeliveryMode(DeliveryMode.PERSISTENT);
        // 6.创建消息
        TextMessage textMessage = session.createTextMessage("哈哈哈");
        producer.send(textMessage);

        producer.close();
        session.close();
        connection.close();
    }
}
