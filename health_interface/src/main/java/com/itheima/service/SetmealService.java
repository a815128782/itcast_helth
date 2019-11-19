package com.itheima.service;

import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.Setmeal;

import java.util.List;

/**
 * Author: LiXianG
 * Date: 2019/11/16 23:38
 */
public interface SetmealService {
    //查询所有套餐
    List<Setmeal> findAll() throws Exception;

    //通过id查询当前套餐详情
    Setmeal findById(Integer id) throws Exception;

    void add(Setmeal setmeal, Integer[] checkgroupIds) throws Exception;

    PageResult findPage(QueryPageBean queryPageBean) throws Exception;
}
