package com.example.kafka;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;

import java.util.List;
import java.util.Map;

public class ConsumerSample {
    private static final String topic = "test-topic";

    public static void main(final String... args) {
        Map<String, Object> props = Map.of(
                "bootstrap.servers", "localhost:9092",
                "group.id", "testGroup1",
                "enable.auto.commit", "true",
                "auto.commit.interval.ms", "1000"
        );
        Thread.currentThread().setContextClassLoader(null);
        Consumer<String, String> consumer = new KafkaConsumer<>(props, new StringDeserializer(), new StringDeserializer());
        consumer.subscribe(List.of(topic));
        while (true) {
            // Consumer调用方法poll轮询kafka集群的消息，一直等到kafka集群中没有消息或者达到超过时间(这里是100毫秒)
            ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
                System.out.printf("partition=%d, offset=%d, key=%s, value=%s\n",
                        consumerRecord.partition(), consumerRecord.offset(), consumerRecord.key(), consumerRecord.value());
            }
        }
    }
}
