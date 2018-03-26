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
import com.stylefeng.guns.common.persistence.model.BizOrdersInfo;
import com.stylefeng.guns.modular.system.service.IBizOrdersInfoService;

/**
 * 控制器
 *
 * @author fengshuonan
 * @Date 2018-01-25 14:25:56
 */
@Controller
@RequestMapping("/bizOrdersInfo")
public class BizOrdersInfoController extends BaseController {

    private String PREFIX = "/system/bizOrdersInfo/";

    @Autowired
    private IBizOrdersInfoService bizOrdersInfoService;

    /**
     * 跳转到首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "bizOrdersInfo.html";
    }

    /**
     * 跳转到添加
     */
    @RequestMapping("/bizOrdersInfo_add")
    public String bizOrdersInfoAdd() {
        return PREFIX + "bizOrdersInfo_add.html";
    }

    /**
     * 跳转到修改
     */
    @RequestMapping("/bizOrdersInfo_update/{bizOrdersInfoId}")
    public String bizOrdersInfoUpdate(@PathVariable Integer bizOrdersInfoId, Model model) {
        BizOrdersInfo bizOrdersInfo = bizOrdersInfoService.selectById(bizOrdersInfoId);
        model.addAttribute("item",bizOrdersInfo);
        LogObjectHolder.me().set(bizOrdersInfo);
        return PREFIX + "bizOrdersInfo_edit.html";
    }

    /**
     * 获取列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return bizOrdersInfoService.selectList(null);
    }

    /**
     * 新增
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(BizOrdersInfo bizOrdersInfo) {
        bizOrdersInfoService.insert(bizOrdersInfo);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer bizOrdersInfoId) {
        bizOrdersInfoService.deleteById(bizOrdersInfoId);
        return SUCCESS_TIP;
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(BizOrdersInfo bizOrdersInfo) {
        bizOrdersInfoService.updateById(bizOrdersInfo);
        return super.SUCCESS_TIP;
    }

    /**
     * 详情
     */
    @RequestMapping(value = "/detail/{bizOrdersInfoId}")
    @ResponseBody
    public Object detail(@PathVariable("bizOrdersInfoId") Integer bizOrdersInfoId) {
        return bizOrdersInfoService.selectById(bizOrdersInfoId);
    }
}
