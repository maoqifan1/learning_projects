package com.maoqifan.notification;

import com.maoqifan.clients.notification.NotificationRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@Service
public class NotificationService {
    @Resource
    NotificationRepository repo;

    public void send(NotificationRequest notificationRequest) {
        repo.save(
                Notification.builder()
                        .toCustomerId(notificationRequest.toCustomerId())
                        .toCustomerEmail(notificationRequest.toCustomerName())
                        .sender("Amigoscode")
                        .message(notificationRequest.message())
                        .sendAt(LocalDateTime.now())
                        .build()
        );
    }
}
