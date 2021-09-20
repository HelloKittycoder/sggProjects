package com.atguigu.zk;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * Created by shucheng on 2021/9/19 13:57
 */
public class ZkClientTest {

    private String connectString = "161server:2181,162server:2181,163server:2181";
    private int sessionTimeout = 2000;
    private ZooKeeper zkClient;

    @Before
    public void init() throws IOException {
        zkClient = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                /*List<String> children;
                try {
                    System.out.println("------------------------------");
                    children = zkClient.getChildren("/", true);
                    for (String child : children) {
                        System.out.println(child);
                    }
                    System.out.println("------------------------------");
                } catch (Exception e) {
                    e.printStackTrace();
                }*/
            }
        });
    }

    // 创建节点
    @Test
    public void create() throws KeeperException, InterruptedException {
        String nodeCreated = zkClient.create("/atguigu", "ss.avi".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    // 监听节点变化，在init里写了Watcher
    @Test
    public void getChildren() throws KeeperException, InterruptedException {
        /*List<String> children = zkClient.getChildren("/servers", true);
        byte[] data = zkClient.getData("/servers/161server0000000003", false, null);
        System.out.println(new String(data));*/
        Thread.sleep(Long.MAX_VALUE);
    }

    // 判断节点是否存在
    @Test
    public void exist() throws KeeperException, InterruptedException {
        Stat stat = zkClient.exists("/atguigu2", false);
        System.out.println(stat == null ? "not exist" : "exist");
    }
}
