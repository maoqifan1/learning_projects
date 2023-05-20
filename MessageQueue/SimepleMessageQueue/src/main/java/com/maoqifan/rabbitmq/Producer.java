package com.maoqifan.rabbitmq;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer {
    public static void main(String[] args) throws IOException, TimeoutException {
        // 创建连接工厂
        ConnectionFactory factory =  new ConnectionFactory();
        factory.setUsername("guest");
        factory.setPassword("guest");
        // 设置RabbitMQ地址
        factory.setHost("localhost");
        factory.setVirtualHost("/");
        // 建立代理服务器连接
        Connection connection = factory.newConnection();
        // 创建信道
        Channel channel = connection.createChannel();
        // 声明交换器名字
        String exchange = "hello-exchange";
        // direct 单播
        channel.exchangeDeclare(exchange, "direct", true);

        String routingKey = "testRoutingKey";
        // 发布消息的内容
        byte[] messageBodyBytes = "quit".getBytes();
        // 发布消息
        channel.basicPublish(exchange, routingKey, null, messageBodyBytes);
        // 关闭信道和连接
        channel.close();
        connection.close();
    }
}
