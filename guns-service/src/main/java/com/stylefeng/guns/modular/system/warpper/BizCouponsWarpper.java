package com.stylefeng.guns.modular.system.warpper;

import com.stylefeng.guns.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.core.base.warpper.BaseControllerWarpper;

import java.util.List;
import java.util.Map;

/**
 * 优惠卷管理的包装类
 *
 * @author fengshuonan
 * @date 2017年2月13日 下午10:47:03
 */
public class BizCouponsWarpper extends BaseControllerWarpper {

    public BizCouponsWarpper(List<Map<String, Object>> list) {
        super(list);
    }

    @Override
    public void warpTheMap(Map<String, Object> map) {
    	map.put("agent_id", ConstantFactory.me().getAgent_id((Integer) map.get("agent_id"))); 
        map.put("status", ConstantFactory.me().getStatus((Integer) map.get("status")));       
    }

}