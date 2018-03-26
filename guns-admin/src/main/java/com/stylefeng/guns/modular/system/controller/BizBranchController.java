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

import com.stylefeng.guns.common.persistence.model.BizBranch;
import com.stylefeng.guns.modular.system.dao.BizBranchDao;
import com.stylefeng.guns.modular.system.service.IBizBranchService;
import com.stylefeng.guns.modular.system.warpper.BizBranchWarpper;


/**
 * 控制器
 *
 * @author fengshuonan
 * @Date 2018-01-30 09:17:23
 */
@Controller
@RequestMapping("/bizBranch")
public class BizBranchController extends BaseController {

    private String PREFIX = "/system/bizBranch/";

    @Autowired
    private IBizBranchService bizBranchService;
    
    @Resource
    BizBranchDao bizBranchDao;

    /**
     * 跳转到首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "bizBranch.html";
    }

    /**
     * 跳转到添加
     */
    @RequestMapping("/bizBranch_add")
    public String bizBranchAdd() {
        return PREFIX + "bizBranch_add.html";
    }

    /**
     * 跳转到修改
     */
    @RequestMapping("/bizBranch_update/{bizBranchId}")
    public String bizBranchUpdate(@PathVariable Integer bizBranchId, Model model) {
        BizBranch bizBranch = bizBranchService.selectById(bizBranchId);
        model.addAttribute("item",bizBranch);
        LogObjectHolder.me().set(bizBranch);
        return PREFIX + "bizBranch_edit.html";
    }

    /**
     * 获取列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
    	List<Map<String, Object>> list = this.bizBranchDao.list(condition);

        return super.warpObject(new BizBranchWarpper(list));
    }

    /**
     * 新增
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(BizBranch bizBranch) {
        bizBranchService.insert(bizBranch);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer bizBranchId) {
        bizBranchService.deleteById(bizBranchId);
        return SUCCESS_TIP;
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(BizBranch bizBranch) {
        bizBranchService.updateById(bizBranch);
        return super.SUCCESS_TIP;
    }

    /**
     * 详情
     */
    @RequestMapping(value = "/detail/{bizBranchId}")
    @ResponseBody
    public Object detail(@PathVariable("bizBranchId") Integer bizBranchId) {
        return bizBranchService.selectById(bizBranchId);
    }
}
