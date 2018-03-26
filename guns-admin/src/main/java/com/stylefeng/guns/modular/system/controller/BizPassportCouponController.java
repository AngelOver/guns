package com.stylefeng.guns.modular.system.controller;

import java.util.ArrayList;
import java.util.Iterator;
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
import com.stylefeng.guns.core.node.ZTreeNode;
import com.stylefeng.guns.core.support.BeanKit;

import org.springframework.web.bind.annotation.RequestParam;

import com.stylefeng.guns.common.persistence.model.BizAgent;
import com.stylefeng.guns.common.persistence.model.BizCoupons;
import com.stylefeng.guns.common.persistence.model.BizPassport;
import com.stylefeng.guns.common.persistence.model.BizPassportCoupon;
import com.stylefeng.guns.modular.system.dao.BizPassportCouponsDao;
import com.stylefeng.guns.modular.system.service.IBizAgentService;
import com.stylefeng.guns.modular.system.service.IBizCouponsService;
import com.stylefeng.guns.modular.system.service.IBizPassportCouponService;
import com.stylefeng.guns.modular.system.service.IBizPassportService;
import com.stylefeng.guns.modular.system.warpper.BizPassportCouponWarpper;


/**
 * 控制器
 *
 * @author fengshuonan
 * @Date 2018-01-30 09:20:10
 */
@Controller
@RequestMapping("/bizPassportCoupon")
public class BizPassportCouponController extends BaseController {

    private String PREFIX = "/system/bizPassportCoupon/";

    @Autowired
    private IBizPassportCouponService bizPassportCouponService;
    
    @Resource
    BizPassportCouponsDao bizPassportCouponsDao;
    
    @Autowired
    private IBizCouponsService bizCouponsService;
    
    @Autowired
    private IBizPassportService bizPassportService;
    
    @Autowired
    private IBizAgentService bizAgentService;
    
    /**
     * 跳转到首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "bizPassportCoupon.html";
    }

    /**
     * 跳转到添加
     */
    @RequestMapping("/bizPassportCoupon_add")
    public String bizPassportCouponAdd() {
        return PREFIX + "bizPassportCoupon_add.html";
    }

    /**
     * 跳转到修改
     */
    @RequestMapping("/bizPassportCoupon_update/{bizPassportCouponId}")
    public String bizPassportCouponUpdate(@PathVariable Integer bizPassportCouponId, Model model) {
        BizPassportCoupon bizPassportCoupon = bizPassportCouponService.selectById(bizPassportCouponId);
        Map<String, Object> item = BeanKit.beanToMap(bizPassportCoupon);
        Integer couponId = bizPassportCoupon.getCouponId();
        Integer couponUserId = bizPassportCoupon.getCouponUserId();
        //获得优惠卷名称
        if (couponId!=null&&couponId!=0) {       	
        	BizCoupons coupon = bizCouponsService.selectById(couponId);
        	item.put("couponId", coupon.getCouponName());
		}
        //获得会员名称
        if(couponUserId!=null&&couponUserId!=0){
        	BizPassport member = bizPassportService.selectById(couponUserId);
        	item.put("passportId", member.getNickname());
        }
        model.addAttribute("item",item);
        LogObjectHolder.me().set(bizPassportCoupon);
        return PREFIX + "bizPassportCoupon_edit.html";
    }

    /**
     * 获取列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
    	List<Map<String, Object>> list = bizPassportCouponsDao.list(condition);
        return super.warpObject(new BizPassportCouponWarpper(list));
    }

    /**
     * 新增
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(BizPassportCoupon bizPassportCoupon) {
        bizPassportCouponService.insert(bizPassportCoupon);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer bizPassportCouponId) {
        bizPassportCouponService.deleteById(bizPassportCouponId);
        return SUCCESS_TIP;
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(BizPassportCoupon bizPassportCoupon) {
        bizPassportCouponService.updateById(bizPassportCoupon);
        return super.SUCCESS_TIP;
    }

    /**
     * 详情
     */
    @RequestMapping(value = "/detail/{bizPassportCouponId}")
    @ResponseBody
    public Object detail(@PathVariable("bizPassportCouponId") Integer bizPassportCouponId) {
        return bizPassportCouponService.selectById(bizPassportCouponId);
    }
    
    /**
     * 获取代理商的tree列表
     */
    @RequestMapping(value = "/tree")
    @ResponseBody
    public List<ZTreeNode> tree() {
        List<ZTreeNode> tree = new ArrayList<ZTreeNode>();
        List<BizAgent> agentList = bizAgentService.selectList(null);
        for (BizAgent bizAgent : agentList) {
        	//代理商对象转tree
            ZTreeNode ztree = new ZTreeNode();
            ztree.setIsOpen(false);
            ztree.setId((long)bizAgent.getAgentId());
            ztree.setpId(0L);
            ztree.setOpen(false);
            ztree.setName(bizAgent.getName());
            tree.add(ztree);
		}       
        tree.add(ZTreeNode.createParent());
        return tree;
    }
}
