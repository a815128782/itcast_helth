package com.itheima.mapper;

import com.github.pagehelper.Page;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.CheckItem;

import java.util.List;

/**
 * Author: LiXianG
 * Date: 2019/11/15 14:04
 */
public interface CheckItemMapper {

    //新增检查项
    void add(CheckItem checkItem) throws Exception;

    //分页查询
    Page<CheckItem> findByCondition(String queryString) throws Exception;

    //根据ID删除
    void deleteById(String id) throws Exception;

    //删除检查项之前判断和检查组有无关联
    long findCountByCheckItemId(String id) throws Exception;

    //根据ID查询，回显数据
    CheckItem findById(String id) throws Exception;

    //根据ID修改检查项
    void updateById(CheckItem checkItem) throws Exception;

    //查询所有检查项，用于检查组
    List<CheckItem> findAll() throws Exception;

    List<CheckItem> findByCgId(String id) throws Exception;
}
