package com.itheima.mapper;

import com.github.pagehelper.Page;
import com.itheima.pojo.Member;

import java.util.List;

public interface MemberMapper {
    public List<Member> findAll() throws Exception;
    public Page<Member> selectByCondition(String queryString) throws Exception;
    public void add(Member member) throws Exception;
    public void deleteById(Integer id) throws Exception;
    public Member findById(Integer id) throws Exception;
    public Member findByTelephone(String telephone) throws Exception;
    public void edit(Member member) throws Exception;
    public Integer findMemberCountBeforeDate(String date) throws Exception;
    public Integer findMemberCountByDate(String date) throws Exception;
    public Integer findMemberCountAfterDate(String date) throws Exception;
    public Integer findMemberTotalCount() throws Exception;
}
