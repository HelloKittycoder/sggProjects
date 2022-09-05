### rocketmq基础 rocketmq-basic

https://www.bilibili.com/video/BV1cf4y157sz

---
##### 普通消息
1. 同步发送消息

生产者demo-SyncProducer

2. 异步发送消息

生产者demo-AsyncProducer

3. 单向发送消息

生产者demo-OnewayProducer

4. 消费者消费

消费者demo-Consumer

##### 顺序消息
OrderedProducer、OrderedConsumer

##### 延时消息
生产者-OrderedProducer，消费者用simple/Consumer就可以了

##### 事务消息
生产者-TransactionProducer，事务监听器-ICBCTransactionListener，消费者用simple/Consumer就可以了

##### 批量消息
BatchProducer、BatchConsumer

##### 消息过滤
通过tag过滤 FilterByTagProducer、FilterByTagConsumer

通过sql过滤 FilterBySQLProducer、FilterBySQLConsumer

