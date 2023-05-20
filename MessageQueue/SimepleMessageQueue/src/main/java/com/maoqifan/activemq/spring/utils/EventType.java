package com.maoqifan.activemq.spring.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

// 描述事件类型
@AllArgsConstructor
@Getter
public enum EventType {
    NEW_USER("NEW_USER", "新增用户"),
    NEW_POINT("NEW_POINT", "新增积分");

    private final String value;
    private final String desc;


}
