package com.maoqifan.rpclearning.basis.netty;

/**
 * @apiNote 该接口两个方法一个用语序列化，另一个用于反序列化。
 */
public interface Serializer {
    /**
     * 序列化
     *
     * @param obj 要序列化的对象
     * @return 字节数组
     */
    byte[] serialize(Object obj) throws Exception;

    /**
     * 反序列化
     *
     * @param bytes 序列化后的字节数组
     * @param clazz 类
     * @return 反序列化后的对象
     */
    <T> T deserialize(byte[] bytes, Class<T> clazz) throws Exception;
}
