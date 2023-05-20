package com.maoqifan.DistributedLearning;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorListener;
import org.apache.curator.framework.listen.Listenable;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.curator.framework.recipes.locks.*;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class ZooKeeperConfig {
    private static final Logger log = LoggerFactory.getLogger(ZooKeeperConfig.class);
    private static final int BASE_SLEEP_TIME = 1000;
    private static final int MAX_RETRIES = 3;


    public static CuratorFramework getZkClient() {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(BASE_SLEEP_TIME, MAX_RETRIES);
        return CuratorFrameworkFactory.builder()
                .connectString("127.0.0.1:2181")
                .retryPolicy(retryPolicy)
                .build();
    }

    public static void lockTest() {
        CuratorFramework zkClient = getZkClient();
        // 启动客户端
        zkClient.start();
        //创建第一个互斥锁
        InterProcessLock processLock = new InterProcessMutex(zkClient, "/lock1");
        // 创建第二个互斥锁
        InterProcessLock processLock1 = new InterProcessSemaphoreMutex(zkClient, "/lock2");
        // 创建多锁
        InterProcessMultiLock multiLock = new InterProcessMultiLock(Arrays.asList(processLock, processLock1));

        // 申请多锁
        try {
            if (!multiLock.acquire(10, TimeUnit.SECONDS)) {
                // 如果申请失败
                log.info("申请锁: {}失败", multiLock);
            }
            // 申请到了锁
            log.info("申请到了锁");
            // 打印锁是否被申请到了
            log.info("{}", processLock.isAcquiredInThisProcess());
            log.info("{}", processLock1.isAcquiredInThisProcess());

            // 释放锁资源
            multiLock.release();
            //  关闭客户端连接
            zkClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void basicAPITest() throws Exception {
        CuratorFramework zkClient = getZkClient();
        zkClient.start();
        // 判断节点还是否存在
        if (zkClient.checkExists().forPath("/node1/testEphemeral") != null)
            // 删除节点
            zkClient.delete().forPath("/node1/testEphemeral");
        // 创建节点
        String result = zkClient.create().creatingParentContainersIfNeeded()
                .withMode(CreateMode.EPHEMERAL).forPath("/node1/testEphemeral");
        // 判断节点是否存在
        if (zkClient.checkExists().forPath("/node1/testEphemeralVal") != null)
            zkClient.delete().forPath("/node1/testEphemeralVal");
        //  创建节点并指定内容
        String result1 = zkClient.create().creatingParentContainersIfNeeded()
                .withMode(CreateMode.EPHEMERAL).forPath("/node1/testEphemeralVal", "maoqifan".getBytes());
        log.info("result: {}\nresult1: {}.", result, result1);

        // 读写锁
        InterProcessReadWriteLock lock = new InterProcessReadWriteLock(zkClient, "/node1/testEphemeral");
        lock.readLock().acquire();
        lock.readLock().release();
        lock.writeLock().acquire();
        lock.writeLock().release();


        // 给某个节点注册节点监听器
        String path = "/node1";
        CuratorCache curatorCache = CuratorCache.builder(zkClient, path).build();
        CuratorCacheListener cacheListener = CuratorCacheListener.builder()
                .forNodeCache(() -> {
                    //todo: 可以写监听逻辑
                }).build();
        // 添加监听器
        curatorCache.listenable().addListener(cacheListener);
        curatorCache.start();

    }

    /**
     * @see org.apache.zookeeper.CreateMode  创建节点的类型
     */
    public static void main(String[] args) throws Exception {
        lockTest();
    }


}
