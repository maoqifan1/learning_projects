package com.example.kafka.listener;

import com.alibaba.fastjson2.JSONObject;
import com.example.kafka.utils.Constant;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;

import java.io.InputStream;

public class SecondKillListener {
    private final static Logger logger = LogManager.getLogger(SecondKillListener.class);

    @KafkaListener(id = Constant.SECOND_KILL_CONTAINER, topics = {Constant.TOPIC_SECOND_KILL})
    public void listen(ConsumerRecord<?, ?> record) {
        logger.info("监听到消息记录-------------" +
                "\ntopic = " + record.topic() +
                "\npartition = " + record.partition() +
                "\noffset = " + record.offset() +
                "\nkey = " + record.key() +
                "\nvalue = " + record.value() +
                "\n--------------------");
        if( record.value() != null){
            JSONObject jsonObject = JSONObject.parseObject(record.value().toString());
            logger.info("goods id: "+ jsonObject.get("goodsId"));
            logger.info("goods stock: "+ jsonObject.get("goodsStock"));
            // 获取到业务数据则可以进行相应的处理， 比如生成订单，短信通知等等
        }

    }


}
