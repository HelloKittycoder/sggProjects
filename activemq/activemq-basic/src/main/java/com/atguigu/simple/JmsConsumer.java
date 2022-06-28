package com.atguigu.simple;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;
import java.io.IOException;

/**
 * @author shucheng
 * @date 2022/6/16 22:41
 */
public class JmsConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(JmsConsumer.class);

    public static final String ACTIVEMQ_URL = "tcp://192.168.21.134:61616";
    public static final String QUEUE_NAME = "jdbc01";

    public static void main(String[] args) throws JMSException, IOException {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        Connection connection = connectionFactory.createConnection();
        connection.start();
        LOG.info("连接已建立，消费者1");
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue(QUEUE_NAME);
        MessageConsumer messageConsumer = session.createConsumer(queue);
        /* // 同步阻塞方式接收消息
        while (true) {
            TextMessage message = (TextMessage) messageConsumer.receive();
            if (message != null) {
                System.out.println("****消费者的消息：****" + message.getText());
            } else {
                break;
            }
        }*/

        messageConsumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                // LOG.info("我收到消息了，消息为：{}", message);
                TextMessage textMessage = (TextMessage) message;
                try {
                    LOG.info("消息内容为：{}", textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
        System.in.read();
        messageConsumer.close();
        session.close();
        connection.close();
    }
}
