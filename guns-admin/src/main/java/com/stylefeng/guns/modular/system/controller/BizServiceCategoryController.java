package com.stylefeng.guns.modular.system.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stylefeng.guns.common.annotion.Permission;
import com.stylefeng.guns.common.constant.Const;
import com.stylefeng.guns.common.persistence.model.BizAgent;
import com.stylefeng.guns.common.persistence.model.BizServiceCategory;
import com.stylefeng.guns.common.persistence.model.BizServiceFileds;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.core.node.ZTreeNode;
import com.stylefeng.guns.core.support.BeanKit;
import com.stylefeng.guns.modular.system.dao.BizServiceCategoryDao;
import com.stylefeng.guns.modular.system.service.IBizAgentService;
import com.stylefeng.guns.modular.system.service.IBizServiceCategoryService;
import com.stylefeng.guns.modular.system.service.IBizServiceFiledsService;
import com.stylefeng.guns.modular.system.warpper.BizServiceCategoryDaoWarpper;

/**
 * 服务分类控制器
 *
 * @author dengshuang
 * @Date 2018-01-19 15:35:07
 */
@Controller
@RequestMapping("/bizServiceCategory")
public class BizServiceCategoryController extends BaseController {

    private String PREFIX = "/system/bizServiceCategory/";

    @Autowired
    private IBizServiceCategoryService bizServiceCategoryService;
    
    @Autowired
    private IBizServiceFiledsService bizServiceFiledsService;
    
    @Resource
    BizServiceCategoryDao bzServiceCategoryDao;  
    
    @Autowired
    private IBizAgentService bizAgentService;
    
    /**
     * 跳转到首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "bizServiceCategory.html";
    }

    /**
     * 跳转到添加
     */
    @RequestMapping("/bizServiceCategory_add")
    public String bizServiceCategoryAdd() {
        return PREFIX + "bizServiceCategory_add.html";
    }
    
    /**
     * 跳转到分类字段
     */
   /* @RequestMapping("/bizServiceCategory_filed/{bizServiceCategoryId}")
    public String bizServiceCategoryFiled(@PathVariable Integer bizServiceCategoryId, Model model) {     	
    	BizServiceFileds filed = bizServiceFiledsService.selectById(bizServiceCategoryId);
    	if (filed!=null) {   		 
    		model.addAttribute("item",filed);
    		return PREFIX + "bizServiceFileds_edit.html";    		
		}else {
			return PREFIX + "bizServiceFileds_add.html";
		}           	
    }*/

    /**
     * 跳转到修改
     */
    @RequestMapping("/bizServiceCategory_update/{bizServiceCategoryId}")
    public String bizServiceCategoryUpdate(@PathVariable Integer bizServiceCategoryId, Model model) {
        BizServiceCategory bizServiceCategory = bizServiceCategoryService.selectById(bizServiceCategoryId);
        Map<String, Object> bizServiceCategoryMap = BeanKit.beanToMap(bizServiceCategory);
        //判断是否是顶级分类
        if (bizServiceCategory.getParentCategoryId()==0||bizServiceCategory.getParentCategoryId()==null||bizServiceCategory.getParentCategoryId().equals("")) {
        	bizServiceCategoryMap.put("parentCategoryId", 0);
		}else{
			BizServiceCategory parent = bizServiceCategoryService.selectById(bizServiceCategory.getParentCategoryId());
			bizServiceCategoryMap.put("parentCategoryId", parent.getCategoryName());
		}
        //判断代理上的名称
        if (bizServiceCategory.getAgentId()!=null&&bizServiceCategory.getAgentId()!=0) {
        	 BizAgent agentId = bizAgentService.selectById(bizServiceCategory.getAgentId());
        	 bizServiceCategoryMap.put("agentId", agentId.getName());
		}
       
        model.addAttribute("item",bizServiceCategoryMap);
        LogObjectHolder.me().set(bizServiceCategory);
        return PREFIX + "bizServiceCategory_edit.html";
    }

    /**
     * 获取列表
     */
    @RequestMapping(value = "/list")
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public Object list(String condition) {
    	List<Map<String, Object>> list = this.bzServiceCategoryDao.list(condition);
    	for (Map<String, Object> map : list) {
    		String  details = map.get("details")+"";
    		map.put("details", details.length()<10?details:details.indexOf(10));
		}
        return super.warpObject(new BizServiceCategoryDaoWarpper(list));
    }

    /**
     * 新增
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(BizServiceCategory bizServiceCategory) {
        bizServiceCategoryService.insert(bizServiceCategory);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer bizServiceCategoryId) {
        bizServiceCategoryService.deleteById(bizServiceCategoryId);
        return SUCCESS_TIP;
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(BizServiceCategory bizServiceCategory) {
        bizServiceCategoryService.updateById(bizServiceCategory);
        return super.SUCCESS_TIP;
    }

    /**
     * 详情
     */
    @RequestMapping(value = "/detail/{bizServiceCategoryId}")
    @ResponseBody
    public Object detail(@PathVariable("bizServiceCategoryId") Integer bizServiceCategoryId) {
        return bizServiceCategoryService.selectById(bizServiceCategoryId);
    }
    
    /**
	 * @author denghsuang
     * 获取父级分类
     */
    @RequestMapping(value = "/selectServiceCateTreeList")
    @ResponseBody
    public List<ZTreeNode> selectMenuTreeList() {
        List<ZTreeNode> serviceCateTreeList = this.bzServiceCategoryDao.ServiceCateTreeList();
        serviceCateTreeList.add(ZTreeNode.createParent());
        return serviceCateTreeList;
    }
}
