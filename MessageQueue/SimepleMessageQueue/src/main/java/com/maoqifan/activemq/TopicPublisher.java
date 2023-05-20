package com.maoqifan.activemq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class TopicPublisher {
    // 默认用户名
    private final static String USERNAME = ActiveMQConnection.DEFAULT_USER;
    // 默认密码
    private final static String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
    // 默认连接地址
    private final static String BROKER = ActiveMQConnection.DEFAULT_BROKER_URL;

    public static void main(final String... args) {
        // 创建连接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKER);
        try {
            // 创建连接
            Connection connection = connectionFactory.createConnection();
            // 开启连接
            connection.start();
            // 创建不带事务的会话
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            // 创建主题，用作消费者订阅信息
            Topic myTestTopic = session.createTopic("activemq-topic-test1");
            //  消息生产者
            MessageProducer producer = session.createProducer(myTestTopic);

            for (int i = 0; i <= 3; ++i) {
                TextMessage message = session.createTextMessage("发送消息 : " + i);
                producer.send(message);
            }
            session.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
