package com.maoqifan.mq.schedule;

import com.maoqifan.mq.pojo.TEvent;
import com.maoqifan.mq.service.PointEventService;
import com.maoqifan.mq.service.UserEventService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

@Component
public class PointScheduled {
    @Resource
    PointEventService pointEventService;

    // 每隔5秒查询一次事件表中是否存在处理过程为NEW的事件记录，如有则执行PointEventService的executeEvent方法
    @Scheduled(cron="0/5 * *  * * ? ")
    public void executeEvent() {
        List<TEvent> events = pointEventService.getPublishedEventList();
        if (!CollectionUtils.isEmpty(events)) {
            System.out.println("已发布的积分事件记录总数为：" + events.size());
            for (TEvent event : events) {
                pointEventService.executeEvent(event);
            }
        }
    }
}
