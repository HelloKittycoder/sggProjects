package com.atguigu.jms_spec;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;
import java.io.IOException;

/**
 * @author shucheng
 * @date 2022/6/23 5:45
 */
public class JmsConsumer_message_body {

    public static final Logger LOGGER = LoggerFactory.getLogger(JmsConsumer_message_body.class);
    public static final String ACTIVEMQ_URL = "tcp://192.168.21.134:61616";
    public static final String QUEUE_NAME = "jms_queue_body";
    // public static final String TOPIC_NAME = "jms_topic";

    public static void main(String[] args) throws JMSException, IOException {
        // 1.创建连接工厂
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        // 2.创建连接
        Connection connection = connectionFactory.createConnection();
        connection.start();
        // 3.创建session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 4.创建消息目的地
        Destination destination = session.createQueue(QUEUE_NAME);
        // Destination destination = session.createTopic(TOPIC_NAME);
        // 5.创建消费者
        MessageConsumer consumer = session.createConsumer(destination);
        // 6.接收消息
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                try {
                    if (message instanceof TextMessage) {
                        TextMessage textMessage = (TextMessage) message;
                        LOGGER.info("原始消息为：{}，消息内容为：{}", textMessage, textMessage.getText());
                    } else if (message instanceof MapMessage) {
                        MapMessage mapMessage = (MapMessage) message;
                        LOGGER.info("原始消息为：{}，消息内容为：{}", mapMessage);
                        System.out.println(mapMessage.getString("name"));
                        System.out.println(mapMessage.getInt("age"));
                    }
                } catch (JMSException e) {
                    LOGGER.error(e.getMessage(), e);
                }
            }
        });

        /*while (true) {
            TextMessage message = (TextMessage) consumer.receive();
            if (message != null) {
                LOGGER.info("消息内容为：{}", message.getText());
            } else {
                break;
            }
        }*/

        System.in.read();
        consumer.close();
        session.close();
        connection.close();
    }
}
