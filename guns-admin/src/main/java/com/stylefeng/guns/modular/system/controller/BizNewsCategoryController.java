package com.stylefeng.guns.modular.system.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.stylefeng.guns.core.base.controller.BaseController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;

import com.stylefeng.guns.core.log.LogObjectHolder;

import org.springframework.web.bind.annotation.RequestParam;

import com.stylefeng.guns.common.annotion.Permission;
import com.stylefeng.guns.common.constant.Const;
import com.stylefeng.guns.common.persistence.model.BizNewsCategory;
import com.stylefeng.guns.modular.system.dao.BizNewsCategoryDao;
import com.stylefeng.guns.modular.system.service.IBizNewsCategoryService;
import com.stylefeng.guns.modular.system.warpper.BizNewsCategoryWarpper;

/**
 * 控制器
 *
 * @author fengshuonan
 * @Date 2018-01-22 09:30:43
 */
@Controller
@RequestMapping("/bizNewsCategory")
public class BizNewsCategoryController extends BaseController {

    private String PREFIX = "/system/bizNewsCategory/";

    @Autowired
    private IBizNewsCategoryService bizNewsCategoryService;
    
    @Resource
    BizNewsCategoryDao bizNewsCategoryDao;

    /**
     * 跳转到首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "bizNewsCategory.html";
    }

    /**
     * 跳转到添加
     */
    @RequestMapping("/bizNewsCategory_add")
    public String bizNewsCategoryAdd() {
        return PREFIX + "bizNewsCategory_add.html";
    }

    /**
     * 跳转到修改
     */
    @RequestMapping("/bizNewsCategory_update/{bizNewsCategoryId}")
    public String bizNewsCategoryUpdate(@PathVariable Integer bizNewsCategoryId, Model model) {
        BizNewsCategory bizNewsCategory = bizNewsCategoryService.selectById(bizNewsCategoryId);
        model.addAttribute("item",bizNewsCategory);
        LogObjectHolder.me().set(bizNewsCategory);
        return PREFIX + "bizNewsCategory_edit.html";
    }

    /**
     * 获取列表
     */
    @RequestMapping(value = "/list")
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public Object list(String condition) {
    	
    	List<Map<String,Object>> list = this.bizNewsCategoryDao.list(condition);
    	
        return super.warpObject(new BizNewsCategoryWarpper(list));
    }

    /**
     * 新增
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(BizNewsCategory bizNewsCategory) {
        bizNewsCategoryService.insert(bizNewsCategory);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer bizNewsCategoryId) {
        bizNewsCategoryService.deleteById(bizNewsCategoryId);
        return SUCCESS_TIP;
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(BizNewsCategory bizNewsCategory) {
        bizNewsCategoryService.updateById(bizNewsCategory);
        return super.SUCCESS_TIP;
    }

    /**
     * 详情
     */
    @RequestMapping(value = "/detail/{bizNewsCategoryId}")
    @ResponseBody
    public Object detail(@PathVariable("bizNewsCategoryId") Integer bizNewsCategoryId) {
        return bizNewsCategoryService.selectById(bizNewsCategoryId);
    }
}
