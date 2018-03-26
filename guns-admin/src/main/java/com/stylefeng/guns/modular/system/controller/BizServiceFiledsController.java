package com.stylefeng.guns.modular.system.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
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
import com.stylefeng.guns.common.persistence.model.BizAgent;
import com.stylefeng.guns.common.persistence.model.BizServiceCategory;
import com.stylefeng.guns.common.persistence.model.BizServiceFieldValue;
import com.stylefeng.guns.common.persistence.model.BizServiceFileds;
import com.stylefeng.guns.common.persistence.model.Dict;
import com.stylefeng.guns.modular.system.dao.BizServiceDao;
import com.stylefeng.guns.modular.system.dao.BizServiceFieldDao;
import com.stylefeng.guns.modular.system.service.IBizAgentService;
import com.stylefeng.guns.modular.system.service.IBizServiceCategoryService;
import com.stylefeng.guns.modular.system.service.IBizServiceFieldValueService;
import com.stylefeng.guns.modular.system.service.IBizServiceFiledsService;
import com.stylefeng.guns.modular.system.warpper.BizPassportWarpper;
import com.stylefeng.guns.modular.system.warpper.BizServiceFiledsWarpper;
import com.stylefeng.guns.modular.system.warpper.BizServiceWarpper;

/**
 * 控制器
 *
 * @author fengshuonan
 * @Date 2018-01-22 10:46:29
 */
@Controller
@RequestMapping("/bizServiceFileds")
public class BizServiceFiledsController extends BaseController {

    private String PREFIX = "/system/bizServiceFileds/";

    @Autowired
    private IBizServiceFiledsService bizServiceFiledsService;
    
    @Resource
    BizServiceFieldDao bizServiceFiledsDao;
    
    @Autowired
    private IBizServiceFieldValueService bizServiceFieldValueService;
    
    @Autowired
    private IBizServiceCategoryService bizServiceCategoryService;
    
    @Autowired
    private IBizAgentService bizAgentService;

    /**
     * 跳转到首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "bizServiceFileds.html";
    }

    /**
     * 跳转到添加
     */
    @RequestMapping("/bizServiceFileds_add")
    public String bizServiceFiledsAdd() {
        return PREFIX + "bizServiceFileds_add.html";
    }

    /**
     * 跳转到修改
     */
    @RequestMapping("/bizServiceFileds_update/{bizServiceFiledsId}")
    public String bizServiceFiledsUpdate(@PathVariable Integer bizServiceFiledsId, Model model) {
        BizServiceFileds bizServiceFileds = bizServiceFiledsService.selectById(bizServiceFiledsId);            
        //对象转map
        Map<String, Object> fileds = BeanKit.beanToMap(bizServiceFileds);
        //获得分类名称
        if (bizServiceFileds.getCategoryId()!=null) {
        	BizServiceCategory categotyName = bizServiceCategoryService.selectById(bizServiceFileds.getCategoryId());
        	fileds.put("categotyName", categotyName.getCategoryName());
		}
       //判断是否有值
        List<BizServiceFieldValue> value = bizServiceFieldValueService.selectList(new EntityWrapper<BizServiceFieldValue>().eq("filed_id", bizServiceFileds.getFiledId()));    
        List<BizServiceFieldValue> list = new ArrayList<BizServiceFieldValue>();        
        if(value.size()>0){
        String str = value.get(0).getValue();
	        if (str!=null&&!"".equals(str.trim())) {
	        	String[] split = str.split(";");
	        	for (int i = 0; i < split.length; i++) {
	        		BizServiceFieldValue map = new BizServiceFieldValue();
	        		map.setValue(split[i]);
	        		list.add(map);
				}	        	
			}
        }
        //获得代理商名称
        if (bizServiceFileds.getAgentId()!=null&&bizServiceFileds.getAgentId()!=0) {
       	 BizAgent agent = bizAgentService.selectById(bizServiceFileds.getAgentId());
       	fileds.put("agentId", agent.getName());
		}  
        model.addAttribute("subDicts",list);
        model.addAttribute("item",fileds);            
        LogObjectHolder.me().set(bizServiceFileds);
        return PREFIX + "bizServiceFileds_edit.html";
    }

    /**
     * 获取列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
    	 List<Map<String, Object>> list = this.bizServiceFiledsDao.list(condition);
    	 return super.warpObject(new BizServiceFiledsWarpper(list));
    }

    /**
     * 新增
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(BizServiceFileds bizServiceFileds ,String dictValues) {
    	if (ToolUtil.isOneEmpty(bizServiceFileds)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        bizServiceFiledsService.createServiceFiled(bizServiceFileds,dictValues);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer bizServiceFiledsId) {
        bizServiceFiledsService.deleteById(bizServiceFiledsId);
        return SUCCESS_TIP;
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(BizServiceFileds bizServiceFileds ,String dictValues) {
    	if (ToolUtil.isOneEmpty(bizServiceFileds)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        bizServiceFiledsService.updateField(bizServiceFileds ,dictValues);
        return super.SUCCESS_TIP;
    }

    /**
     * 详情
     */
    @RequestMapping(value = "/detail/{bizServiceFiledsId}")
    @ResponseBody
    public Object detail(@PathVariable("bizServiceFiledsId") Integer bizServiceFiledsId) {
        return bizServiceFiledsService.selectById(bizServiceFiledsId);
    }
}
