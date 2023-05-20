package com.maoqifan.mq.service;

import com.maoqifan.mq.mapper.ds1.TUserEventMapper;
import com.maoqifan.mq.pojo.TEvent;
import com.maoqifan.mq.utils.BusinessException;
import com.maoqifan.mq.utils.EventProcess;
import com.maoqifan.mq.utils.EventType;

import javax.annotation.Resource;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Destination;
import javax.jms.Session;
import javax.jms.TextMessage;
import java.util.List;

@Service
public class UserEventService {
    @Resource
    private TUserEventMapper userEventMapper;

    @Resource(name = "jmsTemplate")
    private JmsTemplate jmsTemplate;

    @Resource(name = "topicDistributedTransaction")
    private Destination topic;

    public int newEvent(TEvent event) {
        if (event != null) {
            return userEventMapper.insert(event);
        } else {
            throw new BusinessException("参数为空");
        }
    }

    public List<TEvent> getNewEventList() {
        return userEventMapper.getByProcess(EventProcess.NEW.getValue());
    }

    public void executeEvent(TEvent event) {
        if (event != null) {
            String eventProcess = event.getProcess();
            // 如果是新增用户且event的流程当前在new状态
            if ((EventProcess.NEW.getValue().equals(eventProcess))
                    && (EventType.NEW_USER.getValue().equals(event.getType()))) {
                // 获得消息内容
                String messageContent = event.getContent();

                jmsTemplate.send(topic, (Session session) -> {
                    TextMessage message = session.createTextMessage();
                    message.setText(messageContent);
                    return message;
                });
                // 更新process为published
                event.setProcess(EventProcess.PUBLISHED.getValue());
                userEventMapper.updateProcess(event.getId(), event.getProcess());
            }
        }
    }

}
