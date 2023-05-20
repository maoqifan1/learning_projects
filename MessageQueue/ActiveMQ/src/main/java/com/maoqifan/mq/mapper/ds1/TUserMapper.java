package com.maoqifan.mq.mapper.ds1;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TUserMapper {
    Integer insert(String id, String userName);

}
