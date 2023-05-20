package com.maoqifan.kafkaexample.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Objects;

@RestController
@Slf4j
public class MessageController {
    @Resource
    KafkaTemplate<String, Object> kafkaTemplate;

    @PostMapping("/api/v1/messages")
    public void publish(@RequestBody MessageRequest messageRequest) {
        // 获得ListenableFuture，用法类似CompletableFuture
        ListenableFuture<SendResult<String, Object>> lf = kafkaTemplate.send("maoqifan", messageRequest.getMessage());
        // 利用lf添加回调函数（异步）
        lf.addCallback(res -> {
            log.info("发送消息: {}  ----> {}", Objects.requireNonNull(res).getProducerRecord().value().toString()
                    , Objects.requireNonNull(res).getProducerRecord().topic());
        }, ex -> {
            log.info("发送消息失败: {}", ex.getMessage());
        });
    }
}
