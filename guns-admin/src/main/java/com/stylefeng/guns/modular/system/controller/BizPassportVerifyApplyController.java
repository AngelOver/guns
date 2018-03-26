package com.stylefeng.guns.modular.system.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.stylefeng.guns.core.base.controller.BaseController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;

import com.stylefeng.guns.core.log.LogObjectHolder;

import org.springframework.web.bind.annotation.RequestParam;

import com.stylefeng.guns.common.persistence.model.BizPassportVerifyApply;
import com.stylefeng.guns.modular.system.dao.BizPassportVerifyApplyDao;
import com.stylefeng.guns.modular.system.service.IBizPassportVerifyApplyService;
import com.stylefeng.guns.modular.system.warpper.BizPassportVerifyApplyWarpper;
import com.stylefeng.guns.modular.system.warpper.BizPassportWarpper;

/**
 * 实名认证申请控制器
 *
 * @author fengshuonan
 * @Date 2018-03-14 11:08:53
 */
@Controller
@RequestMapping("/bizPassportVerifyApply")
public class BizPassportVerifyApplyController extends BaseController {

    private String PREFIX = "/system/bizPassportVerifyApply/";

    @Autowired
    private IBizPassportVerifyApplyService bizPassportVerifyApplyService;
    
    @Resource
    BizPassportVerifyApplyDao bizPassportVerifyApplyDao;

    /**
     * 跳转到实名认证申请首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "bizPassportVerifyApply.html";
    }

    /**
     * 跳转到添加实名认证申请
     */
    @RequestMapping("/bizPassportVerifyApply_add")
    public String bizPassportVerifyApplyAdd() {
        return PREFIX + "bizPassportVerifyApply_add.html";
    }

    /**
     * 跳转到修改实名认证申请
     */
    @RequestMapping("/bizPassportVerifyApply_update/{bizPassportVerifyApplyId}")
    public String bizPassportVerifyApplyUpdate(@PathVariable Integer bizPassportVerifyApplyId, Model model) {
        BizPassportVerifyApply bizPassportVerifyApply = bizPassportVerifyApplyService.selectById(bizPassportVerifyApplyId);
        model.addAttribute("item",bizPassportVerifyApply);
        LogObjectHolder.me().set(bizPassportVerifyApply);
        return PREFIX + "bizPassportVerifyApply_edit.html";
    }

    /**
     * 获取实名认证申请列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
    	List<Map<String, Object>> list = this.bizPassportVerifyApplyDao.list(condition);
    	
        return super.warpObject(new BizPassportVerifyApplyWarpper(list));
    }

    /**
     * 新增实名认证申请
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(BizPassportVerifyApply bizPassportVerifyApply) {
        bizPassportVerifyApplyService.insert(bizPassportVerifyApply);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除实名认证申请
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer bizPassportVerifyApplyId) {
        bizPassportVerifyApplyService.deleteById(bizPassportVerifyApplyId);
        return SUCCESS_TIP;
    }

    /**
     * 修改实名认证申请
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(BizPassportVerifyApply bizPassportVerifyApply) {
        bizPassportVerifyApplyService.updateById(bizPassportVerifyApply);
        return super.SUCCESS_TIP;
    }

    /**
     * 实名认证申请详情
     */
    @RequestMapping(value = "/detail/{bizPassportVerifyApplyId}")
    @ResponseBody
    public Object detail(@PathVariable("bizPassportVerifyApplyId") Integer bizPassportVerifyApplyId) {
        return bizPassportVerifyApplyService.selectById(bizPassportVerifyApplyId);
    }
}
