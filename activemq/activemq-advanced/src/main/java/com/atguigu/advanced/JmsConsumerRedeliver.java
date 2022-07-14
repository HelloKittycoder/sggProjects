package com.atguigu.advanced;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.RedeliveryPolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;
import java.io.IOException;

/**
 * 消息消费的重试机制
 * （生产者代码正常，和以前写法一样 activemq-basic的simple/JmsProducer；
 * 消费者这边未消费成功）
 *
 * @author shucheng
 * @date 2022/7/13 7:44
 */
public class JmsConsumerRedeliver {

    private static final Logger LOG = LoggerFactory.getLogger(JmsConsumerRedeliver.class);

    public static final String ACTIVEMQ_URL = "tcp://192.168.21.134:61616";
    public static final String QUEUE_NAME = "Redeliver-queue";

    public static void main(String[] args) throws JMSException, IOException {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        // 修改默认参数，设置消息消费重试3次
        RedeliveryPolicy redeliveryPolicy = new RedeliveryPolicy();
        redeliveryPolicy.setMaximumRedeliveries(3);
        connectionFactory.setRedeliveryPolicy(redeliveryPolicy);

        Connection connection = connectionFactory.createConnection();
        connection.start();
        LOG.info("连接已建立，消费者1");
        Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
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
                    // session.commit();
                    session.rollback();
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
