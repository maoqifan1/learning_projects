package com.maoqifan.mq.mapper.ds2;

import com.maoqifan.mq.pojo.TPoint;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TPointMapper {
    Integer insert(TPoint tpoint);
}
