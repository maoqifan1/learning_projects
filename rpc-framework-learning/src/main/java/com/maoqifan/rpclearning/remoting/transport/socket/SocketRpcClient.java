package com.maoqifan.rpclearning.remoting.transport.socket;

import com.maoqifan.rpclearning.remoting.dto.RpcRequest;
import com.maoqifan.rpclearning.remoting.transport.RpcRequestTransport;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/**
 * 基于Socket传输RpcRequest
 */
@AllArgsConstructor
@Slf4j
public class SocketRpcClient implements RpcRequestTransport {

    @Override
    public Object sendRpcRequest(RpcRequest request) {
        return null;
    }
}
