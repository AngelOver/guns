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
import com.stylefeng.guns.common.persistence.model.BizServiceImg;
import com.stylefeng.guns.modular.system.service.IBizServiceImgService;

/**
 * 控制器
 *
 * @author fengshuonan
 * @Date 2018-02-27 15:37:19
 */
@Controller
@RequestMapping("/bizServiceImg")
public class BizServiceImgController extends BaseController {

    private String PREFIX = "/system/bizServiceImg/";

    @Autowired
    private IBizServiceImgService bizServiceImgService;

    /**
     * 跳转到首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "bizServiceImg.html";
    }

    /**
     * 跳转到添加
     */
    @RequestMapping("/bizServiceImg_add")
    public String bizServiceImgAdd() {
        return PREFIX + "bizServiceImg_add.html";
    }

    /**
     * 跳转到修改
     */
    @RequestMapping("/bizServiceImg_update/{bizServiceImgId}")
    public String bizServiceImgUpdate(@PathVariable Integer bizServiceImgId, Model model) {
        BizServiceImg bizServiceImg = bizServiceImgService.selectById(bizServiceImgId);
        model.addAttribute("item",bizServiceImg);
        LogObjectHolder.me().set(bizServiceImg);
        return PREFIX + "bizServiceImg_edit.html";
    }

    /**
     * 获取列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return bizServiceImgService.selectList(null);
    }

    /**
     * 新增
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(BizServiceImg bizServiceImg) {
        bizServiceImgService.insert(bizServiceImg);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer bizServiceImgId) {
        bizServiceImgService.deleteById(bizServiceImgId);
        return SUCCESS_TIP;
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(BizServiceImg bizServiceImg) {
        bizServiceImgService.updateById(bizServiceImg);
        return super.SUCCESS_TIP;
    }

    /**
     * 详情
     */
    @RequestMapping(value = "/detail/{bizServiceImgId}")
    @ResponseBody
    public Object detail(@PathVariable("bizServiceImgId") Integer bizServiceImgId) {
        return bizServiceImgService.selectById(bizServiceImgId);
    }
}
