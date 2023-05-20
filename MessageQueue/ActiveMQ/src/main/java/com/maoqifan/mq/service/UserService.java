package com.maoqifan.mq.service;

import com.alibaba.fastjson2.JSON;
import com.maoqifan.mq.mapper.ds1.TUserMapper;

import javax.annotation.Resource;

import com.maoqifan.mq.pojo.TEvent;
import com.maoqifan.mq.pojo.TPoint;
import com.maoqifan.mq.utils.EventProcess;
import com.maoqifan.mq.utils.EventType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class UserService {
    @Resource
    TUserMapper userMapper;
    @Resource
    UserEventService userEventService;

    @Transactional(transactionManager = "transactionManager1")
    public boolean newUser(String userName, Integer pointAmount) {
        // 用户id
        String id = UUID.randomUUID().toString().replace("-", "");
        String pid = UUID.randomUUID().toString();
        // 注册用户
        userMapper.insert(id, userName);
        // 新增事件
        TEvent event = TEvent.builder().process(EventProcess.NEW.getValue()).type(EventType.NEW_USER.getValue()).build();
        // 新增积分
        TPoint point = TPoint.builder().id(pid).userId(id).amount(pointAmount).build();
        // 将point对象的JSON字符串保存到事件表的content字段中
        event.setContent(JSON.toJSONString(point));
        // 新建事件
        return userEventService.newEvent(event) == 1;
    }
}
