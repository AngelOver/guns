package com.stylefeng.guns.modular.noauth;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.stylefeng.guns.common.persistence.model.BizPassport;
import com.stylefeng.guns.common.persistence.model.BizPassportVerifyApply;
import com.stylefeng.guns.core.support.BeanKit;
import com.stylefeng.guns.core.util.MD5Util;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.auth.util.Util;
import com.stylefeng.guns.modular.system.service.IBizPassportCouponService;
import com.stylefeng.guns.modular.system.service.IBizPassportService;
import com.stylefeng.guns.modular.system.service.IBizPassportVerifyApplyService;

/**
*
*个人中心接口
* @author dengshuang
*
*/
@RestController
@RequestMapping("/noauth")
public class UserCenterContrellor {
	
	@Autowired
	private IBizPassportService bizPassportService;
	
	@Autowired
	private IBizPassportCouponService bizPassportCouponService;
	
	@Autowired
	private IBizPassportVerifyApplyService bizPassportVerifyApplyService;
	
	/**
	 * 根据用户id 判断用户安全级别
	 * data:2018年3月7日 10:40:02
	 * 
	 */	 
	 @RequestMapping(value = "/security_level")
	 @ResponseBody
	 public Object security_level(HttpServletRequest request, HttpServletResponse response) {
		 response.setHeader("Access-Control-Allow-Origin", "*"); 
		 Map<String,Object> result = new HashMap<String, Object>();	
		 String id = request.getParameter("id");
		 int safety_score = 0;
		 if (id!=null&&!"".equals(id.trim())) {
			BizPassport member = bizPassportService.selectById(Integer.parseInt(id));
			if(member!=null){
				Map<String, Object> map = BeanKit.beanToMap(member);			
				//判断是否验证手机
				if(ToolUtil.isOneEmpty(member.getVerifyTel(),member.getVerifyEmail(),member.getVerifyPassword())){
					member.setVerifyEmail(2);
					member.setVerifyPassword(2);
					member.setVerifyTel(2);
					bizPassportService.updateById(member);
				}
				if (member.getVerifyTel()==1) {
					safety_score+=30;
				}			
				//判断是否验证邮箱
				if (member.getVerifyEmail()==1) {
					safety_score+=30;
				}
				//判断是否验证密码
				if (member.getVerifyPassword()==1){
					safety_score+=30;
				}	
				 map.put("safety_score", safety_score);
				 result.put("datas", map);
				 result.put(Util.RESULT, Util.RESULT_RC_SUCCESS);
				 result.put(Util.MESSAGE, "操作成功");
			}else{
				 result.put(Util.RESULT, Util.RESULT_RC_NO_DATA);
				 result.put(Util.MESSAGE, "没找结果");
			}			 
		 }else{
			 result.put(Util.RESULT, Util.RESULT_RC_CHECK_FAIL);
			 result.put(Util.MESSAGE, "参数不对");
		 }		
		 return result;
	 }
	 
   /**
	 * 修改用户手机号码
	 * data:2018年3月7日 15:01:12
	 * 
	 */	
	 @RequestMapping(value = "/verify_tel")
	 @ResponseBody
	 public Object verify_tel(HttpServletRequest request, HttpServletResponse response) {
		 response.setHeader("Access-Control-Allow-Origin", "*"); 
		 Map<String,Object> result = new HashMap<String, Object>();	
		 String telphone = request.getParameter("telphone");
		 String code = request.getParameter("code");
		 String id = request.getParameter("id");		 
		 if (ToolUtil.isOneEmpty(id,code,telphone)) {
			 result.put(Util.RESULT, Util.RESULT_RC_CHECK_FAIL);
			 result.put(Util.MESSAGE, "参数不对");
		 }else{
			 /**完成验证码验证 */				
			 Wrapper<BizPassport> wrapper = new EntityWrapper<>();
			 wrapper.eq("mobile", telphone);
			 List<BizPassport> list = bizPassportService.selectList(wrapper);
			 if (list.size()>0) {
				 result.put(Util.RESULT, Util.RESULT_SL_SUCCESS);
				 result.put(Util.MESSAGE, "手机号码已经存在");
			}else{
				 BizPassport member = bizPassportService.selectById(Integer.parseInt(id));
				 member.setMobile(telphone);
				 member.setVerifyTel(1);
				 bizPassportService.updateById(member);
				 result.put(Util.RESULT, Util.RESULT_RC_SUCCESS);
				 result.put(Util.MESSAGE, "操作成功");
			}
		 }
		 
		 return result;
	 }
	 
	/**
	 * 修改用户邮箱
	 * data:2018年3月7日 15:25:59
	 * 
	 */	
	 @RequestMapping(value = "/verify_email")
	 @ResponseBody
	 public Object verify_email(HttpServletRequest request, HttpServletResponse response) {
		 response.setHeader("Access-Control-Allow-Origin", "*"); 
		 Map<String,Object> result = new HashMap<String, Object>();	
		 String email = request.getParameter("email");
		 String id = request.getParameter("id");	
		 if (ToolUtil.isOneEmpty(id,email)) {
			 result.put(Util.RESULT, Util.RESULT_RC_CHECK_FAIL);
			 result.put(Util.MESSAGE, "参数不对");
		 }else{
			 Wrapper<BizPassport> wrapper = new EntityWrapper<>();
			 wrapper.eq("email", email);
			 List<BizPassport> list = bizPassportService.selectList(wrapper);
			 if (list.size()>0) {
				 result.put(Util.RESULT, Util.RESULT_SL_SUCCESS);
				 result.put(Util.MESSAGE, "邮箱已经存在");
			}else{
				 BizPassport member = bizPassportService.selectById(Integer.parseInt(id));
				 member.setEmail(email);
				 member.setVerifyEmail(1);
				 bizPassportService.updateById(member);
				 result.put(Util.RESULT, Util.RESULT_RC_SUCCESS);
				 result.put(Util.MESSAGE, "操作成功");
			}
		 }
		 return result;
		}
	 
