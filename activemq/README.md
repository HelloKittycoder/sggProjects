### activemq基础 activemq-basic

---
##### 入门案例
Queue-消费者和生产者demo JmsProducer、JmsConsumer

Topic-消费者和生产者demo JmsProducer_topic、JmsConsumer_topic

##### JMS规范
消息头-JmsProducer_message_head、JmsConsumer_message_head

消息体-JmsProducer_message_body、JmsConsumer_message_body

消息属性-JmsProducer_message_attr、JmsConsumer_message_attr

消息的持久化-QueueDeliveryModeProducer、QueueDeliveryModeConsumer  
TopicDeliveryModeProducer、TopicDeliveryModeConsumer

消息的事务性-JmsTxProducer、JmsTxConsumer

消息的签收机制-JmsAckProducer、JmsAckConsumer

##### ActiveMQ的broker
嵌入式的broker启动-EmbedBroker

### spring整合activemq

---
##### spring整合activemq activemq-spring 
队列生产者、消费者测试-SpringQueueSendTest、SpringQueueReceiveTest

主题生产者、消费者测试-SpringTopicSendTest、SpringTopicReceiveTest

这部分非课程中讲的内容（参考链接：https://activemq.apache.org/spring-support）

##### spring整合activemq（带连接池） activemq-pool-spring
队列生产者、消费者测试-SpringQueueProducer、SpringQueueConsumer

主题生产者、消费者测试-SpringTopicProducer、SpringTopicConsumer

队列生产者、消费者（监听器方式）测试-SpringQueueProducer、SpringQueueListener

### springboot整合activemq

--- 
activemq-boot-queue-producer、activemq-boot-queue-consumer

队列生产者、消费者（监听器方式）测试-BootQueueProducer、BootQueueConsumer

队列生产者发送消息（单元测试）-BootQueueProducerTest

activemq-boot-topic-producer、activemq-boot-topic-consumer

主题生产者、消费者（监听器方式）测试-BootTopicProducer、BootTopicConsumer
