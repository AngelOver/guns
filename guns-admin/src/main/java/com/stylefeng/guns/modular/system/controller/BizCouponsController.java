package com.stylefeng.guns.modular.system.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.stylefeng.guns.core.base.controller.BaseController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;

import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.core.support.BeanKit;

import org.springframework.web.bind.annotation.RequestParam;

import com.stylefeng.guns.common.persistence.model.BizAgent;
import com.stylefeng.guns.common.persistence.model.BizCoupons;
import com.stylefeng.guns.common.persistence.model.Setup;
import com.stylefeng.guns.modular.system.dao.BizCouponsDao;
import com.stylefeng.guns.modular.system.service.IBizAgentService;
import com.stylefeng.guns.modular.system.service.IBizCouponsService;
import com.stylefeng.guns.modular.system.service.IBizPassportService;
import com.stylefeng.guns.modular.system.warpper.BizCouponsWarpper;
import com.stylefeng.guns.modular.system.warpper.BizServiceFiledsWarpper;

/**
 * 控制器
 *
 * @author fengshuonan
 * @Date 2018-01-29 14:12:49
 */
@Controller
@RequestMapping("/bizCoupons")
public class BizCouponsController extends BaseController {

    private String PREFIX = "/system/bizCoupons/";

    @Autowired
    private IBizCouponsService bizCouponsService;
    
    @Autowired
    private IBizAgentService bizAgentService;
    
    @Autowired
    private IBizPassportService bizPassportService;
    
    @Resource
    BizCouponsDao bizCouponsDao;

    /**
     * 跳转到首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "bizCoupons.html";
    }

    /**
     * 跳转到添加
     */
    @RequestMapping("/bizCoupons_add")
    public String bizCouponsAdd() {
        return PREFIX + "bizCoupons_add.html";
    }

    /**
     * 跳转到修改
     */
    @RequestMapping("/bizCoupons_update/{bizCouponsId}")
    public String bizCouponsUpdate(@PathVariable Integer bizCouponsId, Model model) {
        BizCoupons bizCoupons = bizCouponsService.selectById(bizCouponsId);
        Map<String, Object> item = BeanKit.beanToMap(bizCoupons);  
        //获得优惠卷的开始时间 结束时间
        String expires = bizCoupons.getExpires();
        if (expires!=null&&!"".equals(expires.trim())) {
        	String[] split = expires.split("~");
        	item.put("begin_time", split[0]);
        	item.put("end_time", split[1]);
		}        
        //获得代理商名称
        if (bizCoupons.getAgentId()!=null&&bizCoupons.getAgentId()!=0) {
        	 BizAgent agent = bizAgentService.selectById(bizCoupons.getAgentId());
        	 item.put("agentId", agent.getName());
		}      
        model.addAttribute("item",item);
        LogObjectHolder.me().set(bizCoupons);
        return PREFIX + "bizCoupons_edit.html";
    }

    /**
     * 获取列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
    	List<Map<String, Object>> list = bizCouponsDao.list(condition);    	
    	 return super.warpObject(new BizCouponsWarpper(list));
    }

    /**
     * 新增
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(BizCoupons bizCoupons ,String begin_time ,String end_time) {    	
    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");    	
    	try {
    		Date bt=sdf.parse(begin_time); 
			Date et=sdf.parse(end_time);
			//开始时间小于结束时间
			if (bt.before(et)) {
				if (bizCoupons.getMin().compareTo(bizCoupons.getCost())==1) {
					bizCoupons.setCreatedAt(new Date());
					bizCoupons.setExpires(begin_time+"~"+end_time);
			        bizCouponsService.insert(bizCoupons);
			        return super.SUCCESS_TIP;
				}else{
					return "2222";
				}								
			}else{
				return "0000";
			}
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "1111";
		}    	       
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer bizCouponsId) {
        bizCouponsService.deleteById(bizCouponsId);
        return SUCCESS_TIP;
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(BizCoupons bizCoupons ,String begin_time ,String end_time) {     
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");    	
    	try {
    		Date bt=sdf.parse(begin_time); 
			Date et=sdf.parse(end_time);
			//开始时间小于结束时间
			if (bt.before(et)) {
				if (bizCoupons.getMin().compareTo(bizCoupons.getCost())==1) {
					bizCoupons.setCreatedAt(new Date());
					bizCoupons.setExpires(begin_time+"~"+end_time);
			        bizCouponsService.updateById(bizCoupons);
			        return super.SUCCESS_TIP;
				}else{
					return "2222";
				}								
			}else{
				return "0000";
			}
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "1111";
		}    	       
    }

    /**
     * 详情
     */
    @RequestMapping(value = "/detail/{bizCouponsId}")
    @ResponseBody
    public Object detail(@PathVariable("bizCouponsId") Integer bizCouponsId) {
        return bizCouponsService.selectById(bizCouponsId);
    }
}
