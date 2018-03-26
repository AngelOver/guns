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
import com.stylefeng.guns.common.persistence.model.BizServiceInfo;
import com.stylefeng.guns.modular.system.service.IBizServiceInfoService;

/**
 * 控制器
 *
 * @author fengshuonan
 * @Date 2018-02-27 16:46:30
 */
@Controller
@RequestMapping("/bizServiceInfo")
public class BizServiceInfoController extends BaseController {

    private String PREFIX = "/system/bizServiceInfo/";

    @Autowired
    private IBizServiceInfoService bizServiceInfoService;

    /**
     * 跳转到首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "bizServiceInfo.html";
    }

    /**
     * 跳转到添加
     */
    @RequestMapping("/bizServiceInfo_add")
    public String bizServiceInfoAdd() {
        return PREFIX + "bizServiceInfo_add.html";
    }

    /**
     * 跳转到修改
     */
    @RequestMapping("/bizServiceInfo_update/{bizServiceInfoId}")
    public String bizServiceInfoUpdate(@PathVariable Integer bizServiceInfoId, Model model) {
        BizServiceInfo bizServiceInfo = bizServiceInfoService.selectById(bizServiceInfoId);
        model.addAttribute("item",bizServiceInfo);
        LogObjectHolder.me().set(bizServiceInfo);
        return PREFIX + "bizServiceInfo_edit.html";
    }

    /**
     * 获取列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return bizServiceInfoService.selectList(null);
    }

    /**
     * 新增
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(BizServiceInfo bizServiceInfo) {
        bizServiceInfoService.insert(bizServiceInfo);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer bizServiceInfoId) {
        bizServiceInfoService.deleteById(bizServiceInfoId);
        return SUCCESS_TIP;
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(BizServiceInfo bizServiceInfo) {
        bizServiceInfoService.updateById(bizServiceInfo);
        return super.SUCCESS_TIP;
    }

    /**
     * 详情
     */
    @RequestMapping(value = "/detail/{bizServiceInfoId}")
    @ResponseBody
    public Object detail(@PathVariable("bizServiceInfoId") Integer bizServiceInfoId) {
        return bizServiceInfoService.selectById(bizServiceInfoId);
    }
}
