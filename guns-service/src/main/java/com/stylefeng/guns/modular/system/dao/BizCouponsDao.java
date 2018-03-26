package com.stylefeng.guns.modular.system.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * 优惠卷dao
 *
 * @author dengshuang
 * @date 2018年1月29日 21:09:40
 */
public interface BizCouponsDao {

	
	/**
     * 优惠卷列表
     * 
     * @author denghsuang
     * @Date 2018年1月18日 11:31:07
     */
	List<Map<String, Object>> list(@Param("condition")String condition);

}
