package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.mapper.CheckGroupMapper;
import com.itheima.pojo.CheckGroup;
import com.itheima.service.CheckGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: LiXianG
 * Date: 2019/11/16 9:23
 * 检查组服务
 */

@Service(interfaceClass = CheckGroupService.class)
@Transactional
public class CheckGroupServiceImpl implements CheckGroupService {

    @Autowired
    private CheckGroupMapper checkGroupMapper;

    //检查项分页查询
    @Override
    public PageResult findPage(QueryPageBean queryPageBean) throws Exception {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();
        //完成分页查询，基于mybatis框架提供的分页助手插件完成
        PageHelper.startPage(currentPage, pageSize);
        Page<CheckGroup> page = checkGroupMapper.findByCondition(queryString);
        long total = page.getTotal();
        List<CheckGroup> rows = page.getResult();
        return new PageResult(total, rows);
    }

    //新增检查组
    @Override
    public void add(CheckGroup checkGroup, Integer[] checkitemIds) throws Exception {
        //新增检查组，操作t_checkgroup表
        checkGroupMapper.add(checkGroup);
        //设置检查组和检查项的多对多的关系关联，操作t_checkgroup_checkitem表
        Integer checkGroupId = checkGroup.getId();
        setCheckGroupAndCheckItem(checkGroupId,checkitemIds);

    }

    //查询检查组
    @Override
    public CheckGroup findById(String id) throws Exception {
        return checkGroupMapper.findById(id);
    }

    //更新检查组信息，同时需要关联检查项
    @Override
    public void update(CheckGroup checkGroup, Integer[] checkitemIds) throws Exception {
        Integer checkGroupId = checkGroup.getId();
        //1.修改检查组基本信息，操作检查组t_checkgroup表
        checkGroupMapper.update(checkGroup);
        //2.清理当前检查组的关联项，操作中间关系表t_checkgroup_checkitem
        checkGroupMapper.deleteAssoication(checkGroupId);
        //3.重新建立当前检查组和检查项的关联关系
        setCheckGroupAndCheckItem(checkGroupId,checkitemIds);

    }

    @Override
    public void deleteById(Integer id) throws Exception {
        //1.清理当前检查组的关联项，操作中间关系表t_checkgroup_checkitem
        checkGroupMapper.deleteAssoication(id);
        //2.操作t_checkgroup删除检查组
        checkGroupMapper.deleteById(id);
    }

    @Override
    public List<CheckGroup> findAll() throws Exception {
        return checkGroupMapper.findAll();
    }

    //建立检查组和检查项多对多关系
    public void setCheckGroupAndCheckItem(Integer checkGroupId,Integer[] checkitemIds) throws Exception{
        if(checkitemIds != null && checkitemIds.length >0){
            for (Integer checkitemId : checkitemIds) {
                Map<String,Integer> map = new HashMap<>();
                map.put("checkGroupId",checkGroupId);
                map.put("checkitemId",checkitemId);
                checkGroupMapper.setCheckGroupAndCheckItem(map);
            }
        }
    }
}
