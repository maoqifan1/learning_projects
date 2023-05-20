package com.maoqifan.mq.pojo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TPoint {

    private String id;
    private String userId;
    private long amount;

}
