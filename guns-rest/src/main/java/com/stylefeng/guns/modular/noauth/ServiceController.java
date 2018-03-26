package com.stylefeng.guns.modular.noauth;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.stylefeng.guns.common.persistence.model.BizAgent;
import com.stylefeng.guns.common.persistence.model.BizCarts;
import com.stylefeng.guns.common.persistence.model.BizContract;
import com.stylefeng.guns.common.persistence.model.BizCoupons;
import com.stylefeng.guns.common.persistence.model.BizEvaluate;
import com.stylefeng.guns.common.persistence.model.BizOrders;
import com.stylefeng.guns.common.persistence.model.BizOrdersInfo;
import com.stylefeng.guns.common.persistence.model.BizPassport;
import com.stylefeng.guns.common.persistence.model.BizPassportCoupon;
import com.stylefeng.guns.common.persistence.model.BizService;
import com.stylefeng.guns.common.persistence.model.BizServiceCategory;
import com.stylefeng.guns.common.persistence.model.BizServiceInfo;
import com.stylefeng.guns.common.persistence.model.BizServiceSpecification;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.auth.util.Util;
import com.stylefeng.guns.modular.system.service.IBizAgentService;
import com.stylefeng.guns.modular.system.service.IBizCartsService;
import com.stylefeng.guns.modular.system.service.IBizContractService;
import com.stylefeng.guns.modular.system.service.IBizCouponsService;
import com.stylefeng.guns.modular.system.service.IBizEvaluateService;
import com.stylefeng.guns.modular.system.service.IBizOrdersInfoService;
import com.stylefeng.guns.modular.system.service.IBizOrdersService;
import com.stylefeng.guns.modular.system.service.IBizPassportCouponService;
import com.stylefeng.guns.modular.system.service.IBizPassportService;
import com.stylefeng.guns.modular.system.service.IBizServiceCategoryService;
import com.stylefeng.guns.modular.system.service.IBizServiceFiledsService;
import com.stylefeng.guns.modular.system.service.IBizServiceService;
import com.stylefeng.guns.modular.system.service.IBizServiceSpecificationService;
import com.stylefeng.guns.modular.system.service.IBizSpecificationValueService;

@RestController
@RequestMapping("/noauth")
public class ServiceController {
	
	@Autowired
	private  IBizAgentService bizAgentService;
	
	@Autowired
	private IBizServiceService bizServiceService;
	
	@Autowired
	private IBizCouponsService bizCouponsService;
	
	@Autowired
	private IBizPassportCouponService bizPassportCouponService;
	
	@Autowired
	private IBizCartsService bizCartsService;
	
	@Autowired
	private  IBizOrdersService bizOrdersService;
	
	
	@Autowired
	private  IBizOrdersInfoService bizOrdersInfoService;
	
	@Autowired
	private IBizPassportService bizPassportService;
	
	@Autowired
	private IBizEvaluateService bizEvaluateService;
	
	@Autowired
	private IBizServiceFiledsService bizServiceFiledsService;
	
	@Autowired
	private IBizContractService bizContractService;
	
	@Autowired
	private IBizServiceSpecificationService  bizServiceSpecificationService;
	
	@Autowired
	private IBizSpecificationValueService   bizSpecificationValueService;
	
