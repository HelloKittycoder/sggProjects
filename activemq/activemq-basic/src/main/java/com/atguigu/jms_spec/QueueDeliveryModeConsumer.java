package com.atguigu.jms_spec;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;
import java.io.IOException;

/**
 * @author shucheng
 * @date 2022/6/23 7:57
 */
public class QueueDeliveryModeConsumer {

    public static final Logger LOGGER = LoggerFactory.getLogger(QueueDeliveryModeConsumer.class);
    public static final String ACTIVEMQ_URL = "tcp://192.168.21.134:61616";
    public static final String QUEUE_NAME = "jms_delivery_mode";

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
        // 5.创建消费者
        MessageConsumer consumer = session.createConsumer(destination);
        // 6.接收消息
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                try {
                    TextMessage textMessage = (TextMessage) message;
                    LOGGER.info("原始消息为：{}，消息内容为：{}", textMessage, textMessage.getText());
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
