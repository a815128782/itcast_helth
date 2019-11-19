package com.itheima.controller;

import com.itheima.constant.MessageConstant;
import com.itheima.constant.RedisMessageConstant;
import com.itheima.entity.Result;
import com.itheima.utils.SMSUtils;
import com.itheima.utils.ValidateCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

/**
 * Author: LiXianG
 * Date: 2019/11/17 15:25
 */
@RestController
@RequestMapping("/validateCode")
public class ValidateCodeController {

    @Autowired
    private JedisPool jedisPool;


    //用户在线体验预约发送验证码
    @RequestMapping("/send4Order")
    public Result send4Order(String telephone){
        //随机生成4位数验证按
        Integer validateCode = ValidateCodeUtils.generateValidateCode(4);
        //给用户发送验证码
        try {
            System.out.println(SMSUtils.VALIDATE_CODE);
            System.out.println(telephone);
            System.out.println(""+validateCode);
            SMSUtils.sendShortMessage(SMSUtils.VALIDATE_CODE, telephone, ""+validateCode);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, MessageConstant.SEND_VALIDATECODE_FAIL);
        }
        //将验证码保存到redis，并设置保存时间(5分钟
        jedisPool.getResource().setex(telephone+ RedisMessageConstant.SENDTYPE_ORDER,300,validateCode.toString());
        return new Result(true, MessageConstant.SEND_VALIDATECODE_SUCCESS);

    }

    //用户手机快速登录发送验证码
    @RequestMapping("/send4Login")
    public Result send4Login(String telephone){
        //随机生成4位数验证按
        Integer validateCode = ValidateCodeUtils.generateValidateCode(6);
        //给用户发送验证码
        try {
            System.out.println(SMSUtils.VALIDATE_CODE);
            System.out.println(telephone);
            System.out.println(""+validateCode);
            SMSUtils.sendShortMessage(SMSUtils.VALIDATE_CODE, telephone, ""+validateCode);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, MessageConstant.SEND_VALIDATECODE_FAIL);
        }
        //将验证码保存到redis，并设置保存时间(5分钟
        jedisPool.getResource().setex(telephone+ RedisMessageConstant.SENDTYPE_LOGIN,300,validateCode.toString());
        return new Result(true, MessageConstant.SEND_VALIDATECODE_SUCCESS);

    }
}