	@Autowired
	private IBizServiceCategoryService bizServiceCategoryService;
		
		
	  /**
	   * 服务商主页 获取推荐代理商列表
	   * data:2018年2月8日 09:43:17
	   * 
	   */
	 @RequestMapping(value = "/agentListCate")
	 @ResponseBody
	 public Object agentList(String id,HttpServletRequest request, HttpServletResponse response) {
		 response.setHeader("Access-Control-Allow-Origin", "*"); 
		 Map<String,Object> result = new HashMap<String, Object>();
		 List<Integer> cate_id = new ArrayList<Integer>();
		 	 String category_id = request.getParameter("category_id");
		 	 //
		 	 if (category_id!=null&&!"".equals(category_id.trim())) {
		 		Wrapper<BizServiceCategory> cate = new EntityWrapper<>();	
			 	cate.eq("parent_category_id", category_id);		 	
			 	List<BizServiceCategory> selectList = bizServiceCategoryService.selectList(cate);		
			 	//是否含有二级分类
			 	if (selectList.size()>0) {
			 		for (BizServiceCategory bizServiceCategory : selectList) {
			 			 Wrapper<BizServiceCategory> cate2 = new EntityWrapper<>();
			 			 cate2.eq("parent_category_id", bizServiceCategory.getParentCategoryId());
			 			 List<BizServiceCategory> selectList2 = bizServiceCategoryService.selectList(cate2);		 			 
			 			 //是否含有三级分类
			 			 if (selectList2.size()>0) {	
			 				for (BizServiceCategory bizServiceCategory2 : selectList2) {
			 					cate_id.add(bizServiceCategory2.getCategoryId());
							}
						}else{
							cate_id.add(bizServiceCategory.getCategoryId());
						}
					}
				}else{
					cate_id.add(Integer.parseInt(category_id));
				}
			 	//获得服务
			 	List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
			 	List<BizService> selectBatchIds = bizServiceService.selectBatchIds(cate_id);
			 	for (BizService bizService : selectBatchIds) {
			 		 List<BizService> tmp = new ArrayList<BizService>();
			 		 tmp.add(bizService);
			 		 Wrapper<BizAgent> wrapper = new EntityWrapper<>();	
			 		 wrapper.eq("agent_id", bizService.getAgentId());
			 		 Map<String, Object> selectMap = bizAgentService.selectMap(wrapper);
			 		 if (selectMap!=null) {
			 			 selectMap.put("service_list", tmp);
					}			 		
			 		 list.add(selectMap);
				}
			 	 result.put("datas", list.size()<5?list:list.subList(0, 4));
				 result.put(Util.RESULT, Util.RESULT_RC_SUCCESS);
				 result.put(Util.MESSAGE, "操作成功");
			 	
			}else{
				//默认代理商列表
				 Wrapper<BizAgent> wrapper = new EntityWrapper<>();		 
				 wrapper.eq("agent_status", 1);
				 wrapper.eq("apply_status", 1);
				 wrapper.eq("recommend", 1);
				 wrapper.eq("agent_type", 2);
				 /*wrapper.eq("agent_id", agent_id);*/
				 List<Map<String, Object>> list = bizAgentService.selectMaps(wrapper);
				 if (list.size()>0) {
					 for (Map<String, Object> map : list) {
						 Wrapper<BizService> wrapper_service = new EntityWrapper<>();
						 wrapper_service.eq("agent_id", map.get("agentId"));
						 wrapper_service.eq("status", 1);
						 List<BizService> serviceList = bizServiceService.selectList(wrapper_service);	
						 if (serviceList.size()>4) {
							 map.put("service_list", serviceList.subList(0, 3));
						}else{
							 map.put("service_list", serviceList);
						}				
					 }							
				}		
				 result.put("datas", list);
				 result.put(Util.RESULT, Util.RESULT_RC_SUCCESS);
				 result.put(Util.MESSAGE, "操作成功");
			}		 			 				 			
		 return result;
	 }
	 
