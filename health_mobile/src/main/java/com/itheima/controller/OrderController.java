package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConstant;
import com.itheima.constant.RedisMessageConstant;
import com.itheima.entity.Result;
import com.itheima.pojo.Order;
import com.itheima.service.OrderService;
import com.itheima.utils.SMSUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import java.util.Map;

/**
 * Author: LiXianG
 * Date: 2019/11/17 16:00
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private JedisPool jedisPool;

    @Reference
    private OrderService orderService;

    //
    @RequestMapping("/submit")
    public Result submit(@RequestBody Map map) {
        String telephone = (String) map.get("telephone");
        //获取redis中保存的验证码
        String validateCodeInRedis = jedisPool.getResource().get(telephone + RedisMessageConstant.SENDTYPE_ORDER);
        String validateCode = (String) map.get("validateCode");
        //进行验证码比对
        if (validateCodeInRedis != null && validateCode != null && validateCode.equals(validateCodeInRedis)) {
            //成功，进行下一步
            map.put("orderType", Order.ORDERTYPE_WEIXIN);
            Result result = null;
            try {
                result = orderService.submit(map);
            } catch (Exception e) {
                e.printStackTrace();
                return new Result(false, MessageConstant.ORDER_FULL);
            }
            if (result.isFlag()) {
                //预约成功，可以为用户发送短信
                try {
                    System.out.println(map.get("orderDate"));
                    System.out.println((String)map.get("orderDate"));
                    System.out.println(""+map.get("orderDate"));
                    SMSUtils.sendShortMessage(SMSUtils.ORDER_NOTICE, telephone, ""+ map.get("orderDate"));
                    System.out.println(123456);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            return result;
        } else {
            //不成功，返回结果给页面
            return new Result(false, MessageConstant.VALIDATECODE_ERROR);
        }
    }

    @RequestMapping("/findById")
    public Result findById(Integer id){
        try {
            Map map = orderService.findById(id);
            return new Result(true,MessageConstant.QUERY_ORDER_SUCCESS,map);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_ORDER_FAIL);
        }

    }
}
