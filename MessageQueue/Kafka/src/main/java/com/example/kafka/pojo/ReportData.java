package com.example.kafka.pojo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReportData {
    private String userId;
    private String createTime;
    private String page;
    private String operate;
    private ReportMetaData metaData;
}
