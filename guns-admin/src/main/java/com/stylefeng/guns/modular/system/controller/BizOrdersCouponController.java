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
import com.stylefeng.guns.common.persistence.model.BizOrdersCoupon;
import com.stylefeng.guns.modular.system.service.IBizOrdersCouponService;

/**
 * 控制器
 *
 * @author fengshuonan
 * @Date 2018-01-25 14:25:35
 */
@Controller
@RequestMapping("/bizOrdersCoupon")
public class BizOrdersCouponController extends BaseController {

    private String PREFIX = "/system/bizOrdersCoupon/";

    @Autowired
    private IBizOrdersCouponService bizOrdersCouponService;

    /**
     * 跳转到首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "bizOrdersCoupon.html";
    }

    /**
     * 跳转到添加
     */
    @RequestMapping("/bizOrdersCoupon_add")
    public String bizOrdersCouponAdd() {
        return PREFIX + "bizOrdersCoupon_add.html";
    }

    /**
     * 跳转到修改
     */
    @RequestMapping("/bizOrdersCoupon_update/{bizOrdersCouponId}")
    public String bizOrdersCouponUpdate(@PathVariable Integer bizOrdersCouponId, Model model) {
        BizOrdersCoupon bizOrdersCoupon = bizOrdersCouponService.selectById(bizOrdersCouponId);
        model.addAttribute("item",bizOrdersCoupon);
        LogObjectHolder.me().set(bizOrdersCoupon);
        return PREFIX + "bizOrdersCoupon_edit.html";
    }

    /**
     * 获取列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return bizOrdersCouponService.selectList(null);
    }

    /**
     * 新增
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(BizOrdersCoupon bizOrdersCoupon) {
        bizOrdersCouponService.insert(bizOrdersCoupon);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer bizOrdersCouponId) {
        bizOrdersCouponService.deleteById(bizOrdersCouponId);
        return SUCCESS_TIP;
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(BizOrdersCoupon bizOrdersCoupon) {
        bizOrdersCouponService.updateById(bizOrdersCoupon);
        return super.SUCCESS_TIP;
    }

    /**
     * 详情
     */
    @RequestMapping(value = "/detail/{bizOrdersCouponId}")
    @ResponseBody
    public Object detail(@PathVariable("bizOrdersCouponId") Integer bizOrdersCouponId) {
        return bizOrdersCouponService.selectById(bizOrdersCouponId);
    }
}
