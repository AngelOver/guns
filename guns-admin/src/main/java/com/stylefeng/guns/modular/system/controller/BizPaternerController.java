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

import com.stylefeng.guns.common.persistence.model.BizPaterner;
import com.stylefeng.guns.modular.system.dao.BizPaternerDao;
import com.stylefeng.guns.modular.system.service.IBizPaternerService;
import com.stylefeng.guns.modular.system.warpper.BizPaternerWarpper;

/**
 * 控制器
 *
 * @author fengshuonan
 * @Date 2018-01-26 10:14:39
 */
@Controller
@RequestMapping("/bizPaterner")
public class BizPaternerController extends BaseController {

    private String PREFIX = "/system/bizPaterner/";

    @Autowired
    private IBizPaternerService bizPaternerService;
    
    @Resource
    BizPaternerDao bizPaternerDao;

    /**
     * 跳转到首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "bizPaterner.html";
    }

    /**
     * 跳转到添加
     */
    @RequestMapping("/bizPaterner_add")
    public String bizPaternerAdd() {
        return PREFIX + "bizPaterner_add.html";
    }

    /**
     * 跳转到修改
     */
    @RequestMapping("/bizPaterner_update/{bizPaternerId}")
    public String bizPaternerUpdate(@PathVariable Integer bizPaternerId, Model model) {
        BizPaterner bizPaterner = bizPaternerService.selectById(bizPaternerId);
        model.addAttribute("item",bizPaterner);
        LogObjectHolder.me().set(bizPaterner);
        return PREFIX + "bizPaterner_edit.html";
    }

    /**
     * 获取列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
    	
    	List<Map<String, Object>> list = this.bizPaternerDao.list(condition);

       return super.warpObject(new BizPaternerWarpper(list));
    }

    /**
     * 新增
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(BizPaterner bizPaterner) {
        bizPaternerService.insert(bizPaterner);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer bizPaternerId) {
        bizPaternerService.deleteById(bizPaternerId);
        return SUCCESS_TIP;
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(BizPaterner bizPaterner) {
        bizPaternerService.updateById(bizPaterner);
        return super.SUCCESS_TIP;
    }

    /**
     * 详情
     */
    @RequestMapping(value = "/detail/{bizPaternerId}")
    @ResponseBody
    public Object detail(@PathVariable("bizPaternerId") Integer bizPaternerId) {
        return bizPaternerService.selectById(bizPaternerId);
    }
}
