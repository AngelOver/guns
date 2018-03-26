package com.stylefeng.guns.modular.system.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * 会员优惠卷dao
 *
 * @author dengshuang
 * @date 2018年1月30日 10:07:52
 */
public interface BizPassportCouponsDao {

	
	/**
     * 会员优惠卷列表
     * 
     * @author denghsuang
     * @Date 2018年1月30日 10:07:58
     */
	List<Map<String, Object>> list(@Param("condition")String condition);

}
