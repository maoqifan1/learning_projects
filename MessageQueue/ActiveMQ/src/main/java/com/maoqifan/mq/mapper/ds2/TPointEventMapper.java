package com.maoqifan.mq.mapper.ds2;

import com.maoqifan.mq.pojo.TEvent;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TPointEventMapper {
    Integer insert(TEvent tEvent);

    Integer updateProcess(long id, String process);

    List<TEvent> getByProcess(String process);
}
