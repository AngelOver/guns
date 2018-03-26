package com.stylefeng.guns.modular.system.controller;

import java.math.BigDecimal;
import java.util.Date;
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

import com.stylefeng.guns.common.annotion.Permission;
import com.stylefeng.guns.common.constant.Const;
import com.stylefeng.guns.common.persistence.model.BizPassport;
import com.stylefeng.guns.modular.system.dao.BizPassportDao;
import com.stylefeng.guns.modular.system.service.IBizPassportService;
import com.stylefeng.guns.modular.system.warpper.BizPassportWarpper;
import com.stylefeng.guns.modular.system.warpper.DictWarpper;

/**
 * 会员管理控制器
 *
 * @author dengshuang
 * @Date 2018-01-17 17:05:20
 */
@Controller
@RequestMapping("/bizPassport")
public class BizPassportController extends BaseController {

    private String PREFIX = "/system/bizPassport/";

    @Autowired
    private IBizPassportService bizPassportService;

    @Resource
    BizPassportDao bizPassportDao;
    
    /**
     * 跳转到首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "bizPassport.html";
    }

    /**
     * 跳转到添加
     */
    @RequestMapping("/bizPassport_add")
    public String bizPassportAdd() {
        return PREFIX + "bizPassport_add.html";
    }

    /**
     * 跳转到修改
     */
    @RequestMapping("/bizPassport_update/{bizPassportId}")
    public String bizPassportUpdate(@PathVariable Integer bizPassportId, Model model) {
        BizPassport bizPassport = bizPassportService.selectById(bizPassportId);
        model.addAttribute("item",bizPassport);
        LogObjectHolder.me().set(bizPassport);
        return PREFIX + "bizPassport_edit.html";
    }

    /**
     * 获取列表
     */
    @RequestMapping(value = "/list")
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public Object list(String condition) {   
    	 List<Map<String, Object>> list = this.bizPassportDao.list(condition);
    	 //bizPassportService.selectList(null);
        return super.warpObject(new BizPassportWarpper(list));
    }

    /**
     * 新增
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(BizPassport bizPassport) {
    	bizPassport.setCreatedAt(new Date());   	
    	/*if (bizPassport.getAvatar()!=null) {
    		bizPassport.setHeadimg(bizPassport.getAvatar());
		}else{
			bizPassport.setHeadimg("girl.gif");
		}    
    	bizPassport.setAvatar(null);*/
    	bizPassport.setStatus(2);
    	bizPassport.setVerify(2);
    	bizPassport.setScore(0L);
    	bizPassport.setBalance(new BigDecimal(0));
        bizPassportService.insert(bizPassport);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Long bizPassportId) {
        bizPassportService.deleteById(bizPassportId);
        return SUCCESS_TIP;
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(BizPassport bizPassport) {
        bizPassportService.updateById(bizPassport);
        return super.SUCCESS_TIP;
    }

    /**
     * 详情
     */
    @RequestMapping(value = "/detail/{bizPassportId}")
    @ResponseBody
    public Object detail(@PathVariable("bizPassportId") Integer bizPassportId) {
        return bizPassportService.selectById(bizPassportId);
    }
}
