package com.maoqifan.activemq.spring.pojo;

import lombok.Data;

@Data
public class TEvent {

    private long id;
    private String type;
    private String process;
    private String content;
    private java.sql.Timestamp createTime;
    private java.sql.Timestamp updateTime;


}
