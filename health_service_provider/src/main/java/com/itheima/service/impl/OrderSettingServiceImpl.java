package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.mapper.OrderSettingMapper;
import com.itheima.pojo.OrderSetting;
import com.itheima.service.OrderSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Author: LiXianG
 * Date: 2019/11/18 14:33
 */
@Service(interfaceClass = OrderSettingService.class)
@Transactional
public class OrderSettingServiceImpl implements OrderSettingService {

    @Autowired
    private OrderSettingMapper orderSettingMapper;

    @Override
    public void add(List<OrderSetting> list) throws Exception {
        if (list != null && list.size() > 0) {
            for (OrderSetting orderSetting : list) {
                //判断当前日前是狗已经进行了预约设置
                long count = orderSettingMapper.findCountByOrderDate(orderSetting.getOrderDate());
                if (count > 0) {
                    //当前日期已经进行了预约设置，执行更新操作
                    orderSettingMapper.updateNumberByOrderDate(orderSetting);
                } else {
                    //没有进行预约设置，执行插入设置
                    orderSettingMapper.add(orderSetting);
                }
            }
        }
    }

    @Override
    public List<Map> getOrderSettingByMonth(String data) throws Exception {
        String begin = data + "-1";
        String end = data + "-31";
        Map<String,String> map = new HashMap<>();
        map.put("begin",begin);
        map.put("end",end);
        List<OrderSetting> orderSettingList = orderSettingMapper.getOrderSettingByMonth(map);
        List<Map> result = new ArrayList<>();
        if(orderSettingList!=null && orderSettingList.size() >0){
            for (OrderSetting orderSetting : orderSettingList) {
                Map<String,Object> stringObjectMap = new HashMap<>();
                //KEY 要和 页面相对应
                stringObjectMap.put("date",orderSetting.getOrderDate().getDate());//获取日期中的 日
                stringObjectMap.put("number",orderSetting.getNumber());
                stringObjectMap.put("reservations",orderSetting.getReservations());
                result.add(stringObjectMap);
            }
        }
        return result;
    }

    @Override
    public void updateNumberByDate(OrderSetting orderSetting) throws Exception {
        //根据日期查询是否已经进行预约设置
        Date orderDate = orderSetting.getOrderDate();
        long count = orderSettingMapper.findCountByOrderDate(orderDate);
        if(count > 0){
            //已经预约设置过了,更新
            orderSettingMapper.updateNumberByOrderDate(orderSetting);
        }else {
            //插入操作
            orderSettingMapper.add(orderSetting);
        }
    }
}
