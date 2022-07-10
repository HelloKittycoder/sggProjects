package com.atguigu.activemq.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author shucheng
 * @date 2022/7/5 8:00
 */
@SpringBootApplication
@EnableScheduling
public class ActivemqBootQueueProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActivemqBootQueueProducerApplication.class, args);
    }
}
