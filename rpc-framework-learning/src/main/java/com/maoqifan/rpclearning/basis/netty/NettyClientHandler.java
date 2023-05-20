package com.maoqifan.rpclearning.basis.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.AttributeKey;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;


/**
 * NettyClientHandler 用于保存服务端发送过来的RpcResponse消息对象，并将RpcResponse消息对象
 * 保存到AttributeMap上，AttributeMap可以看作是一个channel的共享数据源。
 */
@Slf4j
public class NettyClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try {
            RpcResponse response = (RpcResponse) msg;
            log.info("client receive  msg" + response.toString());
            // 申明一个AttributeKey对象
            AttributeKey<RpcResponse> key = AttributeKey.valueOf("rpcResponse");
            // 将服务端返回的结果保存到AttributeMap上
            // Channel实现了AttributeMap接口，这样也表明它存在了AttributeMap相关的属性
            // AttributeMap可以看作是一个Channel共享数据源
            // AttributeMap的key是AttributeKey，value是Attribute
            ctx.channel().attr(key).set(response);
            ctx.channel().close();
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("client caught exception", cause);
        ctx.close();
    }
}
