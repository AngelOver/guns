package com.stylefeng.guns.modular.system.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.exception.GunsException;

import org.flowable.engine.repository.Deployment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;

import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.core.util.ToolUtil;

import org.springframework.web.bind.annotation.RequestParam;

import com.stylefeng.guns.common.exception.BizExceptionEnum;
import com.stylefeng.guns.common.persistence.model.BizAgentPerson;
import com.stylefeng.guns.modular.system.dao.BizAgentPersonDao;
import com.stylefeng.guns.modular.system.service.IBizAgentPersonService;
import com.stylefeng.guns.modular.system.warpper.BizAgentPersonWarpper;

/**
 * 代理人列表控制器
 *
 * @author liuqunnnn
 * @Date 2018-03-03 17:28:05
 */
@Controller
@RequestMapping("/bizAgentPerson")
public class BizAgentPersonController extends BaseController {

    private String PREFIX = "/system/bizAgentPerson/";

    @Autowired
    private IBizAgentPersonService bizAgentPersonService;
    
    @Resource
    BizAgentPersonDao bizAgentPersonDao;

    /**
     * 跳转到代理人列表首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "bizAgentPerson.html";
    }

    /**
     * 跳转到添加代理人列表
     */
    @RequestMapping("/bizAgentPerson_add")
    public String bizAgentPersonAdd() {
        return PREFIX + "bizAgentPerson_add.html";
    }

    /**
     * 跳转到修改代理人列表
     */
    @RequestMapping("/bizAgentPerson_update/{bizAgentPersonId}")
    public String bizAgentPersonUpdate(@PathVariable Integer bizAgentPersonId, Model model) {
        BizAgentPerson bizAgentPerson = bizAgentPersonService.selectById(bizAgentPersonId);
        model.addAttribute("item",bizAgentPerson);
        LogObjectHolder.me().set(bizAgentPerson);
        return PREFIX + "bizAgentPerson_edit.html";
    }

    /**
     * 获取代理人列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {    
    	List<Map<String, Object>> list = this.bizAgentPersonDao.list(condition);
        return super.warpObject(new BizAgentPersonWarpper(list));
    }

    /**
     * 新增代理人列表
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(BizAgentPerson bizAgentPerson) {
    	
        bizAgentPersonService.insert(bizAgentPerson);
        return super.SUCCESS_TIP;
    }

    /**00
     * 删除代理人列表
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer bizAgentPersonId) {
    	bizAgentPersonService.deleteById(bizAgentPersonId);
        return SUCCESS_TIP;
    }

    /**
     * 修改代理人列表
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(BizAgentPerson bizAgentPerson) {
        bizAgentPersonService.updateById(bizAgentPerson);
        return super.SUCCESS_TIP;
    }

    /**
     * 代理人列表详情
     */
    @RequestMapping(value = "/detail/{bizAgentPersonId}")
    @ResponseBody
    public Object detail(@PathVariable("bizAgentPersonId") Integer bizAgentPersonId) {
        return bizAgentPersonService.selectById(bizAgentPersonId);
    }
}
