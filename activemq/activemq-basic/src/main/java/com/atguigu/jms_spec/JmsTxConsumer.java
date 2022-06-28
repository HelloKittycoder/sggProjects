package com.atguigu.jms_spec;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;
import java.io.IOException;

/**
 * @author shucheng
 * @date 2022/6/27 21:07
 */
public class JmsTxConsumer {

    public static final Logger LOGGER = LoggerFactory.getLogger(JmsTxConsumer.class);
    public static final String ACTIVEMQ_URL = "tcp://192.168.21.134:61616";
    public static final String QUEUE_NAME = "Queue_Tx";

    public static void main(String[] args) throws JMSException, IOException {
        // 1.创建连接工厂
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        // 2.创建连接
        Connection connection = connectionFactory.createConnection();
        connection.start();
        // 3.创建session
        Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
        // 4.创建消息目的地
        Destination destination = session.createQueue(QUEUE_NAME);
        // 5.创建消费者
        MessageConsumer consumer = session.createConsumer(destination);
        // 6.接收消息
        consumer.setMessageListener(new MessageListener() {
            int a = 0;
            @Override
            public void onMessage(Message message) {
                try {
                    TextMessage textMessage = (TextMessage) message;
                    LOGGER.info("消息内容为：{}", textMessage.getText());
                    if (a == 0) {
                        System.out.println("commit");
                        session.commit();
                    } else if (a == 1) {
                        System.out.println("rollback");
                        session.rollback();
                    }
                    a++;
                } catch (JMSException e) {
                    LOGGER.error("出错了，消费异常", e);
                    try {
                        session.rollback();
                    } catch (JMSException ex) {
                        ex.printStackTrace();
                    }
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
