package com.atguigu.jms_spec;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;

/**
 * @author shucheng
 * @date 2022/6/27 20:55
 */
public class JmsTxProducer {

    public static final Logger LOGGER = LoggerFactory.getLogger(JmsTxProducer.class);
    public static final String ACTIVEMQ_URL = "tcp://192.168.21.134:61616";
    public static final String QUEUE_NAME = "Queue_Tx";

    public static void main(String[] args) throws JMSException {
        // 1.创建连接工厂
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        // 2.获取连接
        Connection connection = connectionFactory.createConnection();
        connection.start();
        // 3.获取session
        Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
        // 4.创建消息目的地
        Destination destination = session.createQueue(QUEUE_NAME);
        // 5.创建生产者
        MessageProducer producer = session.createProducer(destination);
        // 6.创建消息
        try {
            for (int i = 1; i <= 3; i++) {
                TextMessage textMessage = session.createTextMessage("哈哈哈" + i);
                producer.send(textMessage);
                /*if (i == 2) {
                    throw new RuntimeException("GG...");
                }*/
            }
            session.commit();
            LOGGER.info("消息发送完成");
        } catch (Exception e) {
            LOGGER.error("出现异常，消息回滚", e);
            session.rollback();
        } finally {
            producer.close();
            session.close();
            connection.close();
        }
    }
}
