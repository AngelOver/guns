package com.stylefeng.guns.modular.system.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.stylefeng.guns.core.base.controller.BaseController;

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

import com.stylefeng.guns.common.persistence.model.BizDemands;
import com.stylefeng.guns.modular.system.service.IBizDemandsService;

/**
 * 控制器
 *
 * @author fengshuonan
 * @Date 2018-01-30 14:16:06
 */
@Controller
@RequestMapping("/bizDemands")
public class BizDemandsController extends BaseController {

    private String PREFIX = "/system/bizDemands/";

    @Autowired
    private IBizDemandsService bizDemandsService;
    
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
        return PREFIX + "bizDemands.html";
    }

    /**
     * 跳转到添加
     */
    @RequestMapping("/bizDemands_add")
    public String bizDemandsAdd() {
        return PREFIX + "bizDemands_add.html";
    }

    /**
     * 跳转到修改
     */
    @RequestMapping("/bizDemands_update/{bizDemandsId}")
    public String bizDemandsUpdate(@PathVariable Integer bizDemandsId, Model model) {
        BizDemands bizDemands = bizDemandsService.selectById(bizDemandsId);
        model.addAttribute("item",bizDemands);
        LogObjectHolder.me().set(bizDemands);
        return PREFIX + "bizDemands_edit.html";
    }

    /**
     * 获取列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return bizDemandsService.selectList(null);
    }

    /**
     * 新增
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(BizDemands bizDemands) {
        bizDemandsService.insert(bizDemands);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer bizDemandsId) {
        bizDemandsService.deleteById(bizDemandsId);
        return SUCCESS_TIP;
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(BizDemands bizDemands) {
        bizDemandsService.updateById(bizDemands);
        return super.SUCCESS_TIP;
    }

    /**
     * 详情
     */
    @RequestMapping(value = "/detail/{bizDemandsId}")
    @ResponseBody
    public Object detail(@PathVariable("bizDemandsId") Integer bizDemandsId) {
        return bizDemandsService.selectById(bizDemandsId);
    }
}
