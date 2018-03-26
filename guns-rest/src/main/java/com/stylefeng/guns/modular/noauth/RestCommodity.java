package com.stylefeng.guns.modular.noauth;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.stylefeng.guns.common.persistence.model.BizService;
import com.stylefeng.guns.common.persistence.model.BizServiceCategory;
import com.stylefeng.guns.common.persistence.model.BizServiceImg;
import com.stylefeng.guns.common.persistence.model.BizSpecificationValue;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.auth.util.Util;
import com.stylefeng.guns.modular.system.dao.BizServiceCategoryDao;
import com.stylefeng.guns.modular.system.service.IBizServiceCategoryService;
import com.stylefeng.guns.modular.system.service.IBizServiceImgService;
import com.stylefeng.guns.modular.system.service.IBizServiceService;
import com.stylefeng.guns.modular.system.service.IBizSpecificationValueService;

/**
*
* @author dengshuang
* @Date 2018年2月11日 10:06:19
* 前台 商品管理
*/
@RestController
@RequestMapping("/noauth")
public class RestCommodity {
	
	@Autowired
	private IBizServiceService bizServiceService;
	
	@Autowired
	private IBizServiceCategoryService bizServiceCategoryService;
	
	@Autowired
	private IBizServiceImgService bizServiceImgService;
	
	@Autowired
	private IBizSpecificationValueService  bizSpecificationValueService;
	
	@Resource
	BizServiceCategoryDao bizServiceCategoryDao;
	
	/**
	 * 商品管理 商品列表
	 * data:2018年2月8日 09:43:17
	 * 代理商信息
	 */
	 @RequestMapping(value = "/getServiceList")
	 @ResponseBody
	 public Object agentList(HttpServletRequest request, HttpServletResponse response) {	
		 response.setHeader("Access-Control-Allow-Origin", "*"); 
		 Map<String,Object> result = new HashMap<String, Object>();		
		 Map<String,Object> map = new HashMap<String, Object>();
		 Map<String,Object> page = new HashMap<String, Object>();
		 Map<String,Object> temp = new HashMap<String, Object>();
		 String agent_id = request.getParameter("agent_id");
		 String name = request.getParameter("name");
		 //价格区间
		 String privce_start = request.getParameter("privce_start");
		 String privce_end = request.getParameter("privce_end");
		 //时间区间
		 String begin_time = request.getParameter("begin_time");
		 String end_time = request.getParameter("end_time");
		 String verify = request.getParameter("verify");		 
		 String release = request.getParameter("release");
		 String pageNo = request.getParameter("pageNo");//页数		
		 int pageSize = 5; //偏移量	
		 if (ToolUtil.isEmpty(agent_id)) {
			 result.put(Util.RESULT, Util.RESULT_RC_CHECK_FAIL);
			 result.put(Util.MESSAGE, "参数不对");
		 }else{
			 map.put("agent_id", agent_id);
			 if (!ToolUtil.isEmpty(name)) {
				 map.put("name",name);			
			 }
			 if (!ToolUtil.isEmpty(privce_start)) {
				 map.put("privce_start",privce_start);			
			 }
			 if (!ToolUtil.isEmpty(privce_end)) {
				 map.put("privce_end",privce_end);			
			 }
			 if (!ToolUtil.isEmpty(begin_time)) {
				 map.put("begin_time",begin_time);			
			 }
			 if (!ToolUtil.isEmpty(end_time)) {
				 map.put("end_time",end_time);			
			 }
			 if (!ToolUtil.isEmpty(verify)&&!"0".equals(verify)) {
				 map.put("verify",verify);			
			 }
			 if (!ToolUtil.isEmpty(release)&&!"0".equals(release)) {
				 map.put("release",release);			
			 }		
			 	 int totalCount = bizServiceService.getServiceList2(map).size();
			     map.put("pageNo", pageSize*((Integer.parseInt(pageNo))-1));
			     map.put("pageSize", pageSize);		
				 
			 //获得商品列表
			 List<Map<String,Object>> list = bizServiceService.getServiceList(map);
			/* for (Map<String, Object> map2 : list) {
				 map2.put("check", false);
			}*/
			 page.put("pageNo", pageNo);
			 page.put("pages", totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize+1);
			 temp.put("page", page);
			 temp.put("list", list);
			 result.put("datas", temp);
			 result.put(Util.RESULT, Util.RESULT_RC_SUCCESS);
			 result.put(Util.MESSAGE, "操作成功！");
		 }		 		
		 return result;
	 }

	 
   /**
	 * 上架 or 下架商品
	 * data:2018年2月12日 13:34:59
	 * 商品id集合
	 */
	 @RequestMapping(value = "/releaseUpOrDown")
	 @ResponseBody
	 public Object releaseUpOrDown(HttpServletRequest request, HttpServletResponse response) {
		 response.setHeader("Access-Control-Allow-Origin", "*"); 		
		 Map<String,Object> map = new HashMap<String, Object>();
		 String service_list = request.getParameter("service_list");
		 //状态
		 String release = request.getParameter("release");
		 if (ToolUtil.isEmpty(service_list)) {
			 map.put(Util.RESULT, Util.RESULT_RC_CHECK_FAIL);
			 map.put(Util.MESSAGE, "参数不对");
		 }else{
			 String[] split = service_list.split(",");
			 for (int i = 0; i < split.length; i++) {
				 BizService service = bizServiceService.selectById(Integer.parseInt(split[i]));			
				 service.setRelease(Integer.parseInt(release));
				 bizServiceService.updateById(service);				 
			}	
			 map.put(Util.RESULT, Util.RESULT_RC_SUCCESS);
			 map.put(Util.MESSAGE, "操作成功！");
		 }
		 return map;
	 }
	 
