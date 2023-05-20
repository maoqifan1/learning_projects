package com.maoqifan.activemq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class QueueConsumer {
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
            final Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            // 创建队列，作为消费者消费消息的目的地
            Queue myTestQueue = session.createQueue("activemq-queue-test1");
            // 消息消费者
            MessageConsumer messageConsumer = session.createConsumer(myTestQueue);
            //  消费者实现监听接口消费消息
            messageConsumer.setMessageListener(message -> {
                try {
                    TextMessage textMessage = (TextMessage) message;
                    System.out.println(textMessage.getText());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            try {
                session.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
            // 使主线程休息100秒，使消息消费者对象能够继续存活一段时间，从而能监听到消息
            Thread.sleep(100 * 1000);
            // 关闭资源
            session.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
