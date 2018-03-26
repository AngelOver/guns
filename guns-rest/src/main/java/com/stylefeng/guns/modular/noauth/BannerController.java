package com.stylefeng.guns.modular.noauth;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.stylefeng.guns.common.persistence.model.BizBanner;
import com.stylefeng.guns.modular.auth.util.Util;
import com.stylefeng.guns.modular.system.service.IBizBannerService;

/**
 *
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/noauth")
public class BannerController {

	@Autowired
	private IBizBannerService bizBannerService;	
	
	 /**
     * 获取列表1
     */	
    @RequestMapping(value = "/bannerlist")
    @ResponseBody
    public Object list(String condition , HttpServletRequest request, HttpServletResponse response) {
    	Map<String,Object> map = new HashMap<String, Object>();		
    	//如果没有条件查询所有
    	if(StringUtils.isEmpty(condition)){
    		 Wrapper<BizBanner> wrapper = new EntityWrapper<>();
    		 wrapper.eq("status", 0);
    		response.setHeader("Access-Control-Allow-Origin", "*");
    		List<BizBanner> list = bizBannerService.selectList(null);
    		map.put("datas", list);
	   		map.put(Util.RESULT, Util.RESULT_RC_SUCCESS);
	   		map.put(Util.MESSAGE, "操作成功");
    		return map;
    	}else{
    		//有条件，按条件查询
        	 Wrapper<BizBanner> wrapper = new EntityWrapper<>();
        	 wrapper.eq("status", 0);
            wrapper = wrapper.like("type", "%" + condition + "%").or().like("title", "%" + condition + "%").or().like("url", "%" + condition + "%");
            response.setHeader("Access-Control-Allow-Origin", "*"); 
            List<BizBanner> list = bizBannerService.selectList(wrapper);
            map.put("datas", list);
	   	   	map.put(Util.RESULT, Util.RESULT_RC_SUCCESS);
	   	   	map.put(Util.MESSAGE, "操作成功");
            return map;
    	}    	   	
    }
	
    
    /**
     * 代理商banner
     */	
    @RequestMapping(value = "/agentBannerList")
    @ResponseBody
    public Object agent_list(String id, HttpServletRequest request, HttpServletResponse response) {
    	response.setHeader("Access-Control-Allow-Origin", "*");	
    	Map<String,Object> map = new HashMap<String, Object>();		
    	if (id!=null&&!"".equals(id.trim())) {
    		Wrapper<BizBanner> wrapper = new EntityWrapper<BizBanner>();
    		wrapper = wrapper.eq("agent_Id", id);
    		wrapper = wrapper.eq("status", 0);
    		List<BizBanner> list = bizBannerService.selectList(wrapper);
    		map.put("datas", list);
	   		map.put(Util.RESULT, Util.RESULT_RC_SUCCESS);
	   		map.put(Util.MESSAGE, "操作成功");
    		return map;    		
		}else{
			 map.put(Util.RESULT, Util.RESULT_RC_CHECK_FAIL);
			 map.put(Util.MESSAGE, "参数不对");
		}
    	return map;
    }
}
