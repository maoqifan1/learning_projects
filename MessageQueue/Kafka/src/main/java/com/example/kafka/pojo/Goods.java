package com.example.kafka.pojo;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class Goods  {
    private String goodsId; // 商品id
    private int goodsStock; // 初始化商品库存数量
}
