package com.maoqifan.mq.pojo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TEvent {

    private long id;
    private String type;
    private String process;
    private String content;
    private java.sql.Timestamp createTime;
    private java.sql.Timestamp updateTime;


}
