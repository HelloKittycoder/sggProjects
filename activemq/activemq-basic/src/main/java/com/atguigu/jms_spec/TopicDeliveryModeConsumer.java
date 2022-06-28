package com.atguigu.jms_spec;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;
import java.io.IOException;

/**
 * @author shucheng
 * @date 2022/6/26 21:49
 */
public class TopicDeliveryModeConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(TopicDeliveryModeConsumer.class);
    public static final String ACTIVEMQ_URL = "tcp://192.168.21.134:61616";
    public static final String TOPIC_NAME = "topic_persist";

    public static void main(String[] args) throws JMSException, IOException {
        // 1.创建连接工厂
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        // 2.获取连接
        Connection connection = connectionFactory.createConnection();
        connection.setClientID("zhangsan");
        // 3.获取session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 4.创建消息目的地
        Destination destination = session.createTopic(TOPIC_NAME);
        // 5.创建topic的持久化订阅对象
        TopicSubscriber topicSubscriber = session.createDurableSubscriber((Topic) destination, "topicDest");
        connection.start();
        // 6.接收消息
        while (true) {
            Message message = topicSubscriber.receive();
            if (message != null) {
                TextMessage textMessage = (TextMessage) message;
                LOGGER.info("原始消息为：{}，消息内容为：{}", textMessage, textMessage.getText());
            } else {
                break;
            }
        }


        /*topicSubscriber.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                if (message instanceof TextMessage) {
                    try {
                        TextMessage textMessage = (TextMessage) message;
                        LOGGER.info("原始消息为：{}，消息内容为：{}", textMessage, textMessage.getText());
                    } catch (JMSException e) {
                        LOGGER.error(e.getMessage(), e);
                    }
                }
            }
        });
        System.in.read();*/

        topicSubscriber.close();
        session.close();
        connection.close();
    }
}
