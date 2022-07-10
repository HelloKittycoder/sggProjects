package com.atguigu.activemq.boot.mq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 运行这个时，先注释掉 produceMessageScheduled 上的 @Scheduled
 * @author shucheng
 * @date 2022/7/10 9:59
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BootQueueProducerTest {

    @Autowired
    private BootQueueProducer bootQueueProducer;

    @Test
    public void testSend() {
        bootQueueProducer.produceMessage();
    }
}
