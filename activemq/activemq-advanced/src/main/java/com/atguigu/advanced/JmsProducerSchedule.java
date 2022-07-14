package com.atguigu.advanced;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ScheduledMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;

/**
 * 定时投递
 * @author shucheng
 * @date 2022/6/8 8:53
 */
public class JmsProducerSchedule {

    private static final Logger LOG = LoggerFactory.getLogger(JmsProducerSchedule.class);

    public static final String ACTIVEMQ_URL = "tcp://192.168.21.134:61616";
    public static final String QUEUE_NAME = "Schedule01";

    public static void main(String[] args) throws JMSException {
        // 1.按照给定的url创建连接工厂，这个构造器采用默认的用户名密码
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        // 2.通过连接工厂，获得连接connection，并启动访问
        Connection connection = connectionFactory.createConnection();
        connection.start();
        LOG.info("create connection {}", connection);
        // 3.创建会话session。第1个参数是是否开启事务，第2个参数是消息签收的方式
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 4.创建目的地（两种：队列/主题）
        Queue queue = session.createQueue(QUEUE_NAME);
        // 5.创建消息的生产者
        MessageProducer messageProducer = session.createProducer(queue);
        // 6.通过 messageProducer 生消息发送到消息队列中
        long delay =  10*1000;
        long period = 5*1000;
        int repeat = 3 ;

        // 7.创建消息
        for (int i = 1; i <= 3; i++) {
            TextMessage textMessage = session.createTextMessage("schedule-msg--" + i);
            textMessage.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, delay);
            textMessage.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_PERIOD, period);
            textMessage.setIntProperty(ScheduledMessage.AMQ_SCHEDULED_REPEAT, repeat);
            // 此处的含义：该条消息，等待10秒（初始发送1次），之后每5秒发送1次，重复发送3次（总共发了4次）
            // 8.通过 messageProducer 发送给mq
            messageProducer.send(textMessage);
        }
        LOG.info("消息发送完成");

        // 9.关闭资源
        messageProducer.close();
        session.close();
        connection.close();
        LOG.info(" *** QUEUE_NAME消息发送到MQ完成 ***");
    }
}
