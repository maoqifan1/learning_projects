package com.example.kafka.pojo;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

// 行为发生过程中相关的元数据信息，该数据随着页面和点击的不同而不同
@Data
@Builder
public class ReportMetaData {
    private String title;
    private List<ReportClickData> clickData = new ArrayList<>();
}