	 /**
	   * 服务商详情页面 服务项目
	   * data:2018年2月8日 13:37:34
	   * 代理商信息 服务项目信息 优惠券信息
	   */
	 @RequestMapping(value = "/couponsList")
	 @ResponseBody
	 public Object couponsList(String id,String member_id,HttpServletRequest request, HttpServletResponse response) {		
		 response.setHeader("Access-Control-Allow-Origin", "*"); 
		 Map<String, Object> map =new HashMap<String, Object>();	
		 Map<String, Object> result =new HashMap<String, Object>();
		 if (id!=null&&!"".equals(id.trim())) {
			//服务项目
			 BizService service = bizServiceService.selectById(id);	
			 //获得代理商的信息
			 BizAgent agent = bizAgentService.selectById(service.getAgentId());
			 //获取优惠卷信息			 
			 Wrapper<BizCoupons> wrapper = new EntityWrapper<>();
			 wrapper.eq("agent_id", service.getAgentId());
			 List<BizCoupons> coupsonList = bizCouponsService.selectList(wrapper);			 
			 for (BizCoupons bizCoupons : coupsonList) {
				BigDecimal min = bizCoupons.getMin();
				BigDecimal cost = bizCoupons.getCost();
				bizCoupons.setCouponName("满"+min+"减少"+cost);
				//判断用户是否领取优惠券
				 Wrapper<BizPassportCoupon> wrapper_member = new EntityWrapper<>();
				 wrapper_member.eq("passport_id", member_id);
				 wrapper_member.eq("coupon_id", bizCoupons.getCouponId());
				 List<BizPassportCoupon> passportList = bizPassportCouponService.selectList(wrapper_member);
				 if (passportList.size()>0) {
					 bizCoupons.setStatus(1);
				}else{
					bizCoupons.setStatus(0);
				}				 
			}
			 map.put("service", service);
			 map.put("agent", agent);
			 map.put("coupsonList", coupsonList);
			 result.put("datas", map);
			 result.put(Util.RESULT, Util.RESULT_RC_SUCCESS);
			 result.put(Util.MESSAGE, "操作成功");
		}else{
			result.put(Util.RESULT, Util.RESULT_RC_CHECK_FAIL);
			result.put(Util.MESSAGE, "参数不对");
		}		 		 
		 return result;
	 }
	 
	 /**
	   * 领取优惠券
	   * data:2018年2月8日 15:21:29
	   * 优惠券id 会员id
	   */
	 @RequestMapping(value = "/receiveCoupons")
	 @ResponseBody
	 public Object receiveCoupons(String id,String member_id,HttpServletRequest request, HttpServletResponse response) {
		 Map<String, Object> map =new HashMap<String, Object>();	
		 response.setHeader("Access-Control-Allow-Origin", "*"); 
		 /*判断是否登录*/
		 if (ToolUtil.isOneEmpty(id,member_id)) {			
			 map.put(Util.RESULT, Util.RESULT_RC_CHECK_FAIL);
			 map.put(Util.MESSAGE, "参数不对");
		}else{			
			BizCoupons coupons = bizCouponsService.selectById(id);	
			if (coupons!=null) {
				Integer num = coupons.getNum();
				 if (num==0) {
					 map.put(Util.RESULT, Util.RESULT_SL_SUCCESS);
					 map.put(Util.MESSAGE, "优惠券已经领完");
				}else{
					//优惠券数量减少
					num = num -1;
					coupons.setNum(num);
					bizCouponsService.updateById(coupons);
					//插入会员优惠券中间表					
					BizPassportCoupon passport =  new BizPassportCoupon();
					passport.setPassportId(Integer.parseInt(member_id));
					passport.setCost(coupons.getCost());
					passport.setMin(coupons.getMin());
					passport.setIsUsed(2);
					passport.setCouponId(coupons.getCouponId());
					passport.setCreatedAt(new Date());
					passport.setCouponUserId(Integer.parseInt(member_id));
					bizPassportCouponService.insert(passport);
					//未设置过期时间
					map.put(Util.RESULT, Util.RESULT_RC_SUCCESS);
					map.put(Util.MESSAGE, "领取成功！");
				}			
			}else{
				map.put(Util.RESULT, Util.RESULT_SL_SUCCESS);
				map.put(Util.MESSAGE, "优惠券不存在");
			}
			 
		}
		 return map;
	 }	
	 
