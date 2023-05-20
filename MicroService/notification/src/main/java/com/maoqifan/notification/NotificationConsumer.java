package com.maoqifan.notification;

import com.maoqifan.clients.notification.NotificationRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public class NotificationConsumer {
    @Resource
    NotificationService notificationService;

    @RabbitListener(queues = "${rabbitmq.queues.notification}")
    public void consumer(NotificationRequest request
    ) {
        log.info("Consumed {} from queue", request);
        notificationService.send(
                request
        );
    }
}
