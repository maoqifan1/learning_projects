package com.maoqifan.simplequeue;

public class ConsumeClient {
    public static void main(String[] args) throws Exception {
        System.out.println("消费的消息：" + MqClient.consume());
    }
}