	 /**
	   * 加入购物车
	   * data:2018年2月8日 16:19:11
	   * 服务项目id 会员id
	   */ 
	 @RequestMapping(value = "/joinCart")
	 @ResponseBody
	 public Object joinCart(HttpServletRequest request, HttpServletResponse response){
		 Map<String, Object> map =new HashMap<String, Object>();
		 response.setHeader("Access-Control-Allow-Origin", "*"); 
		 //会员id
		 String id = request.getParameter("id");
		 String service_id = request.getParameter("service_id");
		 String num = request.getParameter("num");
		 if (ToolUtil.isOneEmpty(id,service_id,num)) {
			 map.put(Util.RESULT, Util.RESULT_RC_CHECK_FAIL);
			 map.put(Util.MESSAGE, "参数不对");
		 }else{			 
			 BizService bizService = bizServiceService.selectById(service_id);
			 BizCarts bizCarts  = new BizCarts();
			 if (bizService!=null) {
				 bizCarts.setAgentId(bizService.getAgentId());
				 bizCarts.setNum(Integer.parseInt(num));
				 bizCarts.setServiceId(bizService.getServiceId());
				 bizCarts.setPassportId(Integer.parseInt(id));
				 bizCarts.setServiceName(bizService.getName());
				 bizCarts.setShopPrice(bizService.getShopPrice());				 
				 bizCarts.setsTotal(bizService.getShopPrice().multiply(new BigDecimal(num)));
				 bizCartsService.insert(bizCarts);
				 map.put(Util.RESULT, Util.RESULT_RC_SUCCESS);
				 map.put(Util.MESSAGE, "成功加入购物车！");
			}else{
				map.put(Util.RESULT, Util.RESULT_RC_NO_DATA);
				map.put(Util.MESSAGE, "服务不存在");
			}			
		 }
		 return map;
	 }
	 

	 /**
	   * 获取购物车
	   * data:2018年2月9日 14:27:11
	   *会员id 
	   */ 
	 @RequestMapping(value = "/carList")
	 @ResponseBody
	 public Object carListById(HttpServletRequest request, HttpServletResponse response){
		 Map<String, Object> map =new HashMap<String, Object>();
		 response.setHeader("Access-Control-Allow-Origin", "*"); 
		 JSONArray  array = new JSONArray();
		 //会员id
		 String id = request.getParameter("id");
		 Wrapper<BizCarts> wrapper = new EntityWrapper<>();
	     wrapper.eq("passport_id", id);
	     List<BizCarts>  cartList = bizCartsService.selectList(wrapper);
	     Set  agentIds = new HashSet();
	     Map<Integer,List<BizCarts>> cartMap = new HashMap<Integer,List<BizCarts>>();
	     for(BizCarts cart : cartList){
	    	    Integer temp =  cart.getAgentId();
	    	    if(cartMap.get(temp) != null ) {
	    	    	List<BizCarts> cartsTemp =cartMap.get(temp);
	    	    	cartsTemp.add(cart);
	    	    	cartMap.put(temp,cartsTemp);
	    	    } else {
	    	    	List<BizCarts> cartsTemp = new ArrayList<BizCarts>();
	    	    	cartsTemp.add(cart);
	    	    	cartMap.put(temp,cartsTemp);
	    	    }
	     }
	     agentIds = cartMap.keySet();
	     List  ids = new ArrayList();
	     ids.addAll(agentIds);
	    List<BizAgent>  agentList =  bizAgentService.selectBatchIds(ids);
	    for(BizAgent agent : agentList) {
	    	System.out.println(agent.getExample());
	    	JSONObject object = new JSONObject();
	    	object.put("item", agent);
	    	object.put("list", cartMap.get(agent.getAgentId()));
	    	array.add(object);
	    }
		 map.put("datas", array);
		 map.put(Util.RESULT, Util.RESULT_RC_SUCCESS);
		 map.put(Util.MESSAGE, "操作成功");
		 return map;
	 }
	 
	 
	 
