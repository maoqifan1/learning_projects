package com.maoqifan.rabbitmq.spring;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.support.AbstractApplicationContext;
public class SendMessage {
    public static void main(String[] args) throws InterruptedException{
        AbstractApplicationContext applicationContext = new ClassPathXmlApplicationContext("rabbitContext.xml");
        RabbitTemplate rabbitTemplate = applicationContext.getBean(RabbitTemplate.class);
        rabbitTemplate.convertAndSend("hello world");
        Thread.sleep(1000*10);
        applicationContext.close();
    }
}
