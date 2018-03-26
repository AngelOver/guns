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

import com.stylefeng.guns.common.persistence.model.BizAgentCase;
import com.stylefeng.guns.modular.system.dao.BizAgentCaseDao;
import com.stylefeng.guns.modular.system.service.IBizAgentCaseService;
import com.stylefeng.guns.modular.system.warpper.BizAgentCaseWarpper;


/**
 * 控制器
 *
 * @author fengshuonan
 * @Date 2018-02-09 14:45:10
 */
@Controller
@RequestMapping("/bizAgentCase")
public class BizAgentCaseController extends BaseController {

    private String PREFIX = "/system/bizAgentCase/";

    @Autowired
    private IBizAgentCaseService bizAgentCaseService;
   
    @Resource
    BizAgentCaseDao   bizAgentCaseDao;

    

    /**
     * 跳转到首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "bizAgentCase.html";
    }

    /**
     * 跳转到添加
     */
    @RequestMapping("/bizAgentCase_add")
    public String bizAgentCaseAdd() {
        return PREFIX + "bizAgentCase_add.html";
    }

    /**
     * 跳转到修改
     */
    @RequestMapping("/bizAgentCase_update/{bizAgentCaseId}")
    public String bizAgentCaseUpdate(@PathVariable Integer bizAgentCaseId, Model model) {
        BizAgentCase bizAgentCase = bizAgentCaseService.selectById(bizAgentCaseId);
        model.addAttribute("item",bizAgentCase);
        LogObjectHolder.me().set(bizAgentCase);
        return PREFIX + "bizAgentCase_edit.html";
    }

    /**
     * 获取列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
     List<Map<String,Object>> list = this.bizAgentCaseDao.list(condition);
    	
        return super.warpObject(new BizAgentCaseWarpper(list));
    }

    /**
     * 新增
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(BizAgentCase bizAgentCase) {
    	bizAgentCase.setCreatedAt(new Date());
        bizAgentCaseService.insert(bizAgentCase);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer bizAgentCaseId) {
        bizAgentCaseService.deleteById(bizAgentCaseId);
        return SUCCESS_TIP;
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(BizAgentCase bizAgentCase) {
        bizAgentCaseService.updateById(bizAgentCase);
        return super.SUCCESS_TIP;
    }

    /**
     * 详情
     */
    @RequestMapping(value = "/detail/{bizAgentCaseId}")
    @ResponseBody
    public Object detail(@PathVariable("bizAgentCaseId") Integer bizAgentCaseId) {
        return bizAgentCaseService.selectById(bizAgentCaseId);
    }
}
