package com.atguigu.activemq.boot.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.stereotype.Component;

/**
 * @author shucheng
 * @date 2022/7/5 8:03
 */
@Component
@EnableJms
public class ActivemqConfig {

    @Value("${myqueue}")
    private String queueName;

    @Bean
    public ActiveMQQueue queue() {
        return new ActiveMQQueue(queueName);
    }
}