	 /**
	   * 获取订单
	   * data:2018年2月11日 14:28:11
	   *会员id 
	   */ 
	 @RequestMapping(value = "/orderList")
	 @ResponseBody
	 public Object orderListById(HttpServletRequest request, HttpServletResponse response){
		 Map<String, Object> map =new HashMap<String, Object>();
		 response.setHeader("Access-Control-Allow-Origin", "*"); 
		 JSONArray  array = new JSONArray();
		 //会员id
		 String id = request.getParameter("id");
		 Wrapper<BizOrders> wrapper = new EntityWrapper<>();
	     wrapper.eq("passport_id", id);
	     List<BizOrders>  ordersList = bizOrdersService.selectList(wrapper);
	     Set  agentIds = new HashSet();
	     Map<Integer,JSONArray> orderMap = new HashMap<Integer,JSONArray>();
	     
	     //订单分类，根据代理分类
	     for(BizOrders order : ordersList){
	    	 	//获取订单详情信息
	    	    Integer temp =  order.getAgentId();
	    	    Integer orderId = order.getOrderId();
	    	    Wrapper<BizOrdersInfo> wrapperInfo = new EntityWrapper<>();
	   	     	wrapperInfo.eq("order_id", orderId);
	   	    	List<BizOrdersInfo>  ordersInfoList = bizOrdersInfoService.selectList(wrapperInfo);
	    	    JSONArray records = new JSONArray();
	   	    	for(BizOrdersInfo oInfo : ordersInfoList){
	    	    	JSONObject record = new JSONObject();	
	    	    	Integer sid = oInfo.getServiceId();
	    	    	BizService bs = bizServiceService.selectById(sid);
	    	    	record.put("orderId", orderId);
	    	    	record.put("serviceId", sid);
	    	    	record.put("serviceName", oInfo.getServiceName());
	    	    	record.put("serviceNum", oInfo.getServiceNum());
	    	    	record.put("servicePrice", oInfo.getServicePrice());
	    	    	record.put("serviceCount", oInfo.getServiceCount());
	    	    	record.put("serviceImg", bs.getListPic());
	    	    	records.add(record);
	    	    }
	    	    
	    	    
	    	    if(orderMap.get(temp) != null ) {
	    	    	JSONArray tempArray = orderMap.get(temp);
	    	    	tempArray.addAll(records);
	    	    	orderMap.put(temp,tempArray);
	    	    } else {
	    	    	JSONArray tempArray = new JSONArray();
	    	    	tempArray.addAll(records);
	    	    	orderMap.put(temp,tempArray);
	    	    }
	     }
	     agentIds = orderMap.keySet();
	     List  ids = new ArrayList();
	     ids.addAll(agentIds);
	    List<BizAgent>  agentList =  bizAgentService.selectBatchIds(ids);
	    for(BizAgent agent : agentList) {
	    	JSONObject object = new JSONObject();
	    	object.put("item", agent);
	    	object.put("list", orderMap.get(agent.getAgentId()));
	    	array.add(object);
	    }
		 map.put("datas", array);
		 map.put(Util.RESULT, Util.RESULT_RC_SUCCESS);
		 map.put(Util.MESSAGE, "操作成功");
		 return map;
	 }
	 

