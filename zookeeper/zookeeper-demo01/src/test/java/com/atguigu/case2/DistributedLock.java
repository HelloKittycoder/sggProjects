package com.atguigu.case2;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 分布式锁
 * Created by shucheng on 2021/9/20 13:32
 */
public class DistributedLock {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private ZooKeeper zk;
    private String connectString = "161server:2181,162server:2181,163server:2181";
    private int sessionTimeout = 2000;
    private CountDownLatch connectLatch = new CountDownLatch(1);
    private CountDownLatch waitLatch = new CountDownLatch(1);
    private String currentNodeCompletePath;
    private String waitPath;

    public DistributedLock() throws IOException, KeeperException, InterruptedException {
        // 1.建立zookeeper连接
        zk = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                // connectLatch 如果连接上zk 可以释放
                if (event.getState() == Event.KeeperState.SyncConnected) {
                    logger.info("监听到zk连接成功");
                    connectLatch.countDown();
                }

                // waitLatch 需要释放
                if (event.getType() == Event.EventType.NodeDeleted && event.getPath().equals(waitPath)) {
                    logger.info("监听到" + waitPath + "被删除");
                    waitLatch.countDown();
                }
            }
        });

        // 等待zk正常连接后，继续往后执行程序
        connectLatch.await();

        // 2.如果没有/locks，则创建/locks节点
        Stat stat = zk.exists("/locks", false);
        if (stat == null) {
            zk.create("/locks", "locks".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }
    }

    public void lock() {
        try {
            // 创建对应的临时带序号节点
            currentNodeCompletePath = zk.create("/locks/seq-", null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);

            // 判断创建的节点是否是序号最小的节点，如果是获取到锁；如果不是，监听该序号的前一个节点
            List<String> children = zk.getChildren("/locks", false);
            if (children.size() == 1) {
                logger.info("当前/locks下节点数量为1，直接获取锁");
                return;
            }

            Collections.sort(children);
            logger.info("当前/locks下节点数量为{}，节点为{}", children.size(), children);
            String currentNodePath = currentNodeCompletePath.substring("/locks/".length());
            int nodeIndex = children.indexOf(currentNodePath);
            logger.info("当前节点名称为【{}】，序号为{}", currentNodePath, nodeIndex);
            if (nodeIndex == -1) {
                System.out.println("数据有误");
            } else if (nodeIndex == 0) {
                logger.info("序号为0，直接获取锁");
                return;
            } else {
                // 对前一个节点进行监听
                waitPath = "/locks/" + children.get(nodeIndex - 1);
                logger.info("{} 对前一个节点【{}】进行监听", currentNodeCompletePath, waitPath);
                zk.getData(waitPath, true, null);

                waitLatch.await();
                return;
            }
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void unlock() {
        try {
            // 删除节点
            logger.info("释放锁，删除{}节点", currentNodeCompletePath);
            zk.delete(currentNodeCompletePath, -1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }
}
