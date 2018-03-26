package com.stylefeng.guns.modular.system.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.stylefeng.guns.core.base.controller.BaseController;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;

import com.stylefeng.guns.core.log.LogObjectHolder;

import org.springframework.web.bind.annotation.RequestParam;

import com.stylefeng.guns.common.persistence.model.BizOrders;
import com.stylefeng.guns.common.persistence.model.BizOrdersLog;
import com.stylefeng.guns.modular.system.service.IBizOrdersLogService;

/**
 * 控制器
 *
 * @author fengshuonan
 * @Date 2018-01-25 14:26:13
 */
@Controller
@RequestMapping("/bizOrdersLog")
public class BizOrdersLogController extends BaseController {

    private String PREFIX = "/system/bizOrdersLog/";

    @Autowired
    private IBizOrdersLogService bizOrdersLogService;
    
    
    @InitBinder
    protected void init(HttpServletRequest request, ServletRequestDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    /**
     * 跳转到首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "bizOrdersLog.html";
    }

    /**
     * 跳转到添加
     */
    @RequestMapping("/bizOrdersLog_add")
    public String bizOrdersLogAdd() {
        return PREFIX + "bizOrdersLog_add.html";
    }

    /**
     * 跳转到修改
     */
    @RequestMapping("/bizOrdersLog_update/{bizOrdersLogId}")
    public String bizOrdersLogUpdate(@PathVariable Integer bizOrdersLogId, Model model) {
        BizOrdersLog bizOrdersLog = bizOrdersLogService.selectById(bizOrdersLogId);
        model.addAttribute("item",bizOrdersLog);
        LogObjectHolder.me().set(bizOrdersLog);
        return PREFIX + "bizOrdersLog_edit.html";
    }

    /**
     * 获取列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {

    	//如果没有条件查询所有
    	if(StringUtils.isEmpty(condition)){
    		return bizOrdersLogService.selectList(null);
    	}
    	
    	//有条件，按条件查询
    	Wrapper<BizOrdersLog> wrapper = new EntityWrapper<>();
    	wrapper = wrapper.like("note", "%" + condition + "%");
        return bizOrdersLogService.selectList(wrapper);
    	
    }

    /**
     * 新增
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(BizOrdersLog bizOrdersLog) {
        bizOrdersLogService.insert(bizOrdersLog);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer bizOrdersLogId) {
        bizOrdersLogService.deleteById(bizOrdersLogId);
        return SUCCESS_TIP;
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(BizOrdersLog bizOrdersLog) {
        bizOrdersLogService.updateById(bizOrdersLog);
        return super.SUCCESS_TIP;
    }

    /**
     * 详情
     */
    @RequestMapping(value = "/detail/{bizOrdersLogId}")
    @ResponseBody
    public Object detail(@PathVariable("bizOrdersLogId") Integer bizOrdersLogId) {
        return bizOrdersLogService.selectById(bizOrdersLogId);
    }
    
    public static void main(String[] args){
    	
    	System.out.println(Integer.parseInt("1a"));
    	
    	
    }
}
