package com.stylefeng.guns.modular.system.service;

import java.util.List;
import java.util.Map;

import com.stylefeng.guns.common.persistence.model.BizPassportCoupon;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author stylefeng
 * @since 2018-01-30
 */
public interface IBizPassportCouponService extends IService<BizPassportCoupon> {

	
  /**
	* 用户优惠券列表
	* data:2018年3月8日 09:40:28
	*/
	List<Map<String, Object>> getCouponsList(Map<String, Object> map);
	
}
