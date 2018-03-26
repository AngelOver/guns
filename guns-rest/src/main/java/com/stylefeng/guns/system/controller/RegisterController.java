package com.stylefeng.guns.system.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.stylefeng.guns.common.persistence.model.BizAgent;
import com.stylefeng.guns.common.persistence.model.BizPassport;
import com.stylefeng.guns.core.util.MD5Util;
import com.stylefeng.guns.modular.auth.util.JwtTokenUtil;
import com.stylefeng.guns.modular.auth.util.Util;
import com.stylefeng.guns.modular.system.dao.BizPassportDao;
import com.stylefeng.guns.modular.system.service.IBizAgentService;
import com.stylefeng.guns.modular.system.service.IBizPassportService;


@RestController
@RequestMapping("/noauth")
public class RegisterController {
	
	private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);
	
	@Autowired
    private IBizPassportService bizPassportService;

    @Resource
    BizPassportDao bizPassportDao;
    
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    
    @Autowired
    private IBizAgentService bizAgentService;
    
    /**
	 * 操作成功
	 */
	public static String SUCCESS_CODE = "0000";
	/**
	 * 已经注册过
	 */
	public static String REGISTERED_CODE = "0101";
	/**
	 * 用户或密码不正确
	 */
	public static String LOGIN_ERROR_CODE = "0102";
	/**
	 * 登录类型不正确
	 */
	public static String LOGIN_TYPE_CODE = "0104";
	/**
	 * 跳转到主页
	 */
	public static String LOGIN_INDEX_CODE = "0001";
	/**
	 * 跳转到服务商主页
	 */
	public static String LOGIN_AGENT_CODE = "0002";
	/**
	 * 修改密码成功
	 */
	public static String UPDATE_PASSWORD_CODE = "0003";
	
    /**
     * 服务商注册
     * @author sjz
     * @date 2018年2月5日下午10:32:36
     * @version 1.0
     */
	@RequestMapping("/register_agent")
	@ResponseBody
	@Transactional
	public Object registerAgent(HttpServletRequest request,HttpServletResponse repResponse){
		repResponse.setHeader("Access-Control-Allow-Origin", "*");
		String name = request.getParameter("name");		
		String email = request.getParameter("email");
		String mobile = request.getParameter("mobile");
		String password = request.getParameter("password");
        
		password = MD5Util.encrypt(password);
		Map<String, Object> map =new HashMap<String, Object>();
		BizPassport bizPassport = new BizPassport();
		BizAgent agent = new BizAgent();
		//有条件，按条件查询
    	 Wrapper<BizPassport> wrapper = new EntityWrapper<>();
		  wrapper.eq("mobile", mobile);
		List<BizPassport> PassportList =  bizPassportService.selectList(wrapper);
		if(PassportList.size()>0){
			 map.put(Util.RESULT, REGISTERED_CODE);
			 map.put(Util.MESSAGE, "该手机号已经注册过");			
		}else{
		bizPassport.setAccount(name);
		bizPassport.setRole(2);
		bizPassport.setMobile(mobile);
		bizPassport.setPassword(password);
		bizPassport.setEmail(email);
		bizPassport.setNickname("");
		bizPassport.setSex(3);
		bizPassport.setVerify(0);
		bizPassport.setScore(Long.parseLong("0"));
		bizPassport.setBalance(new BigDecimal(0));
		bizPassport.setStatus(0);	
		bizPassport.setCreatedAt(new Date());
		bizPassportService.insert(bizPassport);	
		
		Wrapper<BizPassport> wrapp_passwort = new EntityWrapper<>();
		wrapp_passwort.eq("mobile", mobile).and().eq("password", password);
		BizPassport Passport =  bizPassportService.selectOne(wrapp_passwort);
		Long password_id = 	Passport.getPassportId();
		agent.setPassportId(password_id.intValue());
		agent.setIsStar(0);
		agent.setName(name);
		agent.setAgentStatus(1);
		agent.setApplyStatus(0);
		agent.setCreatedAt(new Date());
		agent.setPhone(mobile);		
		
		bizAgentService.insert(agent);
				
		map.put(Util.RESULT, SUCCESS_CODE);
		map.put(Util.MESSAGE, "操作成功");	
		}
		return map;
		
	}
	
	/**
     * 普通用户注册
     * @author sjz
     * @date 2018年2月5日下午11:30:36
     * @version 1.0
     */
	@RequestMapping("/register")
	@ResponseBody
	public Object register(HttpServletRequest request,HttpServletResponse repResponse){
		repResponse.setHeader("Access-Control-Allow-Origin", "*");

		String mobile = request.getParameter("mobile");
		String password = request.getParameter("password");       
		password = MD5Util.encrypt(password);
		Map<String, Object> map =new HashMap<String, Object>();
		BizPassport bizPassport = new BizPassport();
		//有条件，按条件查询
    	 Wrapper<BizPassport> wrapper = new EntityWrapper<>();
		  wrapper.eq("mobile", mobile);
		List<BizPassport> PassportList =  bizPassportService.selectList(wrapper);
		if(PassportList.size()>0){
			map.put(Util.RESULT, REGISTERED_CODE);
			map.put(Util.MESSAGE, "该手机号已经注册过");			
		}else{
		bizPassport.setAccount("");
		bizPassport.setRole(1);
		bizPassport.setMobile(mobile);
		bizPassport.setPassword(password);
		bizPassport.setNickname("");
		bizPassport.setSex(3);
		bizPassport.setVerify(0);
		bizPassport.setScore(Long.parseLong("0"));
		bizPassport.setBalance(new BigDecimal(0));
		bizPassport.setStatus(0);	
		bizPassport.setCreatedAt(new Date());
		bizPassportService.insert(bizPassport);
		
		 map.put(Util.RESULT, SUCCESS_CODE);
		 map.put(Util.MESSAGE, "操作成功");	
		}		
		return map;
		
	}
	
	
	/**
     * 页面登录
     * @author sjz
     * @date 2018年2月6日下午10:40:20
     * @version 1.0
     */
	@RequestMapping("/login")
	@ResponseBody
	public Object Login(HttpServletRequest request,HttpServletResponse repResponse){
		
		repResponse.setHeader("Access-Control-Allow-Origin", "*");
		String mobile = request.getParameter("mobile");
		String password = request.getParameter("password");
		//判断普通用户或是服务商用户
		String status = request.getParameter("status");
		Map<String, Object> map =new HashMap<String, Object>();
		Map<String, Object> maps =new HashMap<String, Object>();
		List<Map<String, Object>> reslut = new ArrayList<Map<String, Object>>();
		password = MD5Util.encrypt(password);

		try {
			//有条件，按条件查询
			 Wrapper<BizPassport> wrapper = new EntityWrapper<>();
			  wrapper.eq("mobile", mobile).or().eq("email", mobile).and().eq("password", password);
			BizPassport passport =  bizPassportService.selectOne(wrapper);
			if(passport !=null && password.equals(passport.getPassword())){	
				   String role = passport.getRole().toString();
				  if(status.equals(role)){
					   final String randomKey = jwtTokenUtil.getRandomKey();
			           final String token = jwtTokenUtil.generateToken(passport.getAccount(), randomKey);
			           logger.info("token值"+token);
			           maps.put("nickname", passport.getNickname());
			           maps.put("mobile", passport.getMobile());
			           maps.put("headimg", passport.getHeadimg());
			           maps.put("email", passport.getEmail());
			           maps.put("account", passport.getAccount());
			           maps.put("randomKey",randomKey );
			           maps.put("status", status);
			           maps.put("token", token);
			           if (status.equals("2")) {
			        	   Long passport_id = passport.getPassportId();
			        	   Wrapper<BizAgent> wrapper_agent = new EntityWrapper<>();
			        	   wrapper_agent.eq("passport_id", passport_id);
			        	   BizAgent selectOne = bizAgentService.selectOne(wrapper_agent);
			        	   maps.put("passport_id", selectOne.getAgentId());	
			           }
			           if (status.equals("1")) {
			        	   maps.put("passport_id", passport.getPassportId());	
			           }			           			           
			           if(status.equals("1")){
						   map.put(Util.RESULT, SUCCESS_CODE);
						   maps.put("userType", LOGIN_INDEX_CODE);
						   reslut.add(maps);
						   map.put("datas", reslut);
						   map.put(Util.MESSAGE, "跳转到主页");
					   }else if(status.equals("2")){
						   map.put(Util.RESULT, SUCCESS_CODE);
						   maps.put("userType", LOGIN_AGENT_CODE);
						   reslut.add(maps);
						   map.put("datas", reslut);
						   map.put(Util.MESSAGE, "跳转到服务商主页");
					   }

				  }else{
					  map.put(Util.RESULT, LOGIN_TYPE_CODE);
					  map.put(Util.MESSAGE, "登录类型不正确");
				  }								
			}else{				
				map.put(Util.RESULT, LOGIN_ERROR_CODE);
				map.put(Util.MESSAGE, "用户或密码不正确");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return map;			 
	}
	
	/**
	 * 验证手机号码存不存在
	 */
	@RequestMapping("/check_phone")
	@ResponseBody
	public Object Check(HttpServletRequest request,HttpServletResponse repResponse){
		repResponse.setHeader("Access-Control-Allow-Origin", "*");
		String mobile = request.getParameter("mobile");
		Map<String, Object> map =new HashMap<String, Object>();
		try {
			//有条件，按条件查询
			 Wrapper<BizPassport> wrapper = new EntityWrapper<>();
			  wrapper.eq("mobile", mobile);
			  BizPassport passport =  bizPassportService.selectOne(wrapper);
			  if(passport !=null){				 
				   map.put(Util.RESULT, SUCCESS_CODE);
				   map.put(Util.MESSAGE, "操作成功");
			  }else{
				   map.put(Util.RESULT, LOGIN_ERROR_CODE);
				   map.put(Util.MESSAGE, "该手机号未注册，请重新输入！");
			  }
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return map;
	}
	
	/**
     * 忘记密码
     * @author sjz
     * @date 2018年3月2日下午16:40:20
     * @version 1.0
     */
	@RequestMapping("/forgetPassword")
	@ResponseBody
	public Object ForgetPassword(HttpServletRequest request,HttpServletResponse repResponse){
		
		repResponse.setHeader("Access-Control-Allow-Origin", "*");
		String mobile = request.getParameter("mobile");
	//	String code = request.getParameter("code");
		String password =request.getParameter("password");
	
		password = MD5Util.encrypt(password);
		Map<String, Object> map =new HashMap<String, Object>();
		try {
			
			//有条件，按条件查询
			 Wrapper<BizPassport> wrapper = new EntityWrapper<>();
			  wrapper.eq("mobile", mobile);
			  BizPassport passport =  bizPassportService.selectOne(wrapper);
			if(passport !=null){			
			   passport.setPassword(password);
			   bizPassportService.updateById(passport);
			   map.put("datas", UPDATE_PASSWORD_CODE);
			   map.put(Util.RESULT, SUCCESS_CODE);
			   map.put(Util.MESSAGE, "操作成功");
			}else{				 
				 map.put(Util.RESULT, LOGIN_ERROR_CODE);
				 map.put(Util.MESSAGE, "操作失败！");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
				
		return map;
		
	}

}
