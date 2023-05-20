package com.example.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;

import java.util.Map;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

public class ProducerSample {
    private final static String topic = "test-topic";

    public static void main(final String... args) {
        Map<String, Object> props = Map.of
                ("bootstrap.servers", "localhost:9092",
                        "zk.connect", "127.0.0.1:2181"
                );
        // kafka producer
        Producer<String, String> producer = new KafkaProducer<String, String>(props, new StringSerializer(), new StringSerializer());
        producer.send(new ProducerRecord<>(topic, "idea-key2", "java message 1"));
        producer.send(new ProducerRecord<>(topic, "idea-key2", "java message 2"));
        producer.send(new ProducerRecord<>(topic, "idea-key2", "java message 3"));
        producer.close();

    }
}
