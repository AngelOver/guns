package com.stylefeng.guns.modular.system.controller;

import java.util.Date;
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

import com.stylefeng.guns.common.persistence.model.BizNews;
import com.stylefeng.guns.modular.system.dao.BizNewsDao;
import com.stylefeng.guns.modular.system.service.IBizNewsService;
import com.stylefeng.guns.modular.system.warpper.BizNewsWarpper;


/**
 * 控制器
 *
 * @author fengshuonan
 * @Date 2018-01-22 14:32:00
 */
@Controller
@RequestMapping("/bizNews")
public class BizNewsController extends BaseController {

    private String PREFIX = "/system/bizNews/";

    @Autowired
    private IBizNewsService bizNewsService;
    
    @Resource
    BizNewsDao bizNewsDao;

    /**
     * 跳转到首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "bizNews.html";
    }

    /**
     * 跳转到添加
     */
    @RequestMapping("/bizNews_add")
    public String bizNewsAdd() {
        return PREFIX + "bizNews_add.html";
    }

    /**
     * 跳转到修改
     */
    @RequestMapping("/bizNews_update/{bizNewsId}")
    public String bizNewsUpdate(@PathVariable Integer bizNewsId, Model model) {
        BizNews bizNews = bizNewsService.selectById(bizNewsId);
        model.addAttribute("item",bizNews);
        LogObjectHolder.me().set(bizNews);
        return PREFIX + "bizNews_edit.html";
    }

    /**
     * 获取列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
    	List<Map<String,Object>> list = this.bizNewsDao.list(condition);
    	
         return super.warpObject(new BizNewsWarpper(list));
    }

    /**
     * 新增
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(BizNews bizNews) {
    	bizNews.setCreatedTime(new Date());
        bizNewsService.insert(bizNews);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer bizNewsId) {
        bizNewsService.deleteById(bizNewsId);
        return SUCCESS_TIP;
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(BizNews bizNews) {
        bizNewsService.updateById(bizNews);
        return super.SUCCESS_TIP;
    }

    /**
     * 详情
     */
    @RequestMapping(value = "/detail/{bizNewsId}")
    @ResponseBody
    public Object detail(@PathVariable("bizNewsId") Integer bizNewsId) {
        return bizNewsService.selectById(bizNewsId);
    }
}
