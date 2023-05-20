package com.maoqifan.amqp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public class RabbitMQMessageProducer {
    @Resource
    AmqpTemplate amqpTemplate;


    public void publish(Object payload, String exchange, String routingKey) {
        log.info("Publishing to {} using routingKey {}. payload: {}", exchange, routingKey, payload);
        amqpTemplate.convertAndSend(exchange, routingKey, payload);
        log.info("Published to {} using routingKey {}. payload: {}", exchange, routingKey, payload);

    }

}
