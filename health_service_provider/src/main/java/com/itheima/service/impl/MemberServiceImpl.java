package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.mapper.MemberMapper;
import com.itheima.pojo.Member;
import com.itheima.service.MemberService;
import com.itheima.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * Author: LiXianG
 * Date: 2019/11/18 20:47
 */
@Service(interfaceClass = MemberService.class)
@Transactional
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberMapper memberMapper;
    @Override
    public Member findByTelephone(String telephone) throws Exception {
        return memberMapper.findByTelephone(telephone);
    }

    @Override
    public void add(Member member) throws Exception {
        String password = member.getPassword();
        if(password != null){
            member.setPassword(MD5Utils.md5(password));
        }
        memberMapper.add(member);
    }
}
