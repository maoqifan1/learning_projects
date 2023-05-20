package com.maoqifan.activemq.spring.demo;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.transport.stomp.StompConnection;


public class StompProducer {
    public static void main(String[] args) {
        StompConnection connection = new StompConnection();
        try {
            connection.open("localhost", 61613);
            // 建立到代理服务器的连接
            connection.connect(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD);
            String message = "消息队列发来消息";
            // 使用Stomp发送消息
            connection.send("/topic/shopping-discount", message);

            connection.disconnect();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
