package com.stylefeng.guns.modular.system.warpper;

import java.util.List;
import java.util.Map;

import com.stylefeng.guns.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.core.base.warpper.BaseControllerWarpper;

/**
 * 
 * @author sjz
 * @date 2018年1月22日下午2:39:00
 * @version 1.0
 */
public class BizNewsWarpper extends BaseControllerWarpper{

	public BizNewsWarpper(List<Map<String, Object>> list) {
		super(list);
	
	}

	@Override
	protected void warpTheMap(Map<String, Object> map) {
        
		map.put("status", ConstantFactory.me().getNewsStatus((Integer) map.get("status")));
		map.put("apply_status", ConstantFactory.me().getApplyStatus((Integer) map.get("apply_status")));
	}

}
