package com.maoqifan.security;

import com.maoqifan.security.mapper.MemberMapper;
import com.maoqifan.security.pojo.Member;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SecurityApplicationTests {
    @Autowired
    MemberMapper memberMapper;

    @Test
    public void contextLoads() {
//        Member member = memberMapper.loadMemberById("24691058");
//        System.out.println(member);
        System.out.println(memberMapper.getMemberRolesById("24691058"))
        ;
    }

}
