package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.mapper.CheckItemMapper;
import com.itheima.pojo.CheckItem;
import com.itheima.service.CheckItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Author: LiXianG
 * Date: 2019/11/15 14:02
 */
@Service(interfaceClass = CheckItemService.class)
@Transactional
public class CheckItemServiceImpl implements CheckItemService {

    @Autowired
    private CheckItemMapper checkItemMapper;

    //检查项新增
    @Override
    public void add(CheckItem checkItem) throws Exception {
        checkItemMapper.add(checkItem);
    }

    //检查项分页查询
    @Override
    public PageResult findPage(QueryPageBean queryPageBean) throws Exception {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();
        //完成分页查询，基于mybatis框架提供的分页助手插件完成
        PageHelper.startPage(currentPage, pageSize);
        Page<CheckItem> page = checkItemMapper.findByCondition(queryString);
        long total = page.getTotal();
        List<CheckItem> rows = page.getResult();
        return new PageResult(total, rows);
    }

    //根据ID删除检查项
    @Override
    public void deleteById(String id) throws Exception {
        //判断当前检查项是否已经关联到检查组
        long count = checkItemMapper.findCountByCheckItemId(id);
        if (count > 0) {
            //当前检查项已经被关联到检查组，不允许删除
            new RuntimeException();
        }
            checkItemMapper.deleteById(id);

    }

    //根据ID查询，回显数据
    @Override
    public CheckItem findById(String id) throws Exception {
        return checkItemMapper.findById(id);
    }

    //根据ID修改检查项
    @Override
    public void updateById(CheckItem checkItem) throws Exception {
        checkItemMapper.updateById(checkItem);
    }

    //查询所有检查项，用于检查组
    @Override
    public List<CheckItem> findAll() throws Exception {
        return checkItemMapper.findAll();
    }
}
