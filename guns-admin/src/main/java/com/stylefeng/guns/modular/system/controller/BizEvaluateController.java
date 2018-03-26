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
import com.stylefeng.guns.common.persistence.model.BizEvaluate;
import com.stylefeng.guns.modular.system.service.IBizEvaluateService;

/**
 * 控制器
 *
 * @author fengshuonan
 * @Date 2018-02-08 16:13:02
 */
@Controller
@RequestMapping("/bizEvaluate")
public class BizEvaluateController extends BaseController {

    private String PREFIX = "/system/bizEvaluate/";

    @Autowired
    private IBizEvaluateService bizEvaluateService;

    /**
     * 跳转到首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "bizEvaluate.html";
    }

    /**
     * 跳转到添加
     */
    @RequestMapping("/bizEvaluate_add")
    public String bizEvaluateAdd() {
        return PREFIX + "bizEvaluate_add.html";
    }

    /**
     * 跳转到修改
     */
    @RequestMapping("/bizEvaluate_update/{bizEvaluateId}")
    public String bizEvaluateUpdate(@PathVariable Integer bizEvaluateId, Model model) {
        BizEvaluate bizEvaluate = bizEvaluateService.selectById(bizEvaluateId);
        model.addAttribute("item",bizEvaluate);
        LogObjectHolder.me().set(bizEvaluate);
        return PREFIX + "bizEvaluate_edit.html";
    }

    /**
     * 获取列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return bizEvaluateService.selectList(null);
    }

    /**
     * 新增
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(BizEvaluate bizEvaluate) {
        bizEvaluateService.insert(bizEvaluate);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer bizEvaluateId) {
        bizEvaluateService.deleteById(bizEvaluateId);
        return SUCCESS_TIP;
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(BizEvaluate bizEvaluate) {
        bizEvaluateService.updateById(bizEvaluate);
        return super.SUCCESS_TIP;
    }

    /**
     * 详情
     */
    @RequestMapping(value = "/detail/{bizEvaluateId}")
    @ResponseBody
    public Object detail(@PathVariable("bizEvaluateId") Integer bizEvaluateId) {
        return bizEvaluateService.selectById(bizEvaluateId);
    }
}
