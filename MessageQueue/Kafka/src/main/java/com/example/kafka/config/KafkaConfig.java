package com.example.kafka.config;

import com.example.kafka.listener.KafkaConsumerListener;
import com.example.kafka.listener.SecondKillListener;
import com.example.kafka.utils.Constant;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

@Configuration
@Component
public class KafkaConfig {
    // 消息生产者工厂配置
    @Bean(name = "producerFactory")
    public ProducerFactory<String, String> producerFactory() {
        Map<String, Object> props = Map.of(
                "bootstrap.servers", "localhost:9092"
        );
        return new DefaultKafkaProducerFactory<>(props, new StringSerializer(), new StringSerializer());
    }

    @Bean(name = "kafkaTemplate")
    public KafkaTemplate<String, String> kafkaTemplate(@Qualifier("producerFactory") ProducerFactory<String, String> factory) {
        return new KafkaTemplate<>(factory, true);
    }

    // 消费者配置
    @Bean(name = "consumerFactory")
    public DefaultKafkaConsumerFactory<String, String> defaultKafkaConsumerFactory() {
        Map<String, Object> props = Map.of(
                "bootstrap.servers", "localhost:9092",
                "group.id", Constant.GROUP_ID_SECOND_KILL,
                "enable.auto.commit", "true",
                "auto.commit.interval.ms", "1000"
        );
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new StringDeserializer());
    }

    // 监听器配置
    @Bean(name = "secondKillListener")
    public SecondKillListener secondKillListener() {
        return new SecondKillListener();
    }

    @Bean(name = "kafkaListenerContainerFactory")
    public ConcurrentKafkaListenerContainerFactory<String, String> concurrentKafkaListenerContainerFactory(@Qualifier("consumerFactory") DefaultKafkaConsumerFactory<String, String> factory) {
        ConcurrentKafkaListenerContainerFactory<String, String> listenerContainerFactory = new ConcurrentKafkaListenerContainerFactory<>();
        listenerContainerFactory.setConsumerFactory(factory);
        return listenerContainerFactory;
    }

}
