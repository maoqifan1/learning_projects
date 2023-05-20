package com.maoqifan.rpclearning.remoting.transport;


import com.maoqifan.rpclearning.remoting.dto.RpcRequest;

public interface RpcRequestTransport {
    /**
     * 向服务器发送rpc请求并获取结果
     */
    Object sendRpcRequest(RpcRequest request);
}
