package com.stylefeng.guns.modular.system.warpper;

import java.util.List;
import java.util.Map;

import com.stylefeng.guns.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.core.base.warpper.BaseControllerWarpper;

public class BizAgentCompanyWarpper extends BaseControllerWarpper{

	public BizAgentCompanyWarpper(List<Map<String, Object>> list) {
		super(list);

	}

	@Override
	protected void warpTheMap(Map<String, Object> map) {
		
		map.put("index_show", ConstantFactory.me().getIndexShow((Integer) map.get("index_show")));
		
	}

}
