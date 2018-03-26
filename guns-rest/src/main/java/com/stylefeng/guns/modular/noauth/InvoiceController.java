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

import com.stylefeng.guns.common.persistence.model.BizPassport;
import com.stylefeng.guns.modular.auth.util.Util;
import com.stylefeng.guns.modular.system.service.IBizInvoiceService;
import com.stylefeng.guns.modular.system.service.IBizPassportService;
import com.stylefeng.guns.modular.system.service.IBizServiceService;

/**
*
*发票管理
* @author dengshuang
*
*/
@RestController
@RequestMapping("/noauth")
public class InvoiceController {
	
	@Autowired
	private IBizServiceService bizServiceService;
	
	@Autowired
	private IBizInvoiceService bizinvoiceservice;
	
	@Autowired
	private IBizPassportService bizpassportservice;
			
	  /**
	   * 发票列表
	   * data:2018年3月6日 15:06:00
	   * 
	   */
	 @RequestMapping(value = "/InvoiceList")
	 @ResponseBody
	 public Object InvoiceList(HttpServletRequest request, HttpServletResponse response) {
		 response.setHeader("Access-Control-Allow-Origin", "*"); 
		 Map<String,Object> result = new HashMap<String, Object>();	
		 Map<String,Object> map = new HashMap<String, Object>();	
		 Map<String,Object> page = new HashMap<String, Object>();
		 Map<String,Object> temp = new HashMap<String, Object>();
		 String id = request.getParameter("id");//会员id
		 String type = request.getParameter("type");//状态
		 String begin_time = request.getParameter("begin_time");//开始时间
		 String end_time = request.getParameter("end_time");//结束时间
		 String order_num = request.getParameter("order_num");//
		 String service_type = request.getParameter("service_type");//服务类型
		 String apply_name = request.getParameter("apply_name");//订单申请人
		 String pageNo = request.getParameter("pageNo");//页数		
		 int pageSize = 5; //偏移量	
		 map.put("id", id);
		 map.put("type", type);
		 map.put("begin_time", begin_time);
		 map.put("end_time", end_time);
		 map.put("order_num", order_num);
		 map.put("service_type", service_type);
		 map.put("apply_name", apply_name);
		 List<Map<String,Object>> list = bizinvoiceservice.getInvoiceList(map);
		 int totalCount = list.size();//总条数
		 map.put("pageNo", pageSize*((Integer.parseInt(pageNo))-1));
		 map.put("pageSize", pageSize);
		 List<Map<String,Object>> list2 = bizinvoiceservice.getInvoiceList(map);
		 page.put("pageNo", pageNo);
		 page.put("pages", totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize+1);
		 temp.put("page", page);
		 temp.put("list", list2);			 
		 result.put("datas", temp);
		 result.put(Util.RESULT, Util.RESULT_RC_SUCCESS);
		 result.put(Util.MESSAGE, "操作成功");
		 return result;
	 }
	 
   /**
	 * 根据用户id 返回用户信息
	 * data:2018年3月6日 15:06:00
	 * 
	 */	 
	 @RequestMapping(value = "/memberInfo")
	 @ResponseBody
	 public Object InvoiceList1(HttpServletRequest request, HttpServletResponse response) {
		 response.setHeader("Access-Control-Allow-Origin", "*"); 
		 Map<String,Object> result = new HashMap<String, Object>();	
		 String id = request.getParameter("id");
		 if (id!=null&&!"".equals(id.trim())) {
			 //对手机 和 邮箱的处理
			BizPassport member = bizpassportservice.selectById(Integer.parseInt(id));
			if (member.getEmail()!=null&&!"".equals(member.getEmail().trim())) {
				member.setEmail(Util.emailReplace(member.getEmail()));
			}
			if (member.getMobile()!=null&&!"".equals(member.getMobile().trim())) {
				member.setMobile(Util.mobileReplace(member.getMobile()));
			}
			 result.put("datas", member);
			 result.put(Util.RESULT, Util.RESULT_RC_SUCCESS);
			 result.put(Util.MESSAGE, "操作成功");
		 }else{
			 result.put(Util.RESULT, Util.RESULT_RC_CHECK_FAIL);
			 result.put(Util.MESSAGE, "参数不对");
		 }		
		 return result;
	 }
}
