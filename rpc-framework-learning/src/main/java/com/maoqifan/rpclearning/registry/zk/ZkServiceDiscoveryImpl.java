package com.maoqifan.rpclearning.registry.zk;

import com.maoqifan.rpclearning.registry.ServiceDiscovery;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;

/**
 * 基于zk的服务发现
 */
@Slf4j
public class ZkServiceDiscoveryImpl implements ServiceDiscovery {
    private  final LoadBalance  loadBalance;


    @Override
    public InetSocketAddress lookupService(String rpcServiceName) {
        return null;
    }
}
