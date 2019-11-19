package com.itheima.mapper;

import com.itheima.pojo.OrderSetting;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Author: LiXianG
 * Date: 2019/11/18 14:41
 */
public interface OrderSettingMapper {
    void add(OrderSetting orderSetting) throws Exception;
    void updateNumberByOrderDate(OrderSetting orderSetting) throws Exception;
    long findCountByOrderDate(Date orderDate) throws Exception;

    //根据日期范围查询 预约设置
    List<OrderSetting> getOrderSettingByMonth(Map<String, String> map) throws Exception;

    OrderSetting findByOrderDate(Date date) throws Exception;

    //更新已预约人数
     void updateReservationsByOrderDate(OrderSetting orderSetting) throws Exception;
}
