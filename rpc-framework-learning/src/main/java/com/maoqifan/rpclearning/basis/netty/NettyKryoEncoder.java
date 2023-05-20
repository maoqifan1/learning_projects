package com.maoqifan.rpclearning.basis.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.AllArgsConstructor;

/**
 * 自定义编码器
 * 网络传输需要通过字节流实现，
 * ByteBuf可以看作是Netty提供的字节数据的容器，使用它会让我们更方便的处理字节数据
 */
@AllArgsConstructor
public class NettyKryoEncoder extends MessageToByteEncoder<Object> {
    private final Serializer serializer;
    private final Class<?> genericClass;


    /**
     * 将对象转换为字节码然后写入到ByteBuf对象中
     */
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object o, ByteBuf byteBuf) throws Exception {
        if (genericClass.isInstance(o)) {
            // 1. 将对象转换为byte
            byte[] body = serializer.serialize(o);
            // 2. 读取消息长度
            int dataLen = body.length;
            // 3. 写入消息对应的字节数组长度，writerIndex加4
            byteBuf.writeInt(dataLen);
            // 4. 将字节写入ByteBuf对象
            byteBuf.writeBytes(body);
        }
    }
}
