package com.maoqifan.security.mapper;

import com.maoqifan.security.pojo.Member;
import com.maoqifan.security.pojo.Role;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface MemberMapper {
    Member loadMemberById(String memberId);

    List<Role> getMemberRolesById(String memberId);

}
