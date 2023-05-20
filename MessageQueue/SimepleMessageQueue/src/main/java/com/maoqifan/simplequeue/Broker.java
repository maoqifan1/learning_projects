package com.maoqifan.simplequeue;

import java.util.concurrent.ArrayBlockingQueue;

public class Broker {
    // 队列存储消息的最大数量
    private final static int MAX_SIZE = 3;

    // 保存消息数据的容器
    private final static ArrayBlockingQueue<String> messageQueue = new ArrayBlockingQueue<>(MAX_SIZE);

    // 生产消息
    public static void produces(String msg) {
        if (messageQueue.offer(msg)) {
            System.out.printf("成功向消息处理中心投递消息:%s,当前消息数:%d\n", msg, messageQueue.size());
        } else {
            System.out.println("消息队列已满，不能继续存储");
        }
        System.out.println("=====================");
    }

    // 消费消息
    public static String consume() {
        String msg = messageQueue.poll(); // 从队列中取一条消息
        if (msg != null) {
            // 有消息从容器中取出
            System.out.printf("已消费消息:%s, 当前暂存消息数:%d\n", msg, messageQueue.size());
        } else {
            System.out.println("消息队列没有可供消费的消息");
        }
        System.out.println("=====================");
        return msg;
    }

}
