package com.stylefeng.guns.common.persistence.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.stylefeng.guns.common.persistence.model.BizPassportCoupon;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author stylefeng
 * @since 2018-01-30
 */
public interface BizPassportCouponMapper extends BaseMapper<BizPassportCoupon> {

  /**
	* 用户优惠券列表
	* data:2018年3月8日 09:40:28
	*/
	List<Map<String, Object>> getCouponsList(@Param("map")Map<String, Object> map);

}