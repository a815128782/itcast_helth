package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.constant.MessageConstant;
import com.itheima.entity.Result;
import com.itheima.mapper.MemberMapper;
import com.itheima.mapper.OrderMapper;
import com.itheima.mapper.OrderSettingMapper;
import com.itheima.pojo.Member;
import com.itheima.pojo.Order;
import com.itheima.pojo.OrderSetting;
import com.itheima.service.OrderService;
import com.itheima.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Author: LiXianG
 * Date: 2019/11/18 17:05
 */
@Service(interfaceClass = OrderService.class)
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderSettingMapper orderSettingMapper;
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private OrderMapper orderMapper;

    //体检预约
    @Override
    public Result submit(Map map) throws Exception {
        //1、检查用户所选择的预约日期是否已经提前进行了预约设置，如果没有设置则无法进行预约
        String orderDate = (String) map.get("orderDate");//预约日期
        Date date = DateUtils.parseString2Date(orderDate);
        OrderSetting orderSetting = orderSettingMapper.findByOrderDate(date);
        if (orderSetting == null) {
            //用户选择的日期没有进行预约设置，无法完成预约
            return new Result(false, MessageConstant.SELECTED_DATE_CANNOT_ORDER);
        }
        //2、检查用户所选择的预约日期是否已经约满，如果已经约满则无法预约
        if (orderSetting.getReservations() >= orderSetting.getNumber()) {
            //已经约满了无法预约
            return new Result(false, MessageConstant.ORDER_FULL);
        }
        //3、检查用户是否重复预约（同一个用户在同一天预约了同一个套餐），如果是重复预约则无法完成再次预约
        String telephone = (String) map.get("telephone");
        Member member = memberMapper.findByTelephone(telephone);
        if (member != null) {
            //有这个用户，再判断是否重复预约
            Integer member_id = member.getId();//会员的ID
            Date odder_Date = DateUtils.parseString2Date(orderDate);//预约的日期
            String setmeal_id = (String) map.get("setmealId");
            Order order = new Order(member_id, odder_Date, Integer.parseInt(setmeal_id));
            //根据条件查询
            List<Order> orderList = orderMapper.findByCondition(order);
            if (orderList != null && orderList.size() > 0) {
                //说明用户在重复预约
                return new Result(false, MessageConstant.HAS_ORDERED);
            }
        } else {
            //4、检查当前用户是否为会员，如果是会员则直接完成预约，如果不是会员则自动完成注册并进行预约
            member = new Member();
            member.setPhoneNumber(telephone);
            member.setName((String) map.get("name"));
            member.setIdCard((String) map.get("idCard"));
            member.setSex((String) map.get("sex"));
            member.setRegTime(new Date());
            memberMapper.add(member);//自动完成会员注册
        }

        //5、预约成功， t_order
        Order order = new Order();
        order.setMemberId(member.getId());//会员ID
        order.setOrderDate(DateUtils.parseString2Date(orderDate));//预约日期
        order.setOrderType((String) map.get("orderType"));//预约类型
        order.setOrderStatus(Order.ORDERSTATUS_NO);//到诊状态
        order.setSetmealId(Integer.parseInt((String) map.get("setmealId")));
        orderMapper.add(order);
        //更新当日的已预约人数 t_ordersetting
        orderSetting.setReservations(orderSetting.getReservations()+1);
        orderSettingMapper.updateReservationsByOrderDate(orderSetting);

        return new Result(true,MessageConstant.ORDER_SUCCESS,order.getId());
    }

    //根据预约ID 查询预约相关信息（体检人姓名，预约日期，套餐名称，预约类型）
    @Override
    public Map findById(Integer id) throws Exception {
        Map map = orderMapper.findById4Detail(id);
        if(map !=null){
            //处理日期格式
            Date orderDate = (Date) map.get("orderDate");
            map.put("orderDate",DateUtils.parseDate2String(orderDate));
        }
        return map;
    }
}
