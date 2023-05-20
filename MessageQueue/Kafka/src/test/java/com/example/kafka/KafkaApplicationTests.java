package com.example.kafka;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import javax.annotation.Resource;

@SpringBootTest
class KafkaApplicationTests {
    @Resource(name = "kafkaTemplate")
    KafkaTemplate<String, String> kafkaTemplate;

    @Test
    void contextLoads() {
        final String topic = "kafka-topic";
        kafkaTemplate.send(topic, "我的测试消息1");
        kafkaTemplate.send(topic, "我的测试消息2");
        kafkaTemplate.send(topic, "我的测试消息3");
    }

}
