package com.stylefeng.guns.modular.system.warpper;

import com.stylefeng.guns.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.core.base.warpper.BaseControllerWarpper;

import java.util.List;
import java.util.Map;

/**
 * 会员优惠卷管理的包装类
 *
 * @author dengshuang
 * @date 2018年1月30日 10:21:27
 */
public class BizPassportCouponWarpper extends BaseControllerWarpper {

    public BizPassportCouponWarpper(List<Map<String, Object>> list) {
        super(list);
    }

    @Override
    public void warpTheMap(Map<String, Object> map) {
    	
        map.put("is_used", ConstantFactory.me().getWhether((Integer) map.get("is_used")));       
    }

}
