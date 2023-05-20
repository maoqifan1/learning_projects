package com.maoqifan.eurekaconsumer;

import com.netflix.discovery.DiscoveryClient;
import com.netflix.eureka.resources.ApplicationResource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@SpringBootApplication

@EnableFeignClients // 启用Feign客户端
public class EurekaConsumerApplication {
//    @Resource
//    RestTemplate restTemplate;

/*    @GetMapping("/consumer/{id}")
    public String consumerTest(@PathVariable(value = "id") Integer id) {
        // 通过带有Ribbon功能的RestTemplate调用服务
        ResponseEntity<String> responseEntity
                // 注意这里的url是提供者的名称
                = restTemplate.exchange("http://provider-application/provider/" + id,
                HttpMethod.GET, null, String.class, id);
        return responseEntity.getBody();

    }

 */
    public static void main(String[] args) {
        SpringApplication.run(EurekaConsumerApplication.class, args);

    }
}
