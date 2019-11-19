package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.constant.RedisConstant;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.mapper.SetMealMapper;
import com.itheima.pojo.CheckGroup;
import com.itheima.pojo.Setmeal;
import com.itheima.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: LiXianG
 * Date: 2019/11/16 23:38
 * 体验套餐服务类
 */
@Service(interfaceClass = SetmealService.class)
@Transactional
public class SetmealServiceImpl implements SetmealService {

    @Autowired
    private SetMealMapper setMealMapper;
    @Autowired
    private JedisPool jedisPool;
    //查询所有套餐
    @Override
    public List<Setmeal> findAll() throws Exception {
        return setMealMapper.findAll();
    }
    //通过id查询当前套餐详情
    @Override
    public Setmeal findById(Integer id) throws Exception {
        return setMealMapper.finById(id);
    }

    //管理后台新增套餐
    @Override
    public void add(Setmeal setmeal, Integer[] checkgroupIds) throws Exception {
        //新增套餐，操作表t_setmeal
        setMealMapper.add(setmeal);
        Integer setmealId = setmeal.getId();
        this.setSetmealAndCheckgroup(setmealId,checkgroupIds);
        //将图片名称保存到Redis的（小）集合中
        String fileName = setmeal.getImg();
        jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_DB_RESOURCES,fileName);
    }

    @Override
    public PageResult findPage(QueryPageBean queryPageBean) throws Exception {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();
        //完成分页查询，基于mybatis框架提供的分页助手插件完成
        PageHelper.startPage(currentPage, pageSize);
        Page<Setmeal> page = setMealMapper.findByCondition(queryString);
        long total = page.getTotal();
        List<Setmeal> rows = page.getResult();
        return new PageResult(total, rows);
    }

    //设置套餐和检查组多对多关系，操作t_setmeal_checkgroup
    public void setSetmealAndCheckgroup(Integer setmealId,Integer[] checkgroupIds) throws Exception{
        if(checkgroupIds != null && checkgroupIds.length > 0){
            for (Integer checkgroupId : checkgroupIds) {
                Map<String,Integer> map = new HashMap<>();
                map.put("setmealId",setmealId);
                map.put("checkgroupId",checkgroupId);
                setMealMapper.setSetmealAndCheckgroup(map);
            }
        }
    }
}
