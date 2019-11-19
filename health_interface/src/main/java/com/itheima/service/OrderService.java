package com.itheima.service;

import com.itheima.entity.Result;

import java.util.Map;

/**
 * Author: LiXianG
 * Date: 2019/11/17 16:15
 */
public interface OrderService {

    Result submit(Map map) throws Exception;

    Map findById(Integer id) throws Exception;
}
