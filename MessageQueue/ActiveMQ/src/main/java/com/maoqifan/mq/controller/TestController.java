package com.maoqifan.mq.controller;

import com.alibaba.fastjson2.JSON;
import com.maoqifan.mq.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TestController {
    @Resource
    UserService userService;

    @PostMapping("/test")
    public String test() {
        return userService.newUser("maoqifan", 1000) ?
                JSON.toJSONString("success") : JSON.toJSONString("error");

    }
}
