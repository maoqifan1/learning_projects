package com.maoqifan.kafkaexample;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.CompletableFuture;

@SpringBootApplication
@EnableKafka
@Slf4j
public class KafkaExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaExampleApplication.class, args);
    }


    @Bean
    CommandLineRunner commandLineRunner(KafkaTemplate<String, Object> kafkaTemplate) {
        return args -> {
            ListenableFuture<SendResult<String, Object>> cf = kafkaTemplate.send("maoqifan", "hello kafka");
            cf.addCallback(res -> {
                log.info("发送消息成功: {}",res);
            }, ex -> {
                log.info("发送消息失败 : {}", ex.getMessage());
            });

        };
    }
}
