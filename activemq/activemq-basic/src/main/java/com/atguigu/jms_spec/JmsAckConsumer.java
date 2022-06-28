package com.atguigu.jms_spec;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;
import java.io.IOException;

/**
 * 消费者
 * 不带事务情况下：
 * 消息确认取决于选择的签收机制，AUTO则为自动签收，CLIENT则为手动签收
 *
 * 带事务情况下：此时签收将不起作用
 * 事务提交的话，则消息会自动确认；事务不提交的话，则无论消息是否签收，都不会被确认
 *
 * @author shucheng
 * @date 2022/6/27 21:07
 */
public class JmsAckConsumer {

    public static final Logger LOGGER = LoggerFactory.getLogger(JmsAckConsumer.class);
    public static final String ACTIVEMQ_URL = "tcp://192.168.21.134:61616";
    public static final String QUEUE_NAME = "Queue_Tx";

    public static void main(String[] args) throws JMSException {
        // 1.创建连接工厂
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        // 2.创建连接
        Connection connection = connectionFactory.createConnection();
        connection.start();
        // 3.创建session
        Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
        // 4.创建消息目的地
        Destination destination = session.createQueue(QUEUE_NAME);
        // 5.创建消费者
        MessageConsumer consumer = session.createConsumer(destination);
        // 6.接收消息
        while (true) {
            TextMessage message = (TextMessage) consumer.receive(4000L);
            if (message != null) {
                LOGGER.info("消息内容为：{}", message.getText());
                // message.acknowledge();
            } else {
                break;
            }
        }

        consumer.close();
        // session.commit();
        session.close();
        connection.close();
    }
}
