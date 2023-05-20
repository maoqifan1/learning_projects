package com.example.kafka.listener;

import com.example.kafka.utils.Constant;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;

public class KafkaConsumerListener {
    private final static Logger logger = LogManager.getLogger(KafkaListener.class);

    @KafkaListener(id = Constant.REPORT_DATA_CONTAINER, topics = {Constant.TOPIC_PAGE, Constant.TOPIC_CLICK})
    public void listen(ConsumerRecord<?, ?> record) {
        logger.info("监听到消息记录-------------" +
                "\ntopic = " + record.topic() +
                "\npartition = " + record.partition() +
                "\noffset = " + record.offset() +
                "\nkey = " + record.key() +
                "\nvalue = " + record.value() +
                "\n--------------------");

    }
}
