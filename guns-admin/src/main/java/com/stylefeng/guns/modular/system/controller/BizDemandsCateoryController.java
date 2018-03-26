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
import com.stylefeng.guns.common.persistence.model.BizDemandsCateory;
import com.stylefeng.guns.modular.system.service.IBizDemandsCateoryService;

/**
 * 控制器
 *
 * @author fengshuonan
 * @Date 2018-02-03 17:16:23
 */
@Controller
@RequestMapping("/bizDemandsCateory")
public class BizDemandsCateoryController extends BaseController {

    private String PREFIX = "/system/bizDemandsCateory/";

    @Autowired
    private IBizDemandsCateoryService bizDemandsCateoryService;

    /**
     * 跳转到首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "bizDemandsCateory.html";
    }

    /**
     * 跳转到添加
     */
    @RequestMapping("/bizDemandsCateory_add")
    public String bizDemandsCateoryAdd() {
        return PREFIX + "bizDemandsCateory_add.html";
    }

    /**
     * 跳转到修改
     */
    @RequestMapping("/bizDemandsCateory_update/{bizDemandsCateoryId}")
    public String bizDemandsCateoryUpdate(@PathVariable Integer bizDemandsCateoryId, Model model) {
        BizDemandsCateory bizDemandsCateory = bizDemandsCateoryService.selectById(bizDemandsCateoryId);
        model.addAttribute("item",bizDemandsCateory);
        LogObjectHolder.me().set(bizDemandsCateory);
        return PREFIX + "bizDemandsCateory_edit.html";
    }

    /**
     * 获取列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return bizDemandsCateoryService.selectList(null);
    }

    /**
     * 新增
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(BizDemandsCateory bizDemandsCateory) {
        bizDemandsCateoryService.insert(bizDemandsCateory);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer bizDemandsCateoryId) {
        bizDemandsCateoryService.deleteById(bizDemandsCateoryId);
        return SUCCESS_TIP;
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(BizDemandsCateory bizDemandsCateory) {
        bizDemandsCateoryService.updateById(bizDemandsCateory);
        return super.SUCCESS_TIP;
    }

    /**
     * 详情
     */
    @RequestMapping(value = "/detail/{bizDemandsCateoryId}")
    @ResponseBody
    public Object detail(@PathVariable("bizDemandsCateoryId") Integer bizDemandsCateoryId) {
        return bizDemandsCateoryService.selectById(bizDemandsCateoryId);
    }
}
