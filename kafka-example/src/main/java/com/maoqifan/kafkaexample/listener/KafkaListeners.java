package com.maoqifan.kafkaexample.listener;

import com.maoqifan.kafkaexample.controller.MessageRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaListeners {

    @KafkaListener(topics = "maoqifan", groupId = "hello")
    void listen(MessageRequest data) {
        log.info("接收到数据：{}", data.getMessage());
    }
}
