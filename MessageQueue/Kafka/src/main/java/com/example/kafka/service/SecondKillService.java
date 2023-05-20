package com.example.kafka.service;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.example.kafka.pojo.Goods;
import com.example.kafka.utils.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

@Service
@Slf4j
public class SecondKillService {
    private final static String goodsId = "123456"; // 商品id
    private final static int goodsStock = 10; // 初始化商品库存数量

    @Resource
    RedisTemplate<String, Object> redisTemplate;
    @Resource
    KafkaTemplate<String, String> kafkaTemplate;

    public Goods init() {
        Goods goods = Goods.builder().goodsId(goodsId).goodsStock(goodsStock).build();
        // 初始化商品,往列表中推入商品1
        redisTemplate.opsForValue().set("testGood", JSON.toJSONString(goods));

        return goods;
    }

    public boolean buy() {
        // 预先减去redis中的库存，如果库存数量足够减，则表示当前用户秒杀到了该商品，如果不够减则表示没有秒杀到该商品
        JSONObject obj = JSON.parseObject(Objects.requireNonNull(redisTemplate.opsForValue().get("testGood")).toString());
        Goods goods = Goods.builder().goodsId(obj.getString("goodsId")).goodsStock(obj.getInteger("goodsStock")).build();
        int stock = goods.getGoodsStock() - 1;
        if (stock < 0) {
            return false;
        }
        goods.setGoodsStock(stock);
        // 将业务数据写到消息队列

        // 同步的方式，获取结果，不推荐
//      SendResult<String, String> result = kafkaTemplate.send(Constant.TOPIC_SECOND_KILL, JSON.toJSONString(goods)).get();

        // 异步方式获取结果
        ListenableFuture<SendResult<String, String>> result = kafkaTemplate.send(Constant.TOPIC_SECOND_KILL, JSON.toJSONString(goods));
        result.addCallback((res) -> {
            log.info("生产者成功发送消息{}到{}",
                    Objects.requireNonNull(res).getProducerRecord().value(), res.getProducerRecord().topic());
        }, (ex) -> {
            log.error("发送消息失败:  {}", ex.getMessage());
        });


        return true;
    }
}
