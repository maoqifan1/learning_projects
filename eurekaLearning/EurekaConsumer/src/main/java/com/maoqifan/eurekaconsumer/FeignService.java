package com.maoqifan.eurekaconsumer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
// 使用@FeignClient表示服务，值是提供着的名称
@FeignClient(value = "application-provider",path = "provider")
// 这里的值是提供者相应的路径
public interface FeignService {
    // 这里的路径和提供者一样，参数也一样
    @GetMapping("/{id}")
    String providerMethod(@PathVariable("id") int id);
}
