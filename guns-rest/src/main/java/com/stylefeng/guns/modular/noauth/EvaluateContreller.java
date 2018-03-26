package com.stylefeng.guns.modular.noauth;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.auth.util.Util;
import com.stylefeng.guns.modular.system.service.IBizEvaluateService;

/**
*
* 服务商中心 评价管理
* @author dengshuang
*
*/
@RestController
@RequestMapping("/noauth")
public class EvaluateContreller {
	
	@Autowired
	private IBizEvaluateService bizEvaluateService;
	
	/**
	 * 根据代理商id获得评价列表
	 * data:2018年3月9日 10:30:19
	 * 
	 */
	 @RequestMapping(value = "/getEvaluateList")
	 @ResponseBody
	 public Object getEvaluateList(HttpServletRequest request, HttpServletResponse response) {
		 response.setHeader("Access-Control-Allow-Origin", "*"); 
		 Map<String,Object> result = new HashMap<String, Object>();	
		 Map<String,Object> map = new HashMap<String, Object>();	
		 Map<String,Object> page = new HashMap<String, Object>();
		 Map<String,Object> temp = new HashMap<String, Object>();
		 String agent_id = request.getParameter("agent_id");
		 String status = request.getParameter("status");
		 String id = request.getParameter("id");//用户id
		 String pageNo = request.getParameter("pageNo");//页数		
		 int pageSize = 5; //偏移量		
		/* String detail_speed = request.getParameter("detail_speed");
		 String has_content = request.getParameter("has_content");*/
		 if (ToolUtil.isOneEmpty(agent_id,status,id)) {
			 result.put(Util.RESULT, Util.RESULT_RC_CHECK_FAIL);
			 result.put(Util.MESSAGE, "参数不对");
		 }else{			
			 map.put("agent_id", agent_id);
			 map.put("status", status);
			 map.put("passport_id", id);
			 List<Map<String , Object>> list = bizEvaluateService.getEvaluateList(map);
			 int totalCount = list.size();//总条数
			 map.put("pageNo", pageSize*((Integer.parseInt(pageNo))-1));
			 map.put("pageSize", pageSize);
			 List<Map<String , Object>> list2 = bizEvaluateService.getEvaluateList(map);
			 page.put("pageNo", pageNo);
			 page.put("pages", totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize+1);
			 temp.put("page", page);
			 temp.put("list", list2);			 
			 result.put("datas", temp);
			 result.put(Util.RESULT, Util.RESULT_RC_SUCCESS);
			 result.put(Util.MESSAGE, "操作成功");
		 }
		 return result;
	 }

}