	/**
	  * 立即下单
	  * data:2018年2月9日 11:21:28
	  * 商品id 会员id
	  */
	 @RequestMapping(value = "/increaseOrder")
	 @ResponseBody
	 public Object increaseOrder(HttpServletRequest request, HttpServletResponse response){	 
		 Map<String, Object> map =new HashMap<String, Object>();
		 response.setHeader("Access-Control-Allow-Origin", "*"); 
		 String service_id = request.getParameter("service_id");//商品id		 
		 String id = request.getParameter("id");//会员id
		 String num = request.getParameter("num");//购买数量
		 if (ToolUtil.isOneEmpty(service_id,id)) {
			 map.put(Util.RESULT, Util.RESULT_RC_CHECK_FAIL);
			 map.put(Util.MESSAGE, "参数不对");
		}else {			
			 BizService service = bizServiceService.selectById(service_id);
			 if (service!=null) {				
				 BizPassport passport = bizPassportService.selectById(id);
				 if (passport!=null) {
					 BizOrders order = new BizOrders();
					 order.setCreatedAt(new Date());
					 //生成订单号码
					 order.setOrderNumber(Util.getDingDnaNo(Integer.parseInt(service_id), num));
					 order.setStatus(0);
					 order.setAgentId(service.getAgentId());					
					 order.setPassportId(passport.getPassportId().intValue());
					 order.setTotalPrice(service.getShopPrice().multiply(new BigDecimal(num)));
					 order.setUsername(passport.getNickname());
					 order.setTelphone(passport.getMobile());
					 order.setMobile(passport.getMobile());
					 order.setEmail(passport.getEmail());
					 bizOrdersService.insert(order);
					 map.put(Util.RESULT, Util.RESULT_RC_SUCCESS);
					 map.put(Util.MESSAGE, "下单成功");
				}else{
					map.put(Util.RESULT, Util.RESULT_RC_CHECK_FAIL);
					map.put(Util.MESSAGE, "用户不存在");
				}			
			}else{
				map.put(Util.RESULT, Util.RESULT_RC_CHECK_FAIL);
				map.put(Util.MESSAGE, "服务不存在");
			}			
		}		 
		return map; 
	 }

	 
	 /**
	  * 获取商品的评论
	  * data:2018年2月9日 11:21:28
	  * 商品id 会员id
	  */
	 @RequestMapping(value = "/evaluateList")
	 @ResponseBody
	 public Object evaluateList(HttpServletRequest request, HttpServletResponse response){	
		 Map<String, Object> map =new HashMap<String, Object>();
		 response.setHeader("Access-Control-Allow-Origin", "*"); 
		 String service_id = request.getParameter("service_id");
		 if (ToolUtil.isEmpty(service_id)) {
			 map.put(Util.RESULT, Util.RESULT_RC_CHECK_FAIL);
			 map.put(Util.MESSAGE, "参数不对");
		}else{
			Wrapper<BizEvaluate> wrapper = new EntityWrapper<>();
			wrapper.eq("service_id", service_id);
			List<Map<String, Object>> list = bizEvaluateService.selectMaps(wrapper);
			for (Map<String, Object> map2 : list) {
				String passport_id = map2.get("passportId")+"";
				if (passport_id!=null&&!"".equals(passport_id.trim())) {
					BizPassport passport = bizPassportService.selectById(passport_id);
					map2.put("passport", passport);
				}
			}
			map.put("datas", list);
			map.put(Util.RESULT, Util.RESULT_RC_SUCCESS);
			map.put(Util.MESSAGE, "操作成功");
		}		 
		 return map;
	 }
	 
	 /**
	  * 获取精选商品
	  * data:2018年2月23日 09:44:22
	  * 代理商id
	  */
	 @RequestMapping(value = "/serviceSelected")
	 @ResponseBody
	 public Object serviceSelected(HttpServletRequest request, HttpServletResponse response){	
		 Map<String, Object> map =new HashMap<String, Object>();
		 response.setHeader("Access-Control-Allow-Origin", "*"); 		 
			Wrapper<BizService> wrapper = new EntityWrapper<>();
			wrapper.eq("selected", 1);			
			List<BizService> list = bizServiceService.selectList(wrapper);
			map.put("datas", list.size()<5?list:list.subList(0, 4));
			map.put(Util.RESULT, Util.RESULT_RC_SUCCESS);
			map.put(Util.MESSAGE, "操作成功");		
		 return map;
	 }
	 	 
