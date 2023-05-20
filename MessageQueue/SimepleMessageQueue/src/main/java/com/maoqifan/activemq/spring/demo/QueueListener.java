package com.maoqifan.activemq.spring.demo;

import javax.jms.TextMessage;
import javax.jms.MessageListener;
import javax.jms.Message;

public class QueueListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            try {
                TextMessage txtMessage = (TextMessage) message;
                String messageStr = txtMessage.getText();
                System.out.println("队列监听收到文本消息 : " + messageStr);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            throw new IllegalArgumentException("只支持TextMessage类型消息");
        }
    }
}
