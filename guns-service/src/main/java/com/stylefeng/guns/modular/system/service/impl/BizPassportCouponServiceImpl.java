package com.stylefeng.guns.modular.system.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stylefeng.guns.common.persistence.model.BizPassportCoupon;
import com.stylefeng.guns.common.persistence.dao.BizPassportCouponMapper;
import com.stylefeng.guns.common.persistence.dao.BizPassportMapper;
import com.stylefeng.guns.modular.system.service.IBizPassportCouponService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author stylefeng
 * @since 2018-01-30
 */
@Service
public class BizPassportCouponServiceImpl extends ServiceImpl<BizPassportCouponMapper, BizPassportCoupon> implements IBizPassportCouponService {

	@Autowired
	private BizPassportCouponMapper bizPassportCouponMapper;
	
  /**
	* 用户优惠券列表
	* data:2018年3月8日 09:40:28
	*/
	@Override
	public List<Map<String, Object>> getCouponsList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		 Map<String,Object> result = new HashMap<String, Object>();	
		 List<Map<String,Object>> list =  bizPassportCouponMapper.getCouponsList(map);	
		/* for (Map<String, Object> map2 : list) {
			 String min = map2.get("min")+"";
			 String cost = map2.get("cost")+"";
			 map2.put("name", "满"+min+"减少"+cost);			 
		}*/
		 return list;
	}
	
}
