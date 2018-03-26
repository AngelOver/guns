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

import com.stylefeng.guns.common.persistence.model.BizDemandsQuote;
import com.stylefeng.guns.common.persistence.model.BizOrdersLog;
import com.stylefeng.guns.modular.system.service.IBizDemandsQuoteService;

/**
 * 控制器
 *
 * @author fengshuonan
 * @Date 2018-01-31 14:30:35
 */
@Controller
@RequestMapping("/bizDemandsQuote")
public class BizDemandsQuoteController extends BaseController {

    private String PREFIX = "/system/bizDemandsQuote/";

    @Autowired
    private IBizDemandsQuoteService bizDemandsQuoteService;
    
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
        return PREFIX + "bizDemandsQuote.html";
    }

    /**
     * 跳转到添加
     */
    @RequestMapping("/bizDemandsQuote_add")
    public String bizDemandsQuoteAdd() {
        return PREFIX + "bizDemandsQuote_add.html";
    }

    /**
     * 跳转到修改
     */
    @RequestMapping("/bizDemandsQuote_update/{bizDemandsQuoteId}")
    public String bizDemandsQuoteUpdate(@PathVariable Integer bizDemandsQuoteId, Model model) {
        BizDemandsQuote bizDemandsQuote = bizDemandsQuoteService.selectById(bizDemandsQuoteId);
        model.addAttribute("item",bizDemandsQuote);
        LogObjectHolder.me().set(bizDemandsQuote);
        return PREFIX + "bizDemandsQuote_edit.html";
    }

    /**
     * 获取列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {

    	//如果没有条件查询所有
    	if(StringUtils.isEmpty(condition)){
    		return bizDemandsQuoteService.selectList(null);
    	}
    	
    	//有条件，按条件查询
    	Wrapper<BizDemandsQuote> wrapper = new EntityWrapper<>();
    	wrapper = wrapper.eq("price",  condition).or().eq("demand_id", condition).or().eq("agent_id", condition);
        return bizDemandsQuoteService.selectList(wrapper);
    }

    /**
     * 新增
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(BizDemandsQuote bizDemandsQuote) {
        bizDemandsQuoteService.insert(bizDemandsQuote);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer bizDemandsQuoteId) {
        bizDemandsQuoteService.deleteById(bizDemandsQuoteId);
        return SUCCESS_TIP;
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(BizDemandsQuote bizDemandsQuote) {
        bizDemandsQuoteService.updateById(bizDemandsQuote);
        return super.SUCCESS_TIP;
    }

    /**
     * 详情
     */
    @RequestMapping(value = "/detail/{bizDemandsQuoteId}")
    @ResponseBody
    public Object detail(@PathVariable("bizDemandsQuoteId") Integer bizDemandsQuoteId) {
        return bizDemandsQuoteService.selectById(bizDemandsQuoteId);
    }
}
