package com.stylefeng.guns.modular.system.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.exception.GunsException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;

import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.core.support.BeanKit;
import com.stylefeng.guns.core.util.ToolUtil;

import org.springframework.web.bind.annotation.RequestParam;

import com.stylefeng.guns.common.exception.BizExceptionEnum;
import com.stylefeng.guns.common.persistence.model.BizService;
import com.stylefeng.guns.common.persistence.model.BizServiceCategory;
import com.stylefeng.guns.modular.system.dao.BizServiceDao;
import com.stylefeng.guns.modular.system.service.IBizServiceCategoryService;
import com.stylefeng.guns.modular.system.service.IBizServiceService;
import com.stylefeng.guns.modular.system.warpper.BizServiceWarpper;

/**
 * 控制器
 *
 * @author fengshuonan
 * @Date 2018-01-20 15:43:11
 */
@Controller
@RequestMapping("/bizService")
public class BizServiceController extends BaseController {

    private String PREFIX = "/system/bizService/";

    @Autowired
    private IBizServiceService bizServiceService;
    
    @Autowired
    private IBizServiceCategoryService bizServiceCategoryService;
    
    @Resource
    BizServiceDao bizServiceDao;

    /**
     * 跳转到首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "bizService.html";
    }

    /**
     * 跳转到添加
     */
    @RequestMapping("/bizService_add")
    public String bizServiceAdd() {
        return PREFIX + "bizService_add.html";
    }

    /**
     * 跳转到修改
     */
    @RequestMapping("/bizService_update/{bizServiceId}")
    public String bizServiceUpdate(@PathVariable Integer bizServiceId, Model model) {
        BizService bizService = bizServiceService.selectById(bizServiceId);
        Map<String, Object> bizMap = BeanKit.beanToMap(bizService);
        if (bizService!=null&&bizService.getCategoryId()!=null) {
        	//获得服务分类名称
        	BizServiceCategory serviceCate = bizServiceCategoryService.selectById(bizService.getCategoryId());
        	bizMap.put("parentName", serviceCate.getCategoryName());
		}
        model.addAttribute("item",bizMap);
        LogObjectHolder.me().set(bizService);
        return PREFIX + "bizService_edit.html";
    }

    /**
     * 获取列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {    
    	List<Map<String, Object>> list = this.bizServiceDao.list(condition);
        return super.warpObject(new BizServiceWarpper(list));
    }

    /**
     * 新增
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(BizService bizService) {
        bizServiceService.insert(bizService);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer bizServiceId) {
    	if (ToolUtil.isOneEmpty(bizServiceId)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }    	
    	BizService bizService = bizServiceService.selectById(bizServiceId);
    	bizService.setStatus(2);
    	bizServiceService.updateById(bizService);
        return SUCCESS_TIP;
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(BizService bizService) {
        bizServiceService.updateById(bizService);
        return super.SUCCESS_TIP;
    }

    /**
     * 详情
     */
    @RequestMapping(value = "/detail/{bizServiceId}")
    @ResponseBody
    public Object detail(@PathVariable("bizServiceId") Integer bizServiceId) {
        return bizServiceService.selectById(bizServiceId);
    }
}
