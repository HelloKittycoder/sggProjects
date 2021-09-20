package com.atguigu.case1;

import org.apache.zookeeper.*;

import java.io.IOException;

/**
 * Created by shucheng on 2021/9/19 18:14
 */
public class DistributeServer {

    private ZooKeeper zkClient;
    private String connectString = "161server:2181,162server:2181,163server:2181";
    private int sessionTimeout = 2000;

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        DistributeServer server = new DistributeServer();

        // 1.获取zk连接
        server.getConnection();

        // 2.注册服务器到zk集群
        server.register(args[0]);

        // 3.处理业务逻辑（睡觉）
        server.business();
    }

    private void getConnection() throws IOException {
        zkClient = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
            }
        });
    }

    private void register(String hostname) throws KeeperException, InterruptedException {
        String nodeCreated = zkClient.create("/servers/" + hostname, hostname.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println(nodeCreated);
        System.out.println(hostname + " is online");
    }

    private void business() throws InterruptedException {
        Thread.sleep(Long.MAX_VALUE);
    }
}
