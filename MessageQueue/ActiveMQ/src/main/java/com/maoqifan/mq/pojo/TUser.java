package com.maoqifan.mq.pojo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TUser {

    private String id;
    private String username;
}