	/**
	 * 修改用户密码
	 * data:2018年3月7日 15:25:59
	 */	
	 @RequestMapping(value = "/verify_password")
	 @ResponseBody
	 public Object verify_password(HttpServletRequest request, HttpServletResponse response) {
		 response.setHeader("Access-Control-Allow-Origin", "*"); 
		 Map<String,Object> result = new HashMap<String, Object>();	
		 String old_password = request.getParameter("old_password");
		 String new_password = request.getParameter("new_password");
		 String id = request.getParameter("id");
		 if (ToolUtil.isOneEmpty(old_password,new_password)) {
			 result.put(Util.RESULT, Util.RESULT_RC_CHECK_FAIL);
			 result.put(Util.MESSAGE, "参数不对");
		 }else{
			 BizPassport member = bizPassportService.selectById(Integer.parseInt(id));
			 if (member.getPassword().equals(MD5Util.encrypt(old_password))) {
				 member.setPassword(MD5Util.encrypt(new_password));
				 member.setVerifyPassword(1);
				 bizPassportService.updateById(member);
				 result.put(Util.RESULT, Util.RESULT_RC_SUCCESS);
				 result.put(Util.MESSAGE, "操作成功");
			}
		 }
		 return result; 
	 }
	 
   /**
	 * 用户优惠券列表
	 * data:2018年3月8日 09:40:28
	 */
	 @RequestMapping(value = "/coupons_list")
	 @ResponseBody
	 public Object coupons_list(HttpServletRequest request, HttpServletResponse response) {
		 response.setHeader("Access-Control-Allow-Origin", "*"); 
		 Map<String,Object> result = new HashMap<String, Object>();	
		 Map<String,Object> map = new HashMap<String, Object>();	
		 Map<String,Object> page = new HashMap<String, Object>();
		 Map<String,Object> temp = new HashMap<String, Object>();
		 String id = request.getParameter("id");
		 String status = request.getParameter("status");
		 String pageNo = request.getParameter("pageNo");//页数		
		 int pageSize = 5; //偏移量		
		 if (ToolUtil.isOneEmpty(id,status,pageNo)) {
			 result.put(Util.RESULT, Util.RESULT_RC_CHECK_FAIL);
			 result.put(Util.MESSAGE, "参数不对");
		 }else{
			 map.put("passport_id", id);
			 map.put("is_used", status);
			 List< Map<String,Object>> list =  bizPassportCouponService.getCouponsList(map);		
			 int totalCount = list.size();//总条数
			 map.put("pageNo", pageSize*((Integer.parseInt(pageNo))-1));
			 map.put("pageSize", pageSize);
			 List< Map<String,Object>> list2 =  bizPassportCouponService.getCouponsList(map);
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
	 
	    /**
		 * 个人实名认证
		 * data:2018年3月12日 14:30:06
		 */	
		 @RequestMapping(value = "/verify")
		 @ResponseBody
		 public Object verify_Certification(HttpServletRequest request, HttpServletResponse response) {
			 
			 response.setHeader("Access-Control-Allow-Origin", "*");
			 String name = request.getParameter("name");
			 String mobile = request.getParameter("mobile");
			 String ID_card = request.getParameter("verfier");
			 String ID_img = request.getParameter("ID_img");
			 String ID_img_side = request.getParameter("ID_img_side");
			 String passport_id = request.getParameter("passport_id");
			 
			 String str = ID_img.split(",")[1];
			 String str2 = ID_img_side.split(",")[1];
			 			 
			 byte[] img =Base64Utils.decode(str.getBytes());
			 byte[] images = Base64Utils.decode(str2.getBytes());
			 
			 			 			 
			 Map<String, Object> map =new HashMap<String, Object>();
			 BizPassportVerifyApply verify = new BizPassportVerifyApply();
			 
			 verify.setPassportId(Integer.valueOf(passport_id));
			 verify.setUsername(name);
			 verify.setIdCard(ID_card);
			 verify.setIdCardPositive(ID_img);
			 verify.setIdCardNegative(ID_img_side);
			 verify.setMobile(mobile);
			 verify.setApplyStatus(0);
			 verify.setApplyTime(new Date());
			 
			 bizPassportVerifyApplyService.insert(verify);
			 	 
			 map.put(Util.RESULT, Util.RESULT_RC_SUCCESS);
			 map.put(Util.MESSAGE, "操作成功");
			 return map;
		 }
		 
		 
		    /**
			 * 企业实名认证
			 * data:2018年3月13日 10:42:12
			 */	
			 @RequestMapping(value = "/company_verify")
			 @ResponseBody
			 public Object company_verify_Certification(HttpServletRequest request, HttpServletResponse response) {
				 
				 
				 return null;
			 }

}
