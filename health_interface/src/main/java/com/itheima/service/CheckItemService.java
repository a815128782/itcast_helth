package com.itheima.service;

import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.CheckItem;

import java.util.List;

/**
 * Author: LiXianG
 * Date: 2019/11/15 11:41
 * 服务接口
 */
public interface CheckItemService {
    //新增检查项
    void add(CheckItem checkItem) throws Exception;

    //分页查询
    PageResult findPage(QueryPageBean queryPageBean) throws Exception;

    //根据ID删除检查项
    void deleteById(String id) throws Exception;

    //根据ID查询，回显数据
    CheckItem findById(String id) throws Exception;

    //根据ID修改检查项
    void updateById(CheckItem checkItem) throws Exception;

    //查询所有检查项，用于检查组
    List<CheckItem> findAll() throws Exception;
}
