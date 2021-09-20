package com.atguigu.case1;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shucheng on 2021/9/19 18:37
 */
public class DistributeClient {

    private ZooKeeper zkClient;
    private String connectString = "161server:2181,162server:2181,163server:2181";
    private int sessionTimeout = 2000;

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        DistributeClient client = new DistributeClient();

        // 1.获取zk连接
        client.getConnection();

        // 2.监听 /servers 下面子节点的增加和删除
        client.getServerList();

        // 3.处理业务逻辑（睡觉）
        client.business();
    }

    private void getConnection() throws IOException {
        zkClient = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                try {
                    getServerList();
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void getServerList() throws KeeperException, InterruptedException {
        List<String> children = zkClient.getChildren("/servers", true);
        List<String> servers = new ArrayList<>();
        for (String child : children) {
            byte[] data = zkClient.getData("/servers/" + child, false, null);
            servers.add(new String(data));
        }
        System.out.println(servers);
    }

    private void business() throws InterruptedException {
        Thread.sleep(Long.MAX_VALUE);
    }
}
