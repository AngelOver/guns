package com.stylefeng.guns.modular.noauth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.stylefeng.guns.common.persistence.model.BizRegions;
import com.stylefeng.guns.core.node.ZTreeNode;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.auth.util.Util;
import com.stylefeng.guns.modular.system.service.IBizRegionsService;



/**
*
*需求发布公共接口:服务地区选择
* @author dengshuang
*
*/
@RestController
@RequestMapping("/noauth")
public class DemandController {
	
	
	@Autowired
	private IBizRegionsService bizRegionsService;
	
	/**
	   * 服务商首页的顶级分类
	   * data:2018年2月6日 14:28:09
	   * 分类名称  id
	   */
	  @RequestMapping(value = "/regionsList")
	  @ResponseBody
	  public Object regionsList(HttpServletRequest request, HttpServletResponse response) {
		  response.setHeader("Access-Control-Allow-Origin", "*");
		  Map<String,Object> result = new HashMap<String, Object>();	
		  String id = request.getParameter("id");
		  String name = request.getParameter("name");
		  String parent_id = request.getParameter("parent_id");
		  String level =request.getParameter("level");
		  return response;
	  
	  }
	  
   /**
	 * 获得省列表
	 * data:2018年3月22日 20:30:07
	 * 
	 */
	  @RequestMapping(value = "/provinceList")
	  @ResponseBody
	  public Object Province(HttpServletRequest request, HttpServletResponse response) {
		  response.setHeader("Access-Control-Allow-Origin", "*");
		  Map<String,Object> result = new HashMap<String, Object>();			 
   	      Wrapper<BizRegions> wapper = new  EntityWrapper<BizRegions>();   	      
   	      wapper.eq("level", 1);
		  List<BizRegions> provinceList = bizRegionsService.selectList(wapper);
		  result.put("datas", provinceList);
		  result.put(Util.RESULT, Util.RESULT_RC_SUCCESS);
		  result.put(Util.MESSAGE, "操作成功");
		  return result;
	  }
	  
   /**
    * 获取地址的tree列表
    */
	  @RequestMapping(value = "/cityList")
	  @ResponseBody
	  public Object City(HttpServletRequest request, HttpServletResponse response) {
		  response.setHeader("Access-Control-Allow-Origin", "*");
		  Map<String,Object> result = new HashMap<String, Object>();
		  String id = request.getParameter("id");
		  if (ToolUtil.isOneEmpty(id)) {
				 result.put(Util.RESULT, Util.RESULT_RC_CHECK_FAIL);
				 result.put(Util.MESSAGE, "参数不对");
			 }else{		
				  Wrapper<BizRegions> wapper = new  EntityWrapper<BizRegions>();   	      		   	      
		   	      wapper.eq("parent_id", id);
				  List<BizRegions> cityList = bizRegionsService.selectList(wapper);
				  result.put("datas", cityList);
				  result.put(Util.RESULT, Util.RESULT_RC_SUCCESS);
				  result.put(Util.MESSAGE, "操作成功");				 
			 }   
		  return result;
	  }
   /**
    * 获取地址的tree列表
    */
	  @RequestMapping(value = "/areaList")
	  @ResponseBody
	  public List<ZTreeNode> Area(HttpServletRequest request) {
		  String id = request.getParameter("id"); 
		  List<ZTreeNode> tree = new ArrayList<ZTreeNode>();
		  Wrapper<BizRegions> wapper = new  EntityWrapper<BizRegions>();
		  wapper.eq("level", 3);
		  wapper.eq("parent_id", id);
		  List<BizRegions> agentList = bizRegionsService.selectList(wapper);
		  for (BizRegions BizRegions : agentList) {
       	//代理商对象转tree
			  ZTreeNode ztree = new ZTreeNode();
			  ztree.setIsOpen(false);
			  ztree.setId((long)BizRegions.getId());
			  ztree.setpId(1L);
//           if(BizRegions.getParentId()==110100){
//           ztree.setOpen(false);
//           ztree.setName(BizRegions.getName());
//           tree.add(ztree);
//           }
			  ztree.setOpen(false);
			  ztree.setName(BizRegions.getName());
			  tree.add(ztree);
			  
		  }       
		  tree.add(ZTreeNode.createParent());
		  return tree;
 	 }
}