package com.stylefeng.guns.modular.system.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.stylefeng.guns.core.base.controller.BaseController;

import org.apache.http.HttpRequest;
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

import com.stylefeng.guns.common.persistence.model.BizRegions;
import com.stylefeng.guns.common.persistence.model.BizServiceFileds;
import com.stylefeng.guns.modular.system.dao.BizRegionsDao;
import com.stylefeng.guns.modular.system.service.IBizRegionsService;

/**
 * 地址管理控制器
 *
 * @author fengshuonan
 * @Date 2018-03-13 16:38:38
 */
@Controller
@RequestMapping("/bizRegions")
public class BizRegionsController extends BaseController {

    private String PREFIX = "/system/bizRegions/";

    @Autowired
    private IBizRegionsService bizRegionsService;

    @Resource
    BizRegionsDao bizRegionsDao;



    /**
     * 跳转到地址管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "bizRegions.html";
    }

    /**
     * 跳转到添加地址管理
     */
    @RequestMapping("/bizRegions_add")
    public String bizRegionsAdd() {
        return PREFIX + "bizRegions_add.html";
    }

    /**
     * 跳转到修改地址管理
     */
    @RequestMapping("/bizRegions_update/{bizRegionsId}")
    public String bizRegionsUpdate(@PathVariable Integer bizRegionsId, Model model) {
        BizRegions bizRegions = bizRegionsService.selectById(bizRegionsId);
        model.addAttribute("item",bizRegions);
        LogObjectHolder.me().set(bizRegions);
        return PREFIX + "bizRegions_edit.html";
    }

    /**
     * 获取地址管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return bizRegionsService.selectList(null);
    }

    /**
     * 新增地址管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(BizRegions bizRegions) {
        bizRegionsService.insert(bizRegions);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除地址管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer bizRegionsId) {
        bizRegionsService.deleteById(bizRegionsId);
        return SUCCESS_TIP;
    }

    /**
     * 修改地址管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(BizRegions bizRegions) {
        bizRegionsService.updateById(bizRegions);
        return super.SUCCESS_TIP;
    }

    /**
     * 地址管理详情
     */
    @RequestMapping(value = "/detail/{bizRegionsId}")
    @ResponseBody
    public Object detail(@PathVariable("bizRegionsId") Integer bizRegionsId) {
        return bizRegionsService.selectById(bizRegionsId);
    }
    /**
     * 获取角色列表
     */
    @RequestMapping(value = "/bizRegionsTreeList")
    @ResponseBody
    public List<ZTreeNode> bizRegionsTreeList() {
        List<ZTreeNode> bizRegionsTreeList = this.bizRegionsDao.RegionsTreeList();
        bizRegionsTreeList.add(ZTreeNode.createParent());
        return bizRegionsTreeList;
    }
//    @RequestMapping(value = "/roleTreeList")
//    @ResponseBody
//    public List<ZTreeNode> roleTreeList() {
//        List<ZTreeNode> roleTreeList = this.roleDao.roleTreeList();
//        roleTreeList.add(ZTreeNode.createParent());
//        return roleTreeList;
//    }

//    /**
//     * 获取地址的tree列表
//     */
//    @RequestMapping(value = "/province")
//    @ResponseBody
//    public List<ZTreeNode> Province() {
//        List<ZTreeNode> tree = new ArrayList<ZTreeNode>();
////        Wrapper<BizRegions> wapper = new  EntityWrapper<BizRegions>();
////        wapper.eq("level", 1);
//        List<BizRegions> agentList = bizRegionsService.selectList(null);
//        for (BizRegions BizRegions : agentList) {
//        	//代理商对象转tree
//            ZTreeNode ztree = new ZTreeNode();
//            ztree.setIsOpen(false);
//            ztree.setId((long)BizRegions.getId());
////            ztree.setpId(0L);
//            /*if(BizRegions.getLevel()==1){
//                ztree.setOpen(false);
//                ztree.setName(BizRegions.getName());
//                tree.add(ztree);
//                }*/
//            ztree.setOpen(true);
//            ztree.setName1(BizRegions.setLevel(BizRegions.getName()));
//            tree.add(ztree);
//		}       
//        tree.add(ZTreeNode.createParent());
//        return tree;
//    }
//    /**
//     * 获取地址的tree列表
//     */
//    @RequestMapping(value = "/city")
//    @ResponseBody
//    public List<ZTreeNode> City(HttpServletRequest request) {
//    	/*BizRegions bizRegions = bizRegionsService.selectById(province); */           
//        //对象转map
////        Map<String, Object> fileds = BeanKit.beanToMap(bizServiceFileds);
//    	String id = request.getParameter("id");    	
//        List<ZTreeNode> tree = new ArrayList<ZTreeNode>();
//        Wrapper<BizRegions> wapper = new  EntityWrapper<BizRegions>();
//        wapper.eq("level",2);
//        wapper.eq("parent_id", id);
//        List<BizRegions> regions = bizRegionsService.selectList(wapper);       
//        for (BizRegions BizRegions : regions) {
//        	//代理商对象转tree
//            ZTreeNode ztree = new ZTreeNode();
//            ztree.setIsOpen(false);
//            ztree.setId((long)BizRegions.getId());
//            ztree.setpId(1L);
////            if(BizRegions.getParentId()==11000){
////                ztree.setOpen(false);
////                ztree.setName(BizRegions.getName());
////                tree.add(ztree);
////                }
//            ztree.setOpen(false);
//            ztree.setName(BizRegions.getName());
//            tree.add(ztree);
//		}       
//        tree.add(ZTreeNode.createParent());
//        return tree;
//    }
//    /**
//     * 获取地址的tree列表
//     */
//    @RequestMapping(value = "/area")
//    @ResponseBody
//    public List<ZTreeNode> Area(HttpServletRequest request) {
//    	String id = request.getParameter("id"); 
//        List<ZTreeNode> tree = new ArrayList<ZTreeNode>();
//        Wrapper<BizRegions> wapper = new  EntityWrapper<BizRegions>();
//        wapper.eq("level", 3);
//        wapper.eq("parent_id", id);
//        List<BizRegions> agentList = bizRegionsService.selectList(wapper);
//        for (BizRegions BizRegions : agentList) {
//        	//代理商对象转tree
//            ZTreeNode ztree = new ZTreeNode();
//            ztree.setIsOpen(false);
//            ztree.setId((long)BizRegions.getId());
//            ztree.setpId(1L);
////            if(BizRegions.getParentId()==110100){
////            ztree.setOpen(false);
////            ztree.setName(BizRegions.getName());
////            tree.add(ztree);
////            }
//            ztree.setOpen(false);
//            ztree.setName(BizRegions.getName());
//            tree.add(ztree);
//            
//		}       
//        tree.add(ZTreeNode.createParent());
//        return tree;
//    }
}
