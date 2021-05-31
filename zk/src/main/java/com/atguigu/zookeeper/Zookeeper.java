package com.atguigu.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class Zookeeper {
    /**
     * zookeeper在java端实现各种API的操作
     */
    private String connect;
    private ZooKeeper zkClient;

    @Test
    public void ls() throws KeeperException, InterruptedException {
        List<String> children = zkClient.getChildren("/", false);
        System.out.println(children);
    }
    @Test
    public void lswatcher() throws KeeperException, InterruptedException {
        zkClient.getChildren("/", new Watcher() {
            public void process(WatchedEvent watchedEvent) {
                System.out.println("获取子节点列表并且监听");
            }
        });
        Thread.sleep(Long.MAX_VALUE);
    }
    @Test
    public void create() throws KeeperException, InterruptedException {
        String path="/gg";
        //创建子节点，四个参数1-路径 2-数据 3-节点的权限 4-创建节点的类型 永久/临时...
        String s = zkClient.create(path, "shuju".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println(s);
    }
    @Test
    //判断节点是否存在
    public void exist() throws KeeperException, InterruptedException {
        String path="/atguigu";
        Stat stat = zkClient.exists(path, false);
        if(stat==null){
            System.out.println("节点不存在");
            return;
        }else{
            System.out.println("stat:::::::"+stat);
        }
    }


    @Test
    public void get() throws KeeperException, InterruptedException {
        //获取节点下的数据,不监听节点
        String path="/gg";
        Stat stat = zkClient.exists(path, false);
        if(stat==null){
            System.out.println("节点不存在");
            return;
        }
        byte[] data = zkClient.getData("/gg", false, stat);
        System.out.println(new String(data));
    }

    /**
     * 创建节点数据
     * 如果是多级目录下的数据，前提是前面的每一层目录都已经存在
     */
    @Test
    public void createdata() throws KeeperException, InterruptedException {
        String path="/gg";
        Stat stat = zkClient.exists(path, false);
        if(stat==null){
            System.out.println("节点不存在");
            return;
        }else {
            zkClient.setData(path, "201116".getBytes(), -1);
        }
    }

    /**
     * 删除节点-->如果是空节点就可以直接删除
     * 如果是非空节点，那么就递归删除，直到所有节点下的数据全部删完在删除根节点。
     *
     */
    @Test
    public void testdaleteall() throws KeeperException, InterruptedException {
        String path="/atguigu";
        deleteall(path,zkClient);
    }
    public void deleteall(String path,ZooKeeper zk) throws KeeperException, InterruptedException {
        Stat stat;
        stat = zkClient.exists(path, false);
        if(stat==null){
            System.out.println("节点不存在");
            return;
        }
        List<String> children = zkClient.getChildren(path, false);
        if(children.size()==0){
            zkClient.delete(path,stat.getVersion());
        }else{
            for (String child : children) {
                deleteall(path+"/"+child, zk);
            }
            zkClient.delete(path,stat.getVersion());
        }

    }


    @Before
    public void zookeeeper() throws IOException {
        //连接客户端
        connect="hadoop102:2181,hadoop103:2181,hadoop104:2181";
        zkClient=new ZooKeeper(connect, 10000, new Watcher() {
            public void process(WatchedEvent watchedEvent) {

            }
        });
    }
    @After
    //关闭资源
    public void close() throws InterruptedException {
        zkClient.close();
    }
}
