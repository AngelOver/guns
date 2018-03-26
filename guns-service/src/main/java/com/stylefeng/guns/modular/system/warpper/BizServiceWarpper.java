package com.stylefeng.guns.modular.system.warpper;

import com.stylefeng.guns.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.core.base.warpper.BaseControllerWarpper;

import java.util.List;
import java.util.Map;

/**
 * 服务规格的包装类
 *
 * @author fengshuonan
 * @date 2017年2月13日 下午10:47:03
 */
public class BizServiceWarpper extends BaseControllerWarpper {

    public BizServiceWarpper(List<Map<String, Object>> list) {
        super(list);
    }

    @Override
    public void warpTheMap(Map<String, Object> map) {   	    	    	 
    	 map.put("agent_id", ConstantFactory.me().getAgent_id((Integer) map.get("agent_id"))); 
    	 map.put("status", ConstantFactory.me().getStatus((Integer) map.get("status")));   
    	 //map.put("list_pic", (map.get("list_pic")+"").length()<10?(map.get("list_pic")+""):(map.get("list_pic")+"").substring(0, 10));
    }

}
