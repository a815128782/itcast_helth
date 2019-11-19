package com.itheima.service;

import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.CheckGroup;

import java.util.List;

/**
 * Author: LiXianG
 * Date: 2019/11/16 9:22
 * 服务接口
 */
public interface CheckGroupService {

    //分页查询
    PageResult findPage(QueryPageBean queryPageBean) throws Exception;

    //新增检查组
    void add(CheckGroup checkGroup, Integer[] checkitemIds) throws Exception;

    //查询检查组
    CheckGroup findById(String id) throws Exception;

    void update(CheckGroup checkGroup, Integer[] checkitemIds) throws Exception;

    //删除检查组
    void deleteById(Integer id) throws Exception;

    List<CheckGroup> findAll() throws Exception;
}
