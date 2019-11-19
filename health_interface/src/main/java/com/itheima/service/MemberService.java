package com.itheima.service;

import com.itheima.pojo.Member;

/**
 * Author: LiXianG
 * Date: 2019/11/18 20:46
 */
public interface MemberService {
    Member findByTelephone(String telephone) throws Exception;

    void add(Member member) throws Exception;
}
