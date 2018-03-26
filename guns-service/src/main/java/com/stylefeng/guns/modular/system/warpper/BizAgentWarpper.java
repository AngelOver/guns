package com.stylefeng.guns.modular.system.warpper;

import java.util.List;
import java.util.Map;

import com.stylefeng.guns.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.core.base.warpper.BaseControllerWarpper;
/**
 * 
 * @author sjz
 * @date 2018年1月20日下午3:05:53
 * @version 1.0
 */
public class BizAgentWarpper extends BaseControllerWarpper{

	public BizAgentWarpper(List<Map<String, Object>> list) {
		
		super(list);	
	}

	@Override
	protected void warpTheMap(Map<String, Object> map) {
		
		map.put("is_star", ConstantFactory.me().getIsStar((Integer) map.get("is_star")));
		map.put("agent_type", ConstantFactory.me().getAgentType((Integer) map.get("agent_type")));
		map.put("agent_status", ConstantFactory.me().getAgentStatus((Integer) map.get("agent_status")));
		map.put("apply_status", ConstantFactory.me().getApplyStatus((Integer) map.get("apply_status")));
				
		
	}

	

}
