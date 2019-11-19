package com.itheima.mapper;

import com.github.pagehelper.Page;
import com.itheima.pojo.Setmeal;

import java.util.List;
import java.util.Map;

/**
 * Author: LiXianG
 * Date: 2019/11/16 23:42
 */
public interface SetMealMapper {
    List<Setmeal> findAll() throws Exception;

    Setmeal finById(Integer id) throws Exception;

    void add(Setmeal setmeal) throws Exception;

    void setSetmealAndCheckgroup(Map<String, Integer> map) throws Exception;

    Page<Setmeal> findByCondition(String queryString) throws Exception;
}
