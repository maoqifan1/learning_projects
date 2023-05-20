package com.maoqifan.eurekaprovider;

import net.minidev.json.JSONValue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@SpringBootApplication
@RestController
public class EurekaProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaProviderApplication.class, args);
    }
    // 给消费者的接口
    @GetMapping(value = "/provider/{id}")
    public String providerMethod(@PathVariable(value = "id") Integer id) {
        return JSONValue.toJSONString(Map.of(id.toString(), "Provider"));
    }


}
