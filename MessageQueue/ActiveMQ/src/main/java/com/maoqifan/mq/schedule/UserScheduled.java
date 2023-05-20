package com.maoqifan.mq.schedule;

import com.maoqifan.mq.pojo.TEvent;
import com.maoqifan.mq.service.UserEventService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

@Component
public class UserScheduled {
    @Resource
    UserEventService userEventService;

    // 每隔5秒查询一次事件表中是否存在处理过程为NEW的事件记录，如有则执行UserEventService的executeEvent方法
    @Scheduled(cron="0/5 * *  * * ? ")
    public void executeEvent() {
        List<TEvent> events = userEventService.getNewEventList();
        if (!CollectionUtils.isEmpty(events)) {
            System.out.println("新增用户的事件记录总数为：" + events.size());
            for (TEvent event : events) {
                userEventService.executeEvent(event);
            }
        }
    }
}
