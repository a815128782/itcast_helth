package com.itheima.service;

import com.itheima.pojo.OrderSetting;

import java.util.List;
import java.util.Map;

/**
 * Author: LiXianG
 * Date: 2019/11/18 14:32
 */
public interface OrderSettingService {
    void add(List<OrderSetting> data) throws Exception;

    List<Map> getOrderSettingByMonth(String data) throws Exception;

    //根据日期设置预约人数
    void updateNumberByDate(OrderSetting orderSetting) throws Exception;
}
