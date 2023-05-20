package com.example.kafka.controller;

import com.alibaba.fastjson2.JSON;
import com.example.kafka.pojo.ReportData;
import com.example.kafka.utils.Constant;
import com.example.kafka.utils.ResultBuilder;
import lombok.NonNull;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/report/pc")
public class ReportPcController {
    @Resource(name = "kafkaTemplate")
    KafkaTemplate<String, String> kafkaTemplate;

    @PostMapping(value = "/pageView")
    public String pageView(@NonNull @RequestBody ReportData reportData) {
        kafkaTemplate.send(Constant.TOPIC_PAGE, reportData.toString());

        return ResultBuilder.Map(ResultBuilder.ok());
    }

    @PostMapping(value = "/click")
    public String click(@NonNull @RequestBody ReportData reportData) {
        kafkaTemplate.send(Constant.TOPIC_PAGE, reportData.toString());
        return ResultBuilder.Map(ResultBuilder.ok());
    }

}
