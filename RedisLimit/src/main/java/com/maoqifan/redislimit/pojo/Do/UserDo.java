package com.maoqifan.redislimit.pojo.Do;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.Optional;

@Data
@Builder

public class UserDo {
    private String userName;
    private String password;
    private int old;

    @Resource
    RedisTemplate<String, Object> redisTemplate;

    public void test(){
        Object key = Objects.requireNonNull(redisTemplate.opsForValue().get("test"));
        if(key != null){

        }

    }
    public static void main(String... args) {
    }
}
