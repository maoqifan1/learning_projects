package com.example.kafka.utils;


import java.util.HashMap;
import java.util.Map;

/**
 * @author maoqifan
 */
@SuppressWarnings("ALL")
public enum TipConstant {
    ERROR("ERROR", "执行过程中发生错误"),
    SUCCESS("SUCCESS", "执行操作成功"),
    UNAUTHORIZED("UNAUTHORIZED", "用户未登录"),
    EMPTY_INPUT("EMPTY_INPUT", "获取用户输入信息值为空"),
    UPLOAD_ERROR("UPLOAD_ERROR", "获取用户上传的文件为空"),
    WRONG_FORMATTER("WRONG_FORMATTER", "用户上传的文件格式错误"),
    EMPTY_LIST("EMPTY_LIST", "输出的数据集为空"),
    ILLEGAL_STR("ILLEGAL_STR", "字符串格式错误"),
    ILLEGAL_NUMBER("ILLEGAL_NUMBER", "输入的数字大小有误"),
    ILLEGAL_OPERATION("ILLEGAL_OPERATION", "操作有误"),
    ILLEGAL_PATH("ILLEGAL_PATH", "路径错误"),
    NO_PROCESS_RUNNING("NO_PROCESS_RUNNING","..."),
    // 模糊测试
    GREY_BOX_TRAIN("GREYBOX_TRAIN","灰盒测试"),
    GREY_BOX_FUZZ("GREYBOX_TRAIN","灰盒测试"),
    BLACK_BOX_FUZZ("BLACKBOX_TRAIN","黑盒测试"),

    WHITE_BOX("WHITEBOX_TRAIN","白盒测试"),
    CAPTOR("captor","网络抓包");

    final String name;
    final String description;

    private TipConstant(String name, String description) {
        this.name = name;
        this.description = description;
    }

    private static final Map<String, TipConstant> descriptionMap;

    static {

        descriptionMap = new HashMap<>(TipConstant.values().length);
        for (TipConstant cfg : TipConstant.values()) {
            descriptionMap.put(cfg.name, cfg);
        }
    }


    public static TipConstant getErrorConfig(String name) {
        return descriptionMap.get(name);
    }


    public static String getName(TipConstant errorConfig, String methodName) {
        System.out.println(methodName + " : " +
                descriptionMap.get(errorConfig.name).description);

        return descriptionMap.get(errorConfig.name).name;
    }


    public static String getName(TipConstant errorConfig) {
        return descriptionMap.get(errorConfig.name).name;
    }

    public static String getExecutingMethodName() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement e = stackTrace[2];
        return e.getClassName() + " : " + e.getMethodName();
    }

    public static String getDescrition(TipConstant errorConfig) {
        return descriptionMap.get(errorConfig.name).description;
    }
}
