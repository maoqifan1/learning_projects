package com.example.kafka.pojo;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class ReportClickData implements Serializable {
    private String tagType;
    private String content;
}
