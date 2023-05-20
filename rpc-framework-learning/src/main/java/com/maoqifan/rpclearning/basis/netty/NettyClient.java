package com.maoqifan.rpclearning.basis.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.AttributeKey;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @apiNote 初始化客户端<br />
 * 客户端中主要有一个用于向服务端发送消息的sendMessage()方法，通过该方法可以将消息
 * 即RpcRequest对象发送服务端，并且可以同步获取服务端返回的结果即RpcResponse对象。
 */
@AllArgsConstructor
@Slf4j
public class NettyClient {
    private final String host;
    private final int port;
    private static final Bootstrap b;

    // 初始化相关资源入EventLoopGroup, Bootstrap
    static {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        b = new Bootstrap();
        KryoSerializer kryoSerializer = new KryoSerializer();
        b.group(eventLoopGroup)
                .channel(NioSocketChannel.class)
                .handler(new LoggingHandler(LogLevel.INFO))
                // 连接超时时间，超过这个时间如果还是建立不上连接的化就代表连接失败
                // 如果15秒内没有发送数据给服务器的化，就发送心跳请求
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .handler(new ChannelInitializer<>() {
                    @Override
                    protected void initChannel(Channel channel) throws Exception {
                        // 自定义序列化编解码器
                        // RpcResponse -> ByteBuf
                        channel.pipeline().addLast(new NettyKryoDecoder(kryoSerializer, RpcResponse.class));
                        // ByteBuf -> RpcRequest
                        channel.pipeline().addLast(new NettyKryoEncoder(kryoSerializer, RpcRequest.class));
                        channel.pipeline().addLast(new NettyClientHandler());
                    }
                });

    }

    /**
     * 发送消息到服务端
     *
     * @param request 消息体
     * @return 服务端返回的数据
     */
    public RpcResponse sendMessage(RpcRequest request) {
        try {
            ChannelFuture f = b.connect(host, port).sync();
            log.info("client connect --> " + host + ":" + port);
            Channel futureChannel = f.channel();
            log.info("send message");
            if (futureChannel != null) {
                futureChannel.writeAndFlush(request).addListener(future -> {
                    if (future.isSuccess()) {
                        log.info("client send message: " + request.toString());
                    } else {
                        log.error("send failed : " + future.cause());
                    }
                });
                // 阻塞等待，直到channel关闭
                futureChannel.closeFuture().sync();
                // 将服务端返回的RpcResponse取出
                AttributeKey<RpcResponse> key = AttributeKey.valueOf("rpcResponse");
                return futureChannel.attr(key).get();
            }

        } catch (InterruptedException e) {
            log.error("occur exception when connect server", e);
        }

        return null;
    }

    public static void main(String... args) {
        RpcRequest request = RpcRequest.builder()
                .interfaceName("interface")
                .methodName("hello").build();
        NettyClient nettyClient = new NettyClient("127.0.0.1", 8889);
        for (int i = 0; i < 3; i++) {
            nettyClient.sendMessage(request);
        }
        RpcResponse response = nettyClient.sendMessage(request);
        System.out.println(response.toString());
    }
}

