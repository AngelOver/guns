package com.stylefeng.guns.modular.system.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.stylefeng.guns.common.persistence.model.BizOrders;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.modular.system.service.IBizOrdersService;

/**
 * 控制器
 *
 * @author fengshuonan
 * @Date 2018-01-25 14:25:13
 */
@Controller
@RequestMapping("/bizOrders")
public class BizOrdersController extends BaseController   {

    private String PREFIX = "/system/bizOrders/";

    @Autowired
    private IBizOrdersService bizOrdersService;
    
    @InitBinder
    protected void init(HttpServletRequest request, ServletRequestDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    /**
     * 跳转到首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "bizOrders.html";
    }

    /**
     * 跳转到添加
     */
    @RequestMapping("/bizOrders_add")
    public String bizOrdersAdd() {
        return PREFIX + "bizOrders_add.html";
    }
    

    /**
     * 跳转到修改
     */
    @RequestMapping("/bizOrders_update/{bizOrdersId}")
    public String bizOrdersUpdate(@PathVariable Integer bizOrdersId, Model model) {
        BizOrders bizOrders = bizOrdersService.selectById(bizOrdersId);
        model.addAttribute("item",bizOrders);
        LogObjectHolder.me().set(bizOrders);
        return PREFIX + "bizOrders_edit.html";
    }

    /**
     * 获取列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
    	//如果没有条件查询所有
    	if(StringUtils.isEmpty(condition)){
    		return bizOrdersService.selectList(null);
    	}
    	
    	//有条件，按条件查询
     	 Wrapper<BizOrders> wrapper = new EntityWrapper<>();
         wrapper = wrapper.like("order_number", "%" + condition + "%")
        		 .or().like("username", "%" + condition + "%")
        		 .or().like("province", "%" + condition + "%")
        		 .or().like("city", "%" + condition + "%")
        		 .or().like("county", "%" + condition + "%")
        		 .or().like("address", "%" + condition + "%")
        		 .or().like("telphone", "%" + condition + "%")
        		 .or().like("email", "%" + condition + "%")
        		 .or().like("certificate_num", "%" + condition + "%")
        		 .or().like("subject", "%" + condition + "%")
        		 .or().like("mobile", "%" + condition + "%");
        return bizOrdersService.selectList(wrapper);
        
    }

    /**
     * 新增
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(BizOrders bizOrders) {
        bizOrdersService.insert(bizOrders);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer bizOrdersId) {
        bizOrdersService.deleteById(bizOrdersId);
        return SUCCESS_TIP;
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(BizOrders bizOrders) {
        bizOrdersService.updateById(bizOrders);
        return super.SUCCESS_TIP;
    }

    /**
     * 详情
     */
    @RequestMapping(value = "/detail/{bizOrdersId}")
    @ResponseBody
    public Object detail(@PathVariable("bizOrdersId") Integer bizOrdersId) {
        return bizOrdersService.selectById(bizOrdersId);
    }
}
