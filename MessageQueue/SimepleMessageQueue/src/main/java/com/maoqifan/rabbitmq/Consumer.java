package com.maoqifan.rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class Consumer {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setVirtualHost("/");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        // 建立到代理服务器的链接
        Connection connection = connectionFactory.newConnection();
        // 创建信道
        final Channel channel = connection.createChannel();
        // 申明交换器
        String exchangeName = "hello-exchange";
        channel.exchangeDeclare(exchangeName, "direct", true);
        // 申明消息队列
        String queueName = channel.queueDeclare().getQueue();
        String routineKey = "testRoutingKey";
        //  绑定队列，通过路由键testRoutingKey 将队列和交换器绑定
        channel.queueBind(queueName, exchangeName, routineKey);

        while (true) {
            // 消费消息
            boolean autoAck = false;
            String consumerTag = "";
            channel.basicConsume(queueName, autoAck, new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope,
                                           AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String routineKey = envelope.getRoutingKey();
                    String contentType = properties.getContentType();
                    System.out.printf("消费的路由键%s\n", routineKey);
                    System.out.printf("消费的内容类型%s\n", contentType);
                    long deliverTag = envelope.getDeliveryTag();
                    // 确认消息
                    channel.basicAck(deliverTag, false);
                    String bodyStr = new String(body, StandardCharsets.UTF_8);
                    System.out.printf("消费的消息体内容%s\n", bodyStr);
                }
            });
        }
    }
}
