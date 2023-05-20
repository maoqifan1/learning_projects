package com.maoqifan.mq.listener;

import com.maoqifan.mq.pojo.TEvent;
import com.maoqifan.mq.service.PointEventService;
import com.maoqifan.mq.utils.BusinessException;
import com.maoqifan.mq.utils.EventProcess;
import com.maoqifan.mq.utils.EventType;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class PointMessageListener implements MessageListener {
    @Resource
    PointEventService pointEventService;

    @Override
    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            try {
                // 获得消息
                TextMessage textMessage = (TextMessage) message;
                // 获得事件内容
                String eventContent = textMessage.getText();
                System.out.println("队列监听器接收到文本消息:" + eventContent);
                if (StringUtils.hasText(eventContent)) {
                    // 新增事件
                    TEvent event = TEvent.builder().content(eventContent).type(EventType.NEW_POINT.getValue()).process(EventProcess.PUBLISHED.getValue()).build();
                    // 在ds2新增记录
                    pointEventService.newEvent(event);

                }
            } catch (JMSException e) {
                throw new BusinessException("接受消息处理过程中出错");
            }
        } else {
            throw new IllegalArgumentException("仅支持TextMessage类型消息");
        }
    }
}
