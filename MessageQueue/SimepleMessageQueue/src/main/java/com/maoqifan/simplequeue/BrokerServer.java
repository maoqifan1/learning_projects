package com.maoqifan.simplequeue;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

// 对外提供Broker类的服务
public class BrokerServer implements Runnable {
    public static int SERVICE_PORT = 9999;

    private final Socket socket;

    public BrokerServer(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream())) {
            while (true) {
                String str = in.readLine();
                if (str == null)
                    return;
                System.out.printf("接收到原始数据: %s\n", str);
                if (str.equals("CONSUME")) { //  表示消费消息
                    String msg = Broker.consume();
                    out.println(msg);
                    out.flush();
                } else { // 生产消息
                    Broker.produces(str);
                }
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(SERVICE_PORT);
            while (true) {
                BrokerServer brokerServer = new BrokerServer(server.accept());
                new Thread(brokerServer).start();
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
}