	 /**
	  * 发布商品
	  * data:2018年3月1日 09:30:53
	  * 分类id 
	  */
	 @RequestMapping(value = "/publish_goods_form")
	 @ResponseBody
	 public Object publish_goods_form(HttpServletRequest request, HttpServletResponse response){	
		 Map<String, Object> map =new HashMap<String, Object>();
		 response.setHeader("Access-Control-Allow-Origin", "*"); 
		 String serviceCategary_id = request.getParameter("serviceCategary_id");
		 String agent_id = request.getParameter("agent_id");
		 if (ToolUtil.isEmpty(agent_id)) {
			 map.put(Util.RESULT, Util.RESULT_RC_CHECK_FAIL);
			 map.put(Util.MESSAGE, "参数不对");
		 }else{
			 Map<String, Object> result = new HashMap<String, Object>();
			 result.put("serviceCategary_id", serviceCategary_id);
			 result.put("agent_id", agent_id);
			 Map<String, Object> list = bizServiceFiledsService.getGoodFiled(result);
			 map.put("datas", list);
			 map.put(Util.RESULT, Util.RESULT_RC_SUCCESS);
			 map.put(Util.MESSAGE, "操作成功");
		 }		
		 return map;
	 }
	 	 
	 /**
	   * 发布商品
	   * data:2018年3月1日 15:05:58
	   * form表单
	   */
	 @RequestMapping(value = "/publish_service")
	 @ResponseBody
	 public Object publish_service(HttpServletRequest request, HttpServletResponse response){	
		 Map<String, Object> result =new HashMap<String, Object>();
		 Map<String, Object> map =new HashMap<String, Object>();
		 response.setHeader("Access-Control-Allow-Origin", "*"); 
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
		 bizServiceService.publish_service(map);	
		 result.put(Util.RESULT, Util.RESULT_RC_SUCCESS);
		 result.put(Util.MESSAGE, "操作成功");
		 return result;
	 }
	 
	 
	 /**
	   * 获得合同列表
	   * data:2018年3月1日 15:06:46
	   * 参数代理人id
	   */
	 @RequestMapping(value ="/contract_list")
	 @ResponseBody
	 public Object contract_list(HttpServletRequest request, HttpServletResponse response){	
		 Map<String, Object> map =new HashMap<String, Object>();
		 response.setHeader("Access-Control-Allow-Origin", "*"); 
		 String agent_id = request.getParameter("agent_id");
		 if (ToolUtil.isEmpty(agent_id)) {
			 map.put(Util.RESULT, Util.RESULT_RC_CHECK_FAIL);
			 map.put(Util.MESSAGE, "参数不对");
		}else{
			Wrapper<BizContract> wrapper = new EntityWrapper<BizContract>();
			//wrapper.eq("agent_id", agent_id);
			List<BizContract> list = bizContractService.selectList(wrapper);
			//默认选择
			BizContract bizContract = new BizContract();
			bizContract.setName("请选择合同主体");
			list.add(bizContract);
			map.put("datas", list);
			map.put(Util.RESULT, Util.RESULT_RC_SUCCESS);
			map.put(Util.MESSAGE, "操作成功");		
		}
		 return map;		 
		 }
	 
