package com.maoqifan.demo;

import com.maoqifan.demo.pojo.User;

import java.util.Optional;

public class TestOptional {
    @SuppressWarnings("all")
    public static void main(final String... args) {
        // 1. 将可能为空的对象转换为optional对象
        User user = new User("maoqifan", "qq", 1);
        Optional<User> optionalUser = Optional.ofNullable(user);
        // 2. optional链式操作
        optionalUser.map(User::getUserName)
                .orElse("maoqifan"); // orElse给出默认值
        // 3. Optional短路
        // 从数据库查处某个用户，如果不存在则向数据库中新建用户并返回
        User user1 = optionalUser.orElseGet(() -> new User("hello", "mm", 2));
        // 4. Optional抛出异常
        optionalUser.orElseThrow(() -> new RuntimeException());
    }
}
