package com.stylefeng.guns.modular.system.warpper;

import com.stylefeng.guns.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.core.base.warpper.BaseControllerWarpper;

import java.util.List;
import java.util.Map;

/**
 * 用户管理的包装类
 *
 * @author fengshuonan
 * @date 2017年2月13日 下午10:47:03
 */
public class BizServiceCategoryDaoWarpper extends BaseControllerWarpper {

    public BizServiceCategoryDaoWarpper(List<Map<String, Object>> list) {
        super(list);
    }

    @Override
    public void warpTheMap(Map<String, Object> map) {
    	 map.put("status", ConstantFactory.me().getStatus((Integer) map.get("status"))); 
    	/* map.put("agent_id", ConstantFactory.me().getAgent_id((Integer) map.get("agent_id"))); */
    	 map.put("parent_category_id", ConstantFactory.me().getParentCategoryId((Integer) map.get("parent_category_id")));
    	 /*System.out.println((Integer) map.get("parent_category_id")+"");*/
    	/* map.put("status", ConstantFactory.me().getStatus((Integer) map.get("status"))); */
    	/* map.put(key, value)*/
    }

}
