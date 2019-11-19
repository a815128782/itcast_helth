package com.itheima.mapper;

import com.github.pagehelper.Page;
import com.itheima.pojo.CheckGroup;

import java.util.List;
import java.util.Map;

/**
 * Author: LiXianG
 * Date: 2019/11/16 9:24
 */
public interface CheckGroupMapper {

    //分页查询，条件
    Page<CheckGroup> findByCondition(String queryString) throws Exception;

    void add(CheckGroup checkGroup) throws Exception;

    void setCheckGroupAndCheckItem(Map map) throws Exception;

    CheckGroup findById(String id) throws Exception;

    //更新检查组
    void update(CheckGroup checkGroup) throws Exception;

    void deleteAssoication(Integer id) throws Exception;

    void deleteById(Integer id) throws Exception;

    List<CheckGroup> findAll() throws Exception;
}
