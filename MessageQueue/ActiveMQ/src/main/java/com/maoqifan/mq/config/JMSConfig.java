package com.maoqifan.mq.config;

import com.maoqifan.mq.listener.PointMessageListener;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.stereotype.Component;

import javax.jms.MessageListener;
import javax.jms.Session;

@Component
@Configuration
public class JMSConfig {
    @Value(value = "${spring.activemq.broker-url}")
    private String brokerUrl;
    @Value(value = "${spring.activemq.user}")
    private String user;
    @Value(value = "${spring.activemq.password")
    private String password;

    // connectionFactory
    @Bean(name = "activeMQConnectionFactory")
    public ActiveMQConnectionFactory activeMQConnectionFactory() {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
        activeMQConnectionFactory.setBrokerURL(brokerUrl);
        activeMQConnectionFactory.setUserName(user);
        activeMQConnectionFactory.setPassword(password);
        return activeMQConnectionFactory;
    }

    @Bean(name = "cachingConnectionFactory")
    public CachingConnectionFactory cachingConnectionFactory(@Qualifier("activeMQConnectionFactory") ActiveMQConnectionFactory connectionFactory) {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
        cachingConnectionFactory.setTargetConnectionFactory(connectionFactory);
        cachingConnectionFactory.setSessionCacheSize(10);
        return cachingConnectionFactory;
    }

    @Bean(name = "topicDistributedTransaction")
    public ActiveMQTopic activeMQTopic() {
        return new ActiveMQTopic("topic-distributed-transaction");
    }

    @Bean(name = "jmsTemplate")
    public JmsTemplate jmsTemplate(@Qualifier("cachingConnectionFactory") CachingConnectionFactory connectionFactory,
                                   @Qualifier("topicDistributedTransaction") ActiveMQTopic topic) {
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(connectionFactory);
        jmsTemplate.setDefaultDestination(topic);
        return jmsTemplate;
    }

    @Bean(name = "pointMessageListener")
    public MessageListener messageListener() {
        return new PointMessageListener();
    }

    @Bean(name = "topicContainer")
    public DefaultMessageListenerContainer messageListenerContainer(@Qualifier("cachingConnectionFactory") CachingConnectionFactory connectionFactory,
                                                                    @Qualifier("topicDistributedTransaction") ActiveMQTopic topic,
                                                                    @Qualifier("pointMessageListener") MessageListener messageListener
    ) {
        DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setDestination(topic);
        container.setMessageListener(messageListener);
        // 消费者通过receive和OnMessage方法收到消息后，自动确认
        container.setSessionAcknowledgeMode(Session.AUTO_ACKNOWLEDGE);
        return container;
    }

}
