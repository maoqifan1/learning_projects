package com.maoqifan.rpclearning.registry.zk;

import com.maoqifan.rpclearning.registry.ServiceRegistry;
import com.maoqifan.rpclearning.registry.zk.util.CuratorUtils;
import org.apache.curator.framework.CuratorFramework;

import java.net.InetSocketAddress;

/**
 * 当我们的服务被注册进zookeeper1时，我们将完整的服务名称rpcServiceName（class name + group + version)
 * 作为根节点，子节点是对应的服务地址(ip+端口号)
 * 1. class name：服务接口名也就是类名: com.maoqifan.HelloService
 * 2. version: 服务版本。主要是为后续不兼容升级提供可能
 * 3. group: 主要用于处理一个接口有多个实现类的情况。
 * 一个根节点可能会对应多个服务地址（相同服务被部署多份的情况）
 *
 * 如果要获得某个服务对应的地址，就直接根据完整的服务名称来获取到其下所有子节点，然后通过
 * 具体的负载均衡策略取出一个就可以了。
 */
public class ZkServiceRegistryImpl implements ServiceRegistry {
    @Override
    public void registerService(String rpcServiceName, InetSocketAddress inetSocketAddress) {
        String servicePath = "%s/%s%s".formatted(CuratorUtils.ZK_REGISTER_ROOT_PATH, rpcServiceName, inetSocketAddress.toString());
        CuratorFramework zkClient = CuratorUtils.getZkClient();
        CuratorUtils.createPersistentNode(zkClient, servicePath);
    }
}
