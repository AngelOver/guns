package com.stylefeng.guns.modular.system.warpper;

import java.util.List;
import java.util.Map;

import com.stylefeng.guns.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.core.base.warpper.BaseControllerWarpper;

/**
 * 
 * @author sjz
 * @date 2018年3月14日下午4:30:13
 * @version 1.0
 */
public class BizPassportVerifyApplyWarpper extends BaseControllerWarpper{

	public BizPassportVerifyApplyWarpper(List<Map<String, Object>> list) {
		super(list);
	
	}

	@Override
	protected void warpTheMap(Map<String, Object> map) {
		
		map.put("apply_status", ConstantFactory.me().getApplyStatus((Integer) map.get("apply_status")));
		
	}

	
	
}
