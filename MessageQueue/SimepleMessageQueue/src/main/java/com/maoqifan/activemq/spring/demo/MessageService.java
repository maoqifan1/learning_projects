package com.maoqifan.activemq.spring.demo;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.*;

@Service
public class MessageService {
    @Resource(name = "jmsTemplate")
    JmsTemplate jmsTemplate;

    @Resource(name = "testQueue")
    private Destination testQueue;

    @Resource(name = "testTopic")
    private Destination testTopic;

    // 向队列发送消息
    public void sendQueueMessage(String messageContent) {
        jmsTemplate.send(testQueue, session -> {
            TextMessage message = session.createTextMessage();
            message.setText(messageContent);
            return message;
        });
    }

    // 向主题发送消息
    public void sendTopicMessage(String messageContent) {
        jmsTemplate.send(testTopic,session -> {
            TextMessage message = session.createTextMessage();
            message.setText(messageContent);
            return message;
        });
    }
}
