package com.maoqifan.security.service;

import com.maoqifan.security.mapper.MemberMapper;
import com.maoqifan.security.pojo.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MemberService implements UserDetailsService {

    @Autowired
    MemberMapper memberMapper;

    @Override
    public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {
        Member member = memberMapper.loadMemberById(memberId);
        if(member == null){
            throw new UsernameNotFoundException("账号不存在");
        }
        member.setRoles(memberMapper.getMemberRolesById(member.getMemberId()));
        return member;
    }
}
