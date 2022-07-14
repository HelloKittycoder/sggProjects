package com.atguigu.advanced;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQMessageProducer;
import org.apache.activemq.AsyncCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;
import java.util.UUID;

/**
 * 异步消息投递（生产者-->broker）
 * 通过下面打印的日志可以清楚地看出，是异步投递的
 * @author shucheng
 * @date 2022/6/8 8:53
 */
public class JmsProducerAsync {

    private static final Logger LOG = LoggerFactory.getLogger(JmsProducerAsync.class);

    public static final String ACTIVEMQ_URL = "tcp://192.168.21.134:61616";
    public static final String QUEUE_NAME = "Async";

    public static void main(String[] args) throws JMSException {
        // 1.按照给定的url创建连接工厂，这个构造器采用默认的用户名密码
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        connectionFactory.setUseAsyncSend(true);
        // 2.通过连接工厂，获得连接connection，并启动访问
        Connection connection = connectionFactory.createConnection();
        connection.start();
        LOG.info("create connection {}", connection);
        // 3.创建会话session。第1个参数是是否开启事务，第2个参数是消息签收的方式
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 4.创建目的地（两种：队列/主题）
        Queue queue = session.createQueue(QUEUE_NAME);
        // 5.创建消息的生产者
        ActiveMQMessageProducer messageProducer = (ActiveMQMessageProducer) session.createProducer(queue);
        // 6.通过 messageProducer 生成3条消息发送到消息队列中
        for (int i = 1; i <= 6; i++) {
            int tempI = i;
            // 7.创建消息
            TextMessage textMessage = session.createTextMessage("msg--" + i);
            String msgId = UUID.randomUUID().toString() + "orderAtguigu";
            textMessage.setJMSMessageID(msgId);
            // 8.通过 messageProducer 发送给mq
            // messageProducer.send(textMessage);
            messageProducer.send(textMessage, new AsyncCallback() {
                @Override
                public void onSuccess() {
                    LOG.info("第{}条消息，成功发送消息id：{}", tempI, msgId);
                }

                @Override
                public void onException(JMSException exception) {
                    LOG.info("第{}条消息，失败发送消息id：{}", tempI, msgId);
                }
            });
            LOG.info("正在发第{}条消息", i);
        }
        LOG.info("消息发送完成");
        // 9.关闭资源
        messageProducer.close();
        session.close();
        connection.close();
        System.out.println(" *** QUEUE_NAME消息发送到MQ完成 ***");
    }
}