   /**
	 * 删除商品
	 * data:2018年2月12日 13:34:48
	 * 商品id集合
	 */
	 @RequestMapping(value = "/deleteService")
	 @ResponseBody
	 public Object deleteService(HttpServletRequest request, HttpServletResponse response) {
		 response.setHeader("Access-Control-Allow-Origin", "*"); 		
		 Map<String,Object> map = new HashMap<String, Object>();
		 String service_list = request.getParameter("service_list");
		 if (ToolUtil.isEmpty(service_list)) {
			 map.put(Util.RESULT, Util.RESULT_RC_CHECK_FAIL);
			 map.put(Util.MESSAGE, "参数不对");
		}else{
			String[] split = service_list.split(",");
			for (int i = 0; i < split.length; i++) {				
				 BizService service = bizServiceService.selectById(Integer.parseInt(split[i]));			
				 service.setStatus(2);
				 bizServiceService.updateById(service);				 			
			}			
			map.put(Util.RESULT, Util.RESULT_RC_SUCCESS);
			map.put(Util.MESSAGE, "操作成功！");
		}
		 return map;
	 }
	 
	 /**
	 * 商品管理基本信息
	 * data:2018年2月12日 15:03:27
	 * agent_id
	 */
	 @RequestMapping(value = "/serviceInfo")
	 @ResponseBody
	 public Object serviceInfo(HttpServletRequest request, HttpServletResponse response) {
		 response.setHeader("Access-Control-Allow-Origin", "*"); 		
		 Map<String,Object> map = new HashMap<String, Object>();
		 String agent_id = request.getParameter("agent_id");
		 if (ToolUtil.isEmpty(agent_id)) {
			 map.put(Util.RESULT, Util.RESULT_RC_CHECK_FAIL);
			 map.put(Util.MESSAGE, "参数不对");
		 }else{
			 //获得商家的基本
			 Map<String,Object> result = bizServiceService.getServiceInfo(agent_id);			 
			 map.put("datas", result);
			 map.put(Util.RESULT, Util.RESULT_RC_SUCCESS);
			 map.put(Util.MESSAGE, "操作成功！");
		 }
		 return map;
	 }	 	 
	 
