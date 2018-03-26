package com.stylefeng.guns.modular.noauth;

import java.math.BigDecimal;
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

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.common.persistence.model.BizAgent;
import com.stylefeng.guns.common.persistence.model.BizAgentCase;
import com.stylefeng.guns.common.persistence.model.BizPaterner;
import com.stylefeng.guns.common.persistence.model.BizService;
import com.stylefeng.guns.modular.auth.util.Util;
import com.stylefeng.guns.modular.system.dao.BizAgentCaseDao;
import com.stylefeng.guns.modular.system.service.IBizAgentCaseService;
import com.stylefeng.guns.modular.system.service.IBizAgentService;
import com.stylefeng.guns.modular.system.service.IBizPaternerService;
import com.stylefeng.guns.modular.system.service.IBizServiceService;


@RestController
@RequestMapping("/noauth")
public class ShopIndexController {
	
	
    @Resource
    BizAgentCaseDao   bizAgentCaseDao;
	
	@Autowired
	private IBizAgentCaseService bizAgentCaseService;
	
	@Autowired
	private IBizServiceService bizServiceService;
	
	@Autowired
	private IBizPaternerService bizPaternerService;
	
	@Autowired
	private  IBizAgentService bizAgentService;
	/**
	 * 	服务案例
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/shop")
	@ResponseBody
	public Object agentcaseList(HttpServletRequest request, HttpServletResponse response){
		
		response.setHeader("Access-Control-Allow-Origin", "*"); 
		Map<String, Object> map = new HashMap<String, Object>();

		//代理商列表
		 Wrapper<BizAgentCase> wrapper = new EntityWrapper<>();

		List<BizAgentCase> shopList =  bizAgentCaseService.selectList(wrapper);

		if(shopList.size()>0){
	
			map.put("datas", shopList);
			map.put(Util.RESULT, Util.RESULT_RC_SUCCESS);
			map.put(Util.MESSAGE, "操作成功");
			return map;
		}
				 		
		return null;
		
	}
	/**
	 * 服务项目
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/service")
	@ResponseBody
	public Object ServiceList(HttpServletRequest request, HttpServletResponse response){
		  
		response.setHeader("Access-Control-Allow-Origin", "*"); 
		Map<String, Object> map = new HashMap<String, Object>();
		Wrapper<BizService> wrapper =  new EntityWrapper<BizService>();
		Page<BizService>  servicePage = new Page<>(1,8);
		 wrapper.eq("status", 1);		
		 servicePage = bizServiceService.selectPage(servicePage,wrapper);
		 if(servicePage !=null){
			 
			    map.put("datas", servicePage);
				map.put(Util.RESULT, Util.RESULT_RC_SUCCESS);
				map.put(Util.MESSAGE, "操作成功");
				return map;			
		 }
		return null;
		
	}
	/**
	 * 合作伙伴
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/paterner")
	@ResponseBody
	public Object Paterner(HttpServletRequest request,HttpServletResponse response){
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> map = new HashMap<String, Object>();
		Wrapper<BizPaterner> wrapper = new EntityWrapper<>();
		List<BizPaterner> pater = bizPaternerService.selectList(wrapper);
		if(pater !=null){
		    map.put("datas", pater);
			map.put(Util.RESULT, Util.RESULT_RC_SUCCESS);
			map.put(Util.MESSAGE, "操作成功");
			return map;	
		}
		return null;
	}
	
	/**
	 * 店铺首页3的案例展示
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/shopCase")
	@ResponseBody
	public Object ShopCase(HttpServletRequest request,HttpServletResponse response){
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> map = new HashMap<String, Object>();
        JSONArray json = new JSONArray();
		Wrapper<BizAgent> wrapper = new EntityWrapper<>();
	
		wrapper.eq("recommend", 1).and().eq("is_star", 1).and()
				.eq("agent_status", 1).and().eq("apply_status", 1);
		List<BizAgent> agentList =  bizAgentService.selectList(wrapper);
		if(agentList !=null){	
			
			for(BizAgent agent:agentList ){
				int agent_id = agent.getAgentId();
				Wrapper<BizService> wrapper2 = new EntityWrapper<>();
				wrapper2.eq("agent_id", agent_id);
				BizService service = bizServiceService.selectOne(wrapper2);
				if(service !=null){
				BigDecimal price = service.getShopPrice();
				String img = service.getListPic();
				String name = service.getName();	
				
				JSONObject oTemp = new JSONObject();
				
				oTemp.put("price", price);
				oTemp.put("img", img);
				oTemp.put("name", name);
				oTemp.put("agent", agent);
				json.add(oTemp);
	
				}	
			}
		}
		map.put("datas", json);
		map.put(Util.RESULT, Util.RESULT_RC_SUCCESS);
		map.put(Util.MESSAGE, "操作成功");
		return map;	
			
	}
	
	/**
	 * 店铺首页3的服务展示
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/serviceCase")
	@ResponseBody
	public Object ServiceCase(HttpServletRequest request,HttpServletResponse response){
		
		response.setHeader("Access-Control-Allow-Origin", "*");
		String category_id = request.getParameter("category_id");		
		Map<String, Object> map = new HashMap<String, Object>();
		Wrapper<BizService> wrapper = new EntityWrapper<>();
		Page<BizService>  servicePage = new Page<>(1,8);
		wrapper.eq("category_id", Integer.valueOf(category_id)).and().eq("status", 1).and().eq("verify", 1);
		servicePage =bizServiceService.selectPage(servicePage, wrapper);
		if(servicePage !=null){
			
			map.put("datas", servicePage);
			map.put(Util.RESULT, Util.RESULT_RC_SUCCESS);
			map.put(Util.MESSAGE, "操作成功");
			return map;	
		}
			
		return null;
	}
	

}
