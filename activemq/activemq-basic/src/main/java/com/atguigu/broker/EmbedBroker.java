package com.atguigu.broker;

import org.apache.activemq.broker.BrokerService;

/**
 * @author shucheng
 * @date 2022/6/28 8:01
 */
public class EmbedBroker {

    public static void main(String[] args) throws Exception {
        //ActiveMQ也支持在vm中通信基于嵌入的broker
        BrokerService brokerService = new BrokerService();
        brokerService.setUseJmx(true);
        brokerService.addConnector("tcp://localhost:61616");
        brokerService.start();
        System.in.read();
    }
}
