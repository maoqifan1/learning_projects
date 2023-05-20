package com.maoqifan.mq.service;

import com.alibaba.fastjson2.JSON;
import com.maoqifan.mq.mapper.ds2.TPointEventMapper;
import com.maoqifan.mq.mapper.ds2.TPointMapper;
import com.maoqifan.mq.pojo.TEvent;
import com.maoqifan.mq.pojo.TPoint;
import com.maoqifan.mq.utils.BusinessException;
import com.maoqifan.mq.utils.EventProcess;
import com.maoqifan.mq.utils.EventType;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PointEventService {
    @Resource
    private TPointEventMapper pointEventMapper;
    @Resource
    private TPointMapper pointMapper;

    public int newEvent(TEvent event) {
        if (event != null) {
            return pointEventMapper.insert(event);
        } else {
            throw new BusinessException("参数为空");
        }
    }

    public List<TEvent> getPublishedEventList() {
        return pointEventMapper.getByProcess(EventProcess.PUBLISHED.getValue());
    }

    public void executeEvent(TEvent event) {
        if (event != null) {
            String eventProcess = event.getProcess();
            // 如果是新增用户且event的流程当前在new状态
            if ((EventProcess.PUBLISHED.getValue().equals(eventProcess))
                    && (EventType.NEW_POINT.getValue().equals(event.getType()))) {
                // 获得消息内容
                TPoint point = JSON.parseObject(event.getContent(), TPoint.class);
                pointMapper.insert(point);
                // 更新process为published
                event.setProcess(EventProcess.PROCESSED.getValue());
                pointEventMapper.updateProcess(event.getId(), event.getProcess());
            }
        }
    }

}