	/**
	 * 商品管理 商品分类
	 * data:2018年2月13日 09:43:58
	 * agent_id
	 */
	 @RequestMapping(value = "/publish_goods")
	 @ResponseBody
	 public Object publish_goods(HttpServletRequest request, HttpServletResponse response) {
		 response.setHeader("Access-Control-Allow-Origin", "*"); 		
		 Map<String,Object> map = new HashMap<String, Object>();
		 String id = request.getParameter("agent_id");
		 if (ToolUtil.isEmpty(id)) {
			 map.put(Util.RESULT, Util.RESULT_RC_CHECK_FAIL);
			 map.put(Util.MESSAGE, "参数不对");
		 }else{
			 List<Map<String, Object>> list = bizServiceCategoryDao.findTopCategoryList();
			 for (Map<String, Object> map2 : list) {
				 if (map2.get("category_id")!=null&&!"".equals(map2.get("category_id"))) {
					 Wrapper<BizServiceCategory> wrapper_children = new EntityWrapper<>();
					 wrapper_children.eq("parent_category_id", map2.get("category_id").toString());
					 //二级分类
					 List<Map<String, Object>> children = bizServiceCategoryService.selectMaps(wrapper_children);
					 for (Map<String, Object> map3 : children) {
						 if (map3.get("categoryId")!=null&&!"".equals(map3.get("categoryId"))) {
							 Wrapper<BizServiceCategory> wrapper_grandson = new EntityWrapper<>();
							 wrapper_grandson.eq("parent_category_id", map3.get("categoryId").toString());
							 //三级分类
							 List<Map<String, Object>> grandson = bizServiceCategoryService.selectMaps(wrapper_grandson);
							 map3.put("grandson", grandson);
						}						
					}
					 map2.put("children", children);			
				}				 
			}
			 map.put("datas", list);
			 map.put(Util.RESULT, Util.RESULT_RC_SUCCESS);
			 map.put(Util.MESSAGE, "操作成功！");
		 }	
		 return map;
	 }
	 
	 
	/**
	 * 发布商品中的分类选项
	 * data:2018年2月21日 09:19:14
	 * agent_id
	 */
	 @RequestMapping(value = "/serviceCategoryTop")
	 @ResponseBody
	 public Object serviceCategoryTop(HttpServletRequest request, HttpServletResponse response) {
		 response.setHeader("Access-Control-Allow-Origin", "*"); 		
		 Map<String,Object> map = new HashMap<String, Object>();
		 String agent_id = request.getParameter("agent_id");
		 if (ToolUtil.isEmpty(agent_id)) {
			 map.put(Util.RESULT, Util.RESULT_RC_CHECK_FAIL);
			 map.put(Util.MESSAGE, "参数不对");
		 }else{
			 List<Map<String, Object>> list = bizServiceCategoryDao.findTopCategoryList();
			 for (Map<String, Object> map2 : list) {
				 map2.put("choose", false);
				 //判断是否下一级分类
				 Wrapper<BizServiceCategory> wrapper= new EntityWrapper<>();
				 wrapper.eq("parent_category_id", map2.get("category_id"));
				 List<BizServiceCategory> cateList = bizServiceCategoryService.selectList(wrapper);
				 if (cateList.size()>0) {
					 map2.put("more", true);
				}else{
					map2.put("more", false);
				}
			}
			 map.put("datas", list);
			 map.put(Util.RESULT, Util.RESULT_RC_SUCCESS);
			 map.put(Util.MESSAGE, "操作成功！");
		 }
		 return map;
	 }
	 
