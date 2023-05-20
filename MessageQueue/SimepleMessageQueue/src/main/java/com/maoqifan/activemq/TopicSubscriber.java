package com.maoqifan.activemq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class TopicSubscriber {
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
            Connection connection = connectionFactory.createConnection();
            connection.start();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            // 创建主题
            Topic topic = session.createTopic("activemq-topic-test1");

            MessageConsumer messageConsumer = session.createConsumer(topic);
            messageConsumer.setMessageListener(message -> {
                try {
                    System.out.println("消费者1收到消息：" + ((TextMessage) message).getText());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            MessageConsumer messageConsumer2 = session.createConsumer(topic);
            messageConsumer2.setMessageListener(message -> {
                try {
                    System.out.println("消费者2收到消息：" + ((TextMessage) message).getText());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            MessageConsumer messageConsumer3 = session.createConsumer(topic);
            messageConsumer3.setMessageListener(message -> {
                try {
                    System.out.println("消费者3收到消息：" + ((TextMessage) message).getText());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            Thread.sleep(100 * 1000);
            session.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
