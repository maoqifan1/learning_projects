package com.example.kafka.controller;

import com.example.kafka.service.SecondKillService;
import com.example.kafka.utils.ResultBuilder;
import com.example.kafka.utils.TipConstant;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class SecondKillController {
    @Resource
    SecondKillService secondKillService;

    @GetMapping("/stock")
    public String getStock() {
        return ResultBuilder.Map(ResultBuilder.ok(secondKillService.init()));
    }

    @PostMapping("/buy")
    public String buy() {
        return secondKillService.buy() ?
                ResultBuilder.Map(ResultBuilder.ok())
                : ResultBuilder.Map(ResultBuilder.error(TipConstant.ERROR));
    }
}