	/**
	 * 根据父类id获得子分类
	 * data:2018年2月21日 10:09:43
	 * agent_id
	 */
	 @RequestMapping(value = "/sub_category")
	 @ResponseBody
	 public Object sub_category(HttpServletRequest request, HttpServletResponse response) {
		 response.setHeader("Access-Control-Allow-Origin", "*"); 		
		 Map<String,Object> map = new HashMap<String, Object>();
		 Wrapper<BizServiceCategory> wrapper = new EntityWrapper<>();
		 String category_id = request.getParameter("category_id");
		 if (ToolUtil.isEmpty(category_id)) {
			 map.put(Util.RESULT, Util.RESULT_RC_CHECK_FAIL);
			 map.put(Util.MESSAGE, "参数不对");
		 }else{
			 wrapper.eq("parent_category_id", category_id);
			 List<Map<String, Object>> list = bizServiceCategoryService.selectMaps(wrapper);
			 for (Map<String, Object> map2 : list) {
				 map2.put("choose", false);
				 //判断是否下一级分类
				 Wrapper<BizServiceCategory> wrapper2 = new EntityWrapper<>();
				 wrapper2.eq("parent_category_id", map2.get("categoryId"));
				 List<BizServiceCategory> cateList = bizServiceCategoryService.selectList(wrapper2);
				 if (cateList.size()>0) {
					 map2.put("more", true);
				}else{
					map2.put("more", false);
				}
			}
			 map.put("datas", list);
			 map.put(Util.RESULT, Util.RESULT_RC_SUCCESS);
			 map.put(Util.MESSAGE, "操作成功！");
		 }
		 return map;
	 }
	 
	/**
	 * 获取分类下的所有服务
	 * data:2018年3月17日 14:48:13
	 * category_id
	 */
	 @RequestMapping(value = "/category_service")
	 @ResponseBody
	 public Object category_service(HttpServletRequest request, HttpServletResponse response) {
		 response.setHeader("Access-Control-Allow-Origin", "*"); 		
		 Map<String,Object> map = new HashMap<String, Object>();
		 Map<String,Object> result = new HashMap<String, Object>();
		 String category_id = request.getParameter("category_id");
		 String cate_id = request.getParameter("cate_id");
		 if (ToolUtil.isEmpty(category_id)) {
			 map.put(Util.RESULT, Util.RESULT_RC_CHECK_FAIL);
			 map.put(Util.MESSAGE, "参数不对");
		 }else{
			 Wrapper<BizServiceCategory> wrapper = new EntityWrapper<>();
			 wrapper.eq("parent_category_id", category_id);
			 List<BizServiceCategory> selectList = bizServiceCategoryService.selectList(wrapper);
			 if (cate_id==null) {								
				 List<Map<String, Object>> list =  bizServiceService.getServiceByCate_id(selectList.get(0).getCategoryId());
				/* //一级分类下面的	
				 List<Map<String, Object>> list2 = bizServiceService.getServiceByCate_id(Integer.parseInt(category_id));
				 if (list2.size()>0) {
					 list.addAll(list2);
				}*/
				 result.put("list", list);
				 result.put("cate", selectList);
				 map.put("datas", result);
				 map.put(Util.RESULT, Util.RESULT_RC_SUCCESS);
				 map.put(Util.MESSAGE, "操作成功！");
			}else{
				 List<Map<String, Object>> list =  bizServiceService.getServiceByCate_id(Integer.parseInt(cate_id));
				/* //一级分类下面的	
				 List<Map<String, Object>> list2 = bizServiceService.getServiceByCate_id(Integer.parseInt(category_id));
				 if (list2.size()>0) {
					 list.addAll(list2);
				}*/
				 result.put("list", list);
				 result.put("cate", selectList);
				 map.put("datas", result);
				 map.put(Util.RESULT, Util.RESULT_RC_SUCCESS);
				 map.put(Util.MESSAGE, "操作成功！");
			}
		 }
		return map; 
	 }
	 
	 
	/**
	 * 服务编辑页面
	 * data:2018年3月19日 15:46:42
	 * service_id
	 */
	 @RequestMapping(value = "/service_edit")
	 @ResponseBody
	 public Object service_edit(HttpServletRequest request, HttpServletResponse response) {
		 response.setHeader("Access-Control-Allow-Origin", "*"); 		
		 Map<String,Object> map = new HashMap<String, Object>();
		 Map<String,Object> result = new HashMap<String, Object>();
		 String service_id = request.getParameter("service_id");
		 if (ToolUtil.isEmpty(service_id)) {
			 map.put(Util.RESULT, Util.RESULT_RC_CHECK_FAIL);
			 map.put(Util.MESSAGE, "参数不对");
		 }else{
			 BizService service = bizServiceService.selectById(Integer.parseInt(service_id));
			 result.put("service", service);
			 //获得列表图
			 Wrapper<BizServiceImg> wrapper = new EntityWrapper<>();
			 wrapper.eq("service_id", service_id);
			List<Map<String, Object>> ListImg = bizServiceImgService.selectMaps(wrapper);
			for (Map<String, Object> map2 : ListImg) {
				map2.put("src", map2.get("content"));
			}
			 result.put("ListImg", ListImg);
			 //获得规格
			 Wrapper<BizSpecificationValue> specification = new EntityWrapper<>();
			 specification.eq("service_id", service_id);
			List<Map<String, Object>> specList = bizSpecificationValueService.selectMaps(specification);
			for (Map<String, Object> map2 : specList) {
				 Map maps = (Map)JSON.parse(map2.get("value")+"");  
				 map2.put("fieldName", maps.get("fieldName"));
				 map2.put("specName", maps.get("specName"));
				 map2.put("price", maps.get("price"));
				 map2.put("cate_id", maps.get("cate_id"));
				 map2.put("type", maps.get("type"));
			}
			 result.put("specList", specList);
			 map.put("datas", result);
			 map.put(Util.RESULT, Util.RESULT_RC_SUCCESS);
			 map.put(Util.MESSAGE, "操作成功！");
		 }		 
		 return map;
	 }
	 
