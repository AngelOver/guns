package com.stylefeng.guns.modular.system.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * 代理所dao层
 * 
 * @author sjz
 * @date 2018年1月25日上午9:42:22
 * @version 1.0
 */
public interface BizAgentCompanyDao {
	
	/**
	 * 获取代理所列表
	 * 
	 * @author sjz
	 * @date 2018年1月25日上午9:42:22
	 * @version 1.0
	 */
	List<Map<String,Object>> list(@Param("condition") String condition);

}
