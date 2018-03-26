package com.stylefeng.guns.modular.noauth;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.stylefeng.guns.modular.auth.util.Util;
import com.stylefeng.guns.modular.system.service.IBizCartsService;



@RestController
@RequestMapping("/noauth")
public class ShopCarController {
	
	@Autowired
	private IBizCartsService bizCartsService;
	
	@Autowired
	private IBizCartsService BizCarts;
	
	@Autowired
	private IBizCartsService BizCartsMapper;
	 
 /**
   * 删除购物车
   * data:2018年2月24日09:55:07
   * 购物车id
   */
	@RequestMapping(value = "/deleteCart")
	@ResponseBody
	 public Object deleteCart(HttpServletRequest request, HttpServletResponse response) {	
		response.setHeader("Access-Control-Allow-Origin", "*"); 
		Map<String, Object> map =new HashMap<String, Object>();
		String id = request.getParameter("id");
		bizCartsService.deleteById(Integer.parseInt(id));
		map.put(Util.RESULT, Util.RESULT_RC_SUCCESS);
		map.put(Util.MESSAGE, "移除成功！");
		return map;
	}
 /**
   *
   * 移入收藏夹
   * data:2018年2月24日09:55:07
   * 购物车id
   */
	@RequestMapping(value = "/CollectCart")
	@ResponseBody
	 public Object CollectCart(HttpServletRequest request, HttpServletResponse response) {
		 Map<String, Object> map =new HashMap<String, Object>();	
		 response.setHeader("Access-Control-Allow-Origin", "*");
		 String id = request.getParameter("id");
		 bizCartsService.deleteById(Integer.parseInt(id));
		 map.put(Util.RESULT, Util.RESULT_RC_SUCCESS);
		 map.put(Util.MESSAGE, "移入成功！");
		return map; 
	}
 /**
   *     
   * 移入收藏夹
   * data:2018年2月24日09:55:07
   * 购物车id
	   */
	
	
	
	
}
