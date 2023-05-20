package com.example.kafka.config;

import com.example.kafka.utils.Constant;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.stereotype.Component;

import java.util.Map;

// 消息主题配置
@Configuration
@Component
public class TopicConfig {
    @Bean(name = "kafkaAdmin")
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> config = Map.of(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, Constant.BOOTSTRAP_SERVERS);
        return new KafkaAdmin(config);
    }

    // 页面浏览相关信息
    @Bean(name = Constant.TOPIC_PAGE)
    public NewTopic pageTopic() {
        return new NewTopic(Constant.TOPIC_PAGE, 10, (short) 2);
    }

    // 点击操作相关信息
    @Bean(name = Constant.TOPIC_CLICK)
    public NewTopic clickTopic() {
        return new NewTopic(Constant.TOPIC_CLICK, 10, (short) 2);
    }

    // 用于秒杀活动的消息主题
    @Bean(name = Constant.TOPIC_SECOND_KILL)
    public NewTopic secondKillTopic() {
        return new NewTopic(Constant.TOPIC_SECOND_KILL, 10, (short) 2);
    }

}
