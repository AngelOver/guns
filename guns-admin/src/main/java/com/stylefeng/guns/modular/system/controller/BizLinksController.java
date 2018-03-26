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

import com.stylefeng.guns.common.persistence.model.BizLinks;
import com.stylefeng.guns.modular.system.dao.BizLinksDao;
import com.stylefeng.guns.modular.system.service.IBizLinksService;
import com.stylefeng.guns.modular.system.warpper.BizLinksWarpper;
import com.stylefeng.guns.modular.system.warpper.BizPaternerWarpper;

/**
 * 控制器
 *
 * @author fengshuonan
 * @Date 2018-01-29 11:34:49
 */
@Controller
@RequestMapping("/bizLinks")
public class BizLinksController extends BaseController {

    private String PREFIX = "/system/bizLinks/";

    @Autowired
    private IBizLinksService bizLinksService;
    
    
    @Resource
    BizLinksDao bizLinksDao;

    /**
     * 跳转到首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "bizLinks.html";
    }

    /**
     * 跳转到添加
     */
    @RequestMapping("/bizLinks_add")
    public String bizLinksAdd() {
        return PREFIX + "bizLinks_add.html";
    }

    /**
     * 跳转到修改
     */
    @RequestMapping("/bizLinks_update/{bizLinksId}")
    public String bizLinksUpdate(@PathVariable Integer bizLinksId, Model model) {
        BizLinks bizLinks = bizLinksService.selectById(bizLinksId);
        model.addAttribute("item",bizLinks);
        LogObjectHolder.me().set(bizLinks);
        return PREFIX + "bizLinks_edit.html";
    }

    /**
     * 获取列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
    	List<Map<String, Object>> list = this.bizLinksDao.list(condition);

        return super.warpObject(new BizLinksWarpper(list));
    }

    /**
     * 新增
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(BizLinks bizLinks) {
        bizLinksService.insert(bizLinks);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer bizLinksId) {
        bizLinksService.deleteById(bizLinksId);
        return SUCCESS_TIP;
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(BizLinks bizLinks) {
        bizLinksService.updateById(bizLinks);
        return super.SUCCESS_TIP;
    }

    /**
     * 详情
     */
    @RequestMapping(value = "/detail/{bizLinksId}")
    @ResponseBody
    public Object detail(@PathVariable("bizLinksId") Integer bizLinksId) {
        return bizLinksService.selectById(bizLinksId);
    }
}
