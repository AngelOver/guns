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

import com.stylefeng.guns.common.persistence.model.BizAgentCompany;
import com.stylefeng.guns.modular.system.dao.BizAgentCompanyDao;
import com.stylefeng.guns.modular.system.service.IBizAgentCompanyService;
import com.stylefeng.guns.modular.system.warpper.BizAgentCompanyWarpper;


/**
 * 控制器
 *
 * @author fengshuonan
 * @Date 2018-01-25 09:34:20
 */
@Controller
@RequestMapping("/bizAgentCompany")
public class BizAgentCompanyController extends BaseController {

    private String PREFIX = "/system/bizAgentCompany/";

    @Autowired
    private IBizAgentCompanyService bizAgentCompanyService;
    
    @Resource
    BizAgentCompanyDao   bizAgentCompanyDao;

    /**
     * 跳转到首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "bizAgentCompany.html";
    }

    /**
     * 跳转到添加
     */
    @RequestMapping("/bizAgentCompany_add")
    public String bizAgentCompanyAdd() {
        return PREFIX + "bizAgentCompany_add.html";
    }

    /**
     * 跳转到修改
     */
    @RequestMapping("/bizAgentCompany_update/{bizAgentCompanyId}")
    public String bizAgentCompanyUpdate(@PathVariable Integer bizAgentCompanyId, Model model) {
        BizAgentCompany bizAgentCompany = bizAgentCompanyService.selectById(bizAgentCompanyId);
        model.addAttribute("item",bizAgentCompany);
        LogObjectHolder.me().set(bizAgentCompany);
        return PREFIX + "bizAgentCompany_edit.html";
    }

    /**
     * 获取列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
          List<Map<String,Object>> list = this.bizAgentCompanyDao.list(condition);
    	
        return super.warpObject(new BizAgentCompanyWarpper(list));
    }

    /**
     * 新增
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(BizAgentCompany bizAgentCompany) {
        bizAgentCompanyService.insert(bizAgentCompany);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer bizAgentCompanyId) {
        bizAgentCompanyService.deleteById(bizAgentCompanyId);
        return SUCCESS_TIP;
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(BizAgentCompany bizAgentCompany) {
        bizAgentCompanyService.updateById(bizAgentCompany);
        return super.SUCCESS_TIP;
    }

    /**
     * 详情
     */
    @RequestMapping(value = "/detail/{bizAgentCompanyId}")
    @ResponseBody
    public Object detail(@PathVariable("bizAgentCompanyId") Integer bizAgentCompanyId) {
        return bizAgentCompanyService.selectById(bizAgentCompanyId);
    }
}
