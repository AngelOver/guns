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
public class BizPassportWarpper extends BaseControllerWarpper {

    public BizPassportWarpper(List<Map<String, Object>> list) {
        super(list);
    }

    @Override
    public void warpTheMap(Map<String, Object> map) {
        map.put("sex", ConstantFactory.me().getSexName((Integer) map.get("sex")));   
        map.put("status", ConstantFactory.me().getStatus((Integer) map.get("status"))); 
        map.put("verify", ConstantFactory.me().getVerify((Integer) map.get("verify"))); 
        map.put("role", ConstantFactory.me().getRole((Integer) map.get("role"))); 
    }

}
