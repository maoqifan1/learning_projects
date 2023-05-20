package com.maoqifan.eurekaconsumer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class FeignController {
    // 调用服务
    @Resource
    private FeignService feignService;

    @GetMapping("/consumer/{id}")
    public String ConsumerTest(@PathVariable("id") Integer id) {
        return feignService.providerMethod(id);
    }
}
