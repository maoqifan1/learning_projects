package com.maoqifan.dproxy.jdk;

public class Test {
    public static void main(String... args) {
        SmsService smsService = (SmsService) JdkProxyFactory.getProxy(new SmsServiceImpl());
        smsService.send("hello world");
    }
}
