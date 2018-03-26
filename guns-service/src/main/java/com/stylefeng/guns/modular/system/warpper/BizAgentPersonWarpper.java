package com.stylefeng.guns.modular.system.warpper;

import com.stylefeng.guns.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.core.base.warpper.BaseControllerWarpper;

import java.util.List;
import java.util.Map;

/**
 * liuqunnnn
 *
 * @author fengshuonan
 * @date 2017年2月13日 下午10:47:03
 */
public class BizAgentPersonWarpper extends BaseControllerWarpper {

    public BizAgentPersonWarpper(List<Map<String, Object>> list) {
        super(list);
    }

    @Override
    public void warpTheMap(Map<String, Object> map) {   	    	    	 
    	 map.put("agent_id", ConstantFactory.me().getAgent_id((Integer) map.get("agent_id")));  
    	 map.put("index_show", ConstantFactory.me().getIndexShow((Integer) map.get("index_show")));
    }

}
 