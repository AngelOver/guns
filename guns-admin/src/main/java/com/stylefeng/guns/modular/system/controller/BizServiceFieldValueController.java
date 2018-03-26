package com.stylefeng.guns.modular.system.controller;

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
import com.stylefeng.guns.modular.system.service.IBizServiceFieldValueService;

/**
 * 控制器
 *
 * @author fengshuonan
 * @Date 2018-01-22 11:29:56
 */
@Controller
@RequestMapping("/bizServiceFieldValue")
public class BizServiceFieldValueController extends BaseController {

    private String PREFIX = "/system/bizServiceFieldValue/";

    @Autowired
    private IBizServiceFieldValueService bizServiceFieldValueService;

    /**
     * 跳转到首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "bizServiceFieldValue.html";
    }

    /**
     * 跳转到添加
     */
    @RequestMapping("/bizServiceFieldValue_add")
    public String bizServiceFieldValueAdd() {
        return PREFIX + "bizServiceFieldValue_add.html";
    }

    /**
     * 跳转到修改
     */
    @RequestMapping("/bizServiceFieldValue_update/{bizServiceFieldValueId}")
    public String bizServiceFieldValueUpdate(@PathVariable Integer bizServiceFieldValueId, Model model) {
        BizServiceFieldValue bizServiceFieldValue = bizServiceFieldValueService.selectById(bizServiceFieldValueId);
        model.addAttribute("item",bizServiceFieldValue);
        LogObjectHolder.me().set(bizServiceFieldValue);
        return PREFIX + "bizServiceFieldValue_edit.html";
    }

    /**
     * 获取列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return bizServiceFieldValueService.selectList(null);
    }

    /**
     * 新增
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(BizServiceFieldValue bizServiceFieldValue) {
        bizServiceFieldValueService.insert(bizServiceFieldValue);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer bizServiceFieldValueId) {
        bizServiceFieldValueService.deleteById(bizServiceFieldValueId);
        return SUCCESS_TIP;
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(BizServiceFieldValue bizServiceFieldValue) {
        bizServiceFieldValueService.updateById(bizServiceFieldValue);
        return super.SUCCESS_TIP;
    }

    /**
     * 详情
     */
    @RequestMapping(value = "/detail/{bizServiceFieldValueId}")
    @ResponseBody
    public Object detail(@PathVariable("bizServiceFieldValueId") Integer bizServiceFieldValueId) {
        return bizServiceFieldValueService.selectById(bizServiceFieldValueId);
    }
}
