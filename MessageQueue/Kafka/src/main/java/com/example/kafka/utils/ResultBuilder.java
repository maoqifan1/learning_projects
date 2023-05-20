package com.example.kafka.utils;

import com.alibaba.fastjson2.JSONObject;
import lombok.Data;
import org.springframework.stereotype.Component;


/**
 * @author maoqifan
 * @version 1.0
 * @description
 */
@Component
@Data
public final class ResultBuilder {
    // 定义json对象
    private static final JSONObject MAPPER = new JSONObject();

    // 响应业务状态
    private Integer code;

    // 响应消息
    private String message;

    // 状态码
    private static final Integer OK_CODE = 200;
    private static final Integer ERROR_CODE = 500;

    // 响应中的数据
    private Object data;

    public static ResultBuilder build(Integer code, TipConstant message, Object data) {
        return new ResultBuilder(code, TipConstant.getName(message), data);
    }
    public static ResultBuilder error(TipConstant message) {
        return new ResultBuilder(ERROR_CODE, TipConstant.getName(message), null);
    }

    public static ResultBuilder ok(Object data) {
        return new ResultBuilder(data);
    }

    public static ResultBuilder ok() {
        return new ResultBuilder(TipConstant.getName(TipConstant.SUCCESS));
    }

    /**
     * @author maoqifan
     * @description 把属性封装到map中, 并格式化为字符串
     */
    public static String Map(ResultBuilder resultBuilder) {
        MAPPER.put("code", resultBuilder.getCode());
        MAPPER.put("message", resultBuilder.getMessage());
        MAPPER.put("data", resultBuilder.getData());
        return MAPPER.toJSONString();
    }

    // 空构造
    public ResultBuilder() {
    }

    // 自定义状态
    public static ResultBuilder build(Integer status, TipConstant message) {
        return new ResultBuilder(status, TipConstant.getName(message), null);
    }

    public ResultBuilder(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResultBuilder(Object data) {
        this.code = OK_CODE;
        this.message = "OK";
        this.data = data;
    }
}
