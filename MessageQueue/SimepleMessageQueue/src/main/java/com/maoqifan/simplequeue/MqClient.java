package com.maoqifan.simplequeue;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

// 消息队列客户端
public class MqClient {
    // 生产消息
    public static void produce(String message) throws Exception {
        Socket socket = new Socket(InetAddress.getLocalHost(), com.maoqifan.simplequeue.BrokerServer.SERVICE_PORT);
        try (PrintWriter out = new PrintWriter(socket.getOutputStream())) {
            out.println(message);
            out.flush();
        }
    }

    // 消费消息
    public static String consume() throws Exception {
        Socket socket = new Socket(InetAddress.getLocalHost(), com.maoqifan.simplequeue.BrokerServer.SERVICE_PORT);
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream())
        ) {
            // 发送CONSUME表示消费
            out.println("CONSUME");
            out.flush();
            // 从消息队列中获取要消费的消息
            String msg = in.readLine();

            return msg;
        }
    }
}
