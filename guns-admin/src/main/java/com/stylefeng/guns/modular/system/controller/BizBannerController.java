package com.stylefeng.guns.modular.system.controller;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.stylefeng.guns.common.persistence.model.BizBanner;
import com.stylefeng.guns.common.persistence.model.Dept;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.modular.system.service.IBizBannerService;

/**
 * 控制器
 *
 * @author fengshuonan
 * @Date 2018-01-24 11:17:26
 */
@Controller
@RequestMapping("/bizBanner")
public class BizBannerController extends BaseController {

    private String PREFIX = "/system/bizBanner/";

    @Autowired
    private IBizBannerService bizBannerService;

    /**
     * 跳转到首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "bizBanner.html";
    }

    /**
     * 跳转到添加
     */
    @RequestMapping("/bizBanner_add")
    public String bizBannerAdd() {
        return PREFIX + "bizBanner_add.html";
    }

    /**
     * 跳转到修改
     */
    @RequestMapping("/bizBanner_update/{bizBannerId}")
    public String bizBannerUpdate(@PathVariable Integer bizBannerId, Model model) {
        BizBanner bizBanner = bizBannerService.selectById(bizBannerId);
        model.addAttribute("item",bizBanner);
        LogObjectHolder.me().set(bizBanner);
        return PREFIX + "bizBanner_edit.html";
    }

    /**
     * 获取列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
    	//如果没有条件查询所有
    	if(StringUtils.isEmpty(condition)){
    		return bizBannerService.selectList(null);
    	}
    	
    	//有条件，按条件查询
     	 Wrapper<BizBanner> wrapper = new EntityWrapper<>();
     	
         wrapper = wrapper.like("type", "%" + condition + "%").or().like("title", "%" + condition + "%").or().like("url", "%" + condition + "%");
        return bizBannerService.selectList(wrapper);
    }
    
       
    

    /**
     * 新增
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(BizBanner bizBanner) {
        bizBannerService.insert(bizBanner);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer bizBannerId) {
        bizBannerService.deleteById(bizBannerId);
        return SUCCESS_TIP;
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(BizBanner bizBanner) {
        bizBannerService.updateById(bizBanner);
        return super.SUCCESS_TIP;
    }

    /**
     * 详情
     */
    @RequestMapping(value = "/detail/{bizBannerId}")
    @ResponseBody
    public Object detail(@PathVariable("bizBannerId") Integer bizBannerId) {
        return bizBannerService.selectById(bizBannerId);
    }
}
