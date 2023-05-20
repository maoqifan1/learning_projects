package com.maoqifan.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * User pojo
 */
@Data
@AllArgsConstructor
public class User {
    @UserAnnotation(name = "用户名")
    private String userName;
    @UserAnnotation(name = "密码")
    private String password;
    @UserAnnotation(name = "性别", dictValue = "0=女,1=男")
    private int sex;
}
