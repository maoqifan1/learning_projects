package com.maoqifan.rpclearning.basis.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;


import java.util.List;

/**
 * 自定义解码器
 * 负责处理"入站"消息，会从ByteBuf中读取到业务对象对应的字节序列，
 * 然后将字节序列转换为我们的业务对象
 */
@Slf4j
@AllArgsConstructor
public class NettyKryoDecoder extends ByteToMessageDecoder {
    private final Serializer serializer;
    private final Class<?> genericClass;
    /**
     * Netty 传输的消息长度也就是对象序列化后对应的字节数组大小，存储在ByteBuf头部
     */
    private static final int BODY_LENGTH = 4;

    /**
     * 解码ByteBuf对象
     *
     * @param channelHandlerContext 解码器关联的 ChannelHandlerContext对象
     * @param byteBuf               "入站"数据，也就是ByteBuf对象
     * @param list                  解码之后的数据对象需要添加到list对象里
     */
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        // 1. byteBuf中写入的消息长度所占字节已经为4了，所以byteBuf的可读字节必须大于4
        if (byteBuf.readableBytes() >= BODY_LENGTH) {
            // 2. 标记当前readIndex位置，以便后面重制readIndex时使用
            byteBuf.markReaderIndex();
            // 3. 读取消息长度
            // caution: 消息长度时encode时候自己写入的
            int dataLen = byteBuf.readInt();
            // 4. 情况不合理时直接return
            if (dataLen < 0 || byteBuf.readableBytes() < 0) {
                log.error("data length or byteBuf readableBytes is not valid");
                return;
            }
            // 5.  如果可读字节小于消息长度，说明是不完整消息，重置readIndex
            if (byteBuf.readableBytes() < dataLen) {
                byteBuf.resetReaderIndex();
                return;
            }
            // 6. 序列化
            byte[] body = new byte[dataLen];
            byteBuf.readBytes(body);
            // 将bytes数组转换为需要的对象
            Object obj = serializer.deserialize(body, genericClass);
            list.add(obj);
            log.info("successful decode ByteBuf to Object");
        }
    }
}
