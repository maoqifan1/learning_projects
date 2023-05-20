package com.maoqifan.mq.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

// 描述事件处理过程
@Getter
@AllArgsConstructor
public enum EventProcess {
    NEW("NEW", "新建"),
    PUBLISHED("PUBLISHED", "已发布"),
    PROCESSED("PROCESSED", "已处理");
    private final String value;
    private final String desc;
}
