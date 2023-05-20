package com.maoqifan.rabbitmq.spring;

import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.core.Message;

public class FooMessageListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        String messageBody = new String(message.getBody());
        System.out.println("消息体为："+messageBody+".");
    }
}
