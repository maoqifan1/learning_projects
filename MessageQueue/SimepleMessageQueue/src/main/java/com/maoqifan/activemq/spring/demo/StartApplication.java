package com.maoqifan.activemq.spring.demo;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StartApplication {
    public static void main(final String ...args){
        AbstractApplicationContext ctx  =  new ClassPathXmlApplicationContext("activemqContext.xml");
        MessageService messageService = (MessageService) ctx.getBean("messageService");
        messageService.sendQueueMessage("测试消息1");
        messageService.sendTopicMessage("测试消息2");
        messageService.sendTopicMessage("测试消息3");
    }
}
