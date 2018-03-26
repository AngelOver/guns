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

import com.stylefeng.guns.common.annotion.Permission;
import com.stylefeng.guns.common.constant.Const;
import com.stylefeng.guns.common.persistence.model.BizAgent;
import com.stylefeng.guns.modular.system.dao.BizAgentDao;
import com.stylefeng.guns.modular.system.service.IBizAgentService;
import com.stylefeng.guns.modular.system.warpper.BizAgentWarpper;
import com.stylefeng.guns.modular.system.warpper.DictWarpper;

/**
 * 控制器
 *
 * @author sjz
 * @Date 2018-01-19 09:28:07
 */
@Controller
@RequestMapping("/bizAgent")
public class BizAgentController extends BaseController {

    private String PREFIX = "/system/bizAgent/";

    @Autowired
    private IBizAgentService bizAgentService;
    
    @Resource
    BizAgentDao bizAgentDao;

    /**
     * 跳转到首页
     */
    @RequestMapping("")
    public String index() {
    	
        return PREFIX + "bizAgent.html";
    }

    /**
     * 跳转到添加
     */
    @RequestMapping("/bizAgent_add")
    public String bizAgentAdd() {
        return PREFIX + "bizAgent_add.html";
    }

    /**
     * 跳转到修改
     */
    @RequestMapping("/bizAgent_update/{bizAgentId}")
    public String bizAgentUpdate(@PathVariable Integer bizAgentId, Model model) {
        BizAgent bizAgent = bizAgentService.selectById(bizAgentId);
        model.addAttribute("item",bizAgent);
        LogObjectHolder.me().set(bizAgent);
        return PREFIX + "bizAgent_edit.html";
    }
    
    /**
     * 跳转到代理商详情页面
     */
    @RequestMapping("/bizAgent_info/{bizAgentId}")
    public String bizAgentInfo(@PathVariable Integer bizAgentId, Model model) {
        Map<String,Object> bizAgentinfo = bizAgentService.selectAgentInfoById(bizAgentId);
        if(bizAgentinfo !=null){
        model.addAttribute("item",bizAgentinfo);
        LogObjectHolder.me().set(bizAgentinfo);
        return PREFIX + "bizAgent_info.html";
        }
       return "null";
    }
    
    

    /**
     * 获取列表
     */
    @RequestMapping(value = "/list")
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public Object list(String condition) {
    	List<Map<String,Object>> list = this.bizAgentDao.list(condition);
    	
        return super.warpObject(new BizAgentWarpper(list));
    }

    /**
     * 新增
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(BizAgent bizAgent) {
    	bizAgent.setApplyStatus(0);
    	bizAgent.setCreatedAt(new Date());
        bizAgentService.insert(bizAgent);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer bizAgentId) {
        bizAgentService.deleteById(bizAgentId);
        return SUCCESS_TIP;
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(BizAgent bizAgent) {
        bizAgentService.updateById(bizAgent);
        return super.SUCCESS_TIP;
    }

    /**
     * 详情
     */
    @RequestMapping(value = "/detail/{bizAgentId}")
    @ResponseBody
    public Object detail(@PathVariable("bizAgentId") Integer bizAgentId) {
        return bizAgentService.selectById(bizAgentId);
    }
}
