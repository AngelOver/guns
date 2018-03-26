package com.stylefeng.guns.modular.system.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.stylefeng.guns.core.base.controller.BaseController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;

import com.stylefeng.guns.core.log.LogObjectHolder;

import org.springframework.web.bind.annotation.RequestParam;

import com.stylefeng.guns.common.persistence.model.BizServiceFieldValue;
import com.stylefeng.guns.common.persistence.model.Setup;
import com.stylefeng.guns.modular.system.service.ISetupService;

/**
 * 控制器
 *
 * @author fengshuonan
 * @Date 2018-01-29 11:06:36
 */
@Controller
@RequestMapping("/setup")
public class SetupController extends BaseController {

    private String PREFIX = "/system/setup/";

    @Autowired
    private ISetupService setupService;

    /**
     * 跳转到首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "setup.html";
    }

    /**
     * 跳转到添加
     */
    @RequestMapping("/setup_add")
    public String setupAdd() {
        return PREFIX + "setup_add.html";
    }

    /**
     * 跳转到修改
     */
    @RequestMapping("/setup_update/{setupId}")
    public String setupUpdate(@PathVariable Integer setupId, Model model) {
        Setup setup = setupService.selectById(setupId);
        model.addAttribute("item",setup);
        LogObjectHolder.me().set(setup);
        return PREFIX + "setup_edit.html";
    }

    /**
     * 获取列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String keyword) {
    if (keyword==null||"".equals(keyword.trim())) {
    		return setupService.selectList(null);
	}
        return setupService.selectList(new EntityWrapper<Setup>().like("keyword", keyword));
    }

    /**
     * 新增
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Setup setup) {
        setupService.insert(setup);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer setupId) {
        setupService.deleteById(setupId);
        return SUCCESS_TIP;
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Setup setup) {
        setupService.updateById(setup);
        return super.SUCCESS_TIP;
    }

    /**
     * 详情
     */
    @RequestMapping(value = "/detail/{setupId}")
    @ResponseBody
    public Object detail(@PathVariable("setupId") Integer setupId) {
        return setupService.selectById(setupId);
    }
}