	/**
	 * 服务编辑修改
	 * data:2018年3月21日 21:48:12
	 * request
	 */
	 @RequestMapping(value = "/service_update")
	 @ResponseBody
	 public Object service_update(HttpServletRequest request, HttpServletResponse response) {
		 response.setHeader("Access-Control-Allow-Origin", "*"); 	
		 Map<String,Object> result = new HashMap<String, Object>();
		 Map<String, Object> map =new HashMap<String, Object>();
		 response.setHeader("Access-Control-Allow-Origin", "*"); 
		 String service_id = request.getParameter("service_id");//商品id
		 String goodsName = request.getParameter("goodsName");//商品名称
		 String goodsSecTitle = request.getParameter("goodsSecTitle");//商品副标题
		 String goodsKeyWord = request.getParameter("goodsKeyWord");//关键字
		 String contractSelectId = request.getParameter("contractSelectId");//合同主体
		 String content_web = request.getParameter("content_web");//商品详情网页版
		 String content_tel = request.getParameter("content_tel");//商品详情手机版
		 String agent_id = request.getParameter("agent_id");//代理商id		
		 String serviceCategary_id = request.getParameter("serviceCategary_id");//分类id
		 //商品的封面
		 String img_1 = request.getParameter("img_1");
		 String img_2 = request.getParameter("img_2");
		 String img_3 = request.getParameter("img_3");
		 String img_4 = request.getParameter("img_4");
		 String img_5 = request.getParameter("img_5");
		 String img_6 = request.getParameter("img_6");		 
		 String formList  = request.getParameter("formList");	
		 //规格值
		 String value = request.getParameter("value");
		 map.put("service_id", service_id);
		 map.put("goodsName", goodsName);
		 map.put("goodsSecTitle", goodsSecTitle);
		 map.put("goodsKeyWord", goodsKeyWord);
		 map.put("contractSelectId", contractSelectId);
		 map.put("content_web", content_web);
		 map.put("content_tel", content_tel);
		 map.put("agent_id", agent_id);
		 map.put("agent_id", agent_id);
		 map.put("serviceCategary_id", serviceCategary_id);		
		 map.put("formList", formList);	
		 map.put("img_1", img_1);
		 map.put("img_2", img_2);
		 map.put("img_3", img_3);
		 map.put("img_4", img_4);
		 map.put("img_5", img_5);
		 map.put("img_6", img_6);
		 map.put("value", value);
		 bizServiceService.update_service(map);	
		 result.put(Util.RESULT, Util.RESULT_RC_SUCCESS);
		 result.put(Util.MESSAGE, "操作成功");
		 return result;
	}
}