	 /**
	   * 获得规格
	   * data:2018年3月14日 15:31:10
	   * 分类id
	   */
	 @RequestMapping(value ="/specification_list")
	 @ResponseBody
	 public Object specification_list(HttpServletRequest request, HttpServletResponse response){	
		 Map<String, Object> map =new HashMap<String, Object>();
		 response.setHeader("Access-Control-Allow-Origin", "*"); 
		 String serviceCategary_id = request.getParameter("serviceCategary_id");
		 if (ToolUtil.isEmpty(serviceCategary_id)) {
			 map.put(Util.RESULT, Util.RESULT_RC_CHECK_FAIL);
			 map.put(Util.MESSAGE, "参数不对");
		}else{
			 Wrapper<BizServiceSpecification> wrapper = new EntityWrapper<BizServiceSpecification>();
			 /*wrapper.eq("category_id", serviceCategary_id);*/
			 List<Map<String, Object>> list = bizServiceSpecificationService.selectMaps(wrapper);
			 //遍历表单
			 for (Map<String, Object> map2 : list) {
				 List<Map<String, Object>> childList = new ArrayList<Map<String,Object>>();
				map2.put("check", false);
				map2.put("show", false);				
				String scale = map2.get("scale")+"";
				if (scale!=null) {
					String[] split = scale.split(",");
					for (int i = 0; i < split.length; i++) {
						Map<String, Object> value =new HashMap<String, Object>();
						value.put("specId", i);
						value.put("specName", split[i]);
						value.put("check", false);
						childList.add(value);
					}
					map2.put("childList", childList);
				}
			}
			map.put("datas", list);
			map.put(Util.RESULT, Util.RESULT_RC_SUCCESS);
			map.put(Util.MESSAGE, "操作成功");		
		}		
		 return map;
	 }
	 
	 
	 /**
	   * 对规格的处理
	   * data:2018年3月14日 16:32:59
	   * json字符串
	   */
	 @RequestMapping(value ="/specification_value")
	 @ResponseBody
	 public Object specification_value(HttpServletRequest request, HttpServletResponse response){	
		 Map<String, Object> map =new HashMap<String, Object>();
		 List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();		 
		 response.setHeader("Access-Control-Allow-Origin", "*"); 
		 String value = request.getParameter("value");
		//遍历方式2
		 JSONArray jsonArray = JSON.parseArray(value);	
	        for (Object obj : jsonArray) {	        	
	            JSONObject jsonObject = (JSONObject) obj;	                       
	            JSONArray childList = JSON.parseArray(jsonObject.get("childList")+"");
	            for (Object object : childList) {	            	
	            	JSONObject childjsonObject = (JSONObject) object;		            	
	            	if (childjsonObject.get("check").equals(true)) {		            		
	            		Map<String, Object> service =new HashMap<String, Object>();	            		
	            		service.put("specName", childjsonObject.get("specName"));
	            		service.put("fieldName", jsonObject.get("fieldName"));
	            		service.put("cate_id", jsonObject.get("id"));//规格表主键
	            		service.put("price", null);//价格
	            		service.put("type", 2);//默认面议
	            		list.add(service);
					}			            	
				}		            
	      }		 
	        map.put("datas", list);
			map.put(Util.RESULT, Util.RESULT_RC_SUCCESS);
			map.put(Util.MESSAGE, "操作成功");		
		 return map;		 		 
	 }
	 
	 
	 /**
	   * 代理商id 获得会员id
	   * data:2018年3月16日 10:49:56
	   * 代理商id
	   */
	 @RequestMapping(value ="/getMemberByAgentId")
	 @ResponseBody
	 public Object getMemberByAgentId(HttpServletRequest request, HttpServletResponse response){	
		 Map<String, Object> map =new HashMap<String, Object>();
		 response.setHeader("Access-Control-Allow-Origin", "*"); 
		 //代理商id
		 String user_id = request.getParameter("user_id");
		 if (ToolUtil.isEmpty(user_id)) {
			 map.put(Util.RESULT, Util.RESULT_RC_CHECK_FAIL);
			 map.put(Util.MESSAGE, "参数不对");
		}else{
			 BizAgent agent = bizAgentService.selectById(Integer.parseInt(user_id));
			 map.put("datas", agent);
			 map.put(Util.RESULT, Util.RESULT_RC_SUCCESS);
			 map.put(Util.MESSAGE, "操作成功");		
		}				
		 return map;
	 }
	 
	 
	 /**
	   * 测试方法 循环插入评论
	   * data:2018年2月9日 14:11:28
	   * 代理商 商品  信息
	   */
	/* @RequestMapping(value = "/demo")
	 @ResponseBody
	 public void demo(String id,HttpServletRequest request, HttpServletResponse response) {
		 BizContract bizevaluate = new BizContract();
		 bizevaluate.setAddress("北京天安门");		
		 bizContractService.insert(bizevaluate);
		 
									 		 
	 }*/
}
