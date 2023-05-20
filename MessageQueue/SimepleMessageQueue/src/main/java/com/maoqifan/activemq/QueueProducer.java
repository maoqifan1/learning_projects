package com.maoqifan.activemq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class QueueProducer {
    // 默认用户名
    public final static String USERNAME = ActiveMQConnection.DEFAULT_USER;
    // 默认密码
    public final static String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
    // 默认连接地址
    public final static String BROKER_URL = ActiveMQConnection.DEFAULT_BROKER_URL;

    public static void main(String[] args) {
        // 创建连接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKER_URL);
        try {
            // 创建连接
            Connection connection = connectionFactory.createConnection();

            // 启动连接
            connection.start();
            // 创建会话
            Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            // 创建队列，需要指定队列名称，消息生产者和消费者根据他来发送、接收对应消息
            Queue myTestQueue = session.createQueue("activemq-queue-test1");
            // 消息生产者
            MessageProducer messageProducer = session.createProducer(myTestQueue);
            // 创建一个消息对象
            TextMessage message = session.createTextMessage("测试一条点对点消息");
            // 发送一条消息
            messageProducer.send(message);
            // 提交事务
            session.commit();
            // 关闭资源
            session.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
