package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConstant;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.entity.Result;
import com.itheima.pojo.CheckItem;
import com.itheima.service.CheckItemService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Author: LiXianG
 * Date: 2019/11/15 11:35
 * 检查项管理
 */
@RestController()
@RequestMapping("/checkitem")
public class CheckItemController {

    @Reference
    private CheckItemService checkItemService;

    /**
     * 新增检查项
     * @param checkItem 检查项
     * @return
     */
    @RequestMapping(value = "/add")
    public Result add(@RequestBody CheckItem checkItem) {
        try {
            checkItemService.add(checkItem);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_CHECKITEM_FAIL);
        }

        return new Result(true, MessageConstant.ADD_CHECKITEM_SUCCESS);
    }

    /**
     * 分页查询
     * @param queryPageBean
     * @return
     * @throws Exception
     */
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) throws Exception {
        PageResult pageResult = checkItemService.findPage(queryPageBean);
        return pageResult;
    }


    /**
     * 根据Id删除检查项
     * @param id
     * @return
     */
    @RequestMapping("/deleteById")
    public Result deleteById(String id) {
        try {
            checkItemService.deleteById(id);
        } catch (Exception e) {
            return new Result(false, MessageConstant.DELETE_CHECKITEM_FAIL);
        }
        return new Result(true, MessageConstant.DELETE_CHECKITEM_SUCCESS);
    }


    /**
     * 通过ID查询检查项，用于数据回显
     * @param id
     * @return
     */
    @RequestMapping("/findById")
    public Result findById(String id) {
        CheckItem checkItem = null;
        try {
            checkItem = checkItemService.findById(id);
        } catch (Exception e) {
            return new Result(false, MessageConstant.QUERY_CHECKITEM_FAIL);
        }
        return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS,checkItem);
    }

    /**
     * 根据id修改检查项
     * @param checkItem
     * @return
     */
    @RequestMapping("/updateById")
    public Result updateById(@RequestBody CheckItem checkItem) {

        try {
            checkItemService.updateById(checkItem);
        } catch (Exception e) {
            return new Result(false, MessageConstant.EDIT_CHECKITEM_FAIL);
        }
        return new Result(true, MessageConstant.EDIT_CHECKITEM_SUCCESS);
    }

    /**
     * 查询所有检查项，用于检查组
     * @return
     */
    @RequestMapping("/findAll")
    public Result findAll() {
        List<CheckItem> checkItemList = null;
        try {
            checkItemList = checkItemService.findAll();
        } catch (Exception e) {
            return new Result(false, MessageConstant.QUERY_CHECKITEM_FAIL);
        }
        return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS,checkItemList);
    }

}
