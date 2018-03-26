package com.stylefeng.guns.modular.system.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;


/**
 * 代理商的dao层
 * 
 * @author sjz
 * @date 2018年1月20日上午9:24:34
 * @version 1.0
 */
public interface BizAgentDao {
	
	/**
	 * 获取代理商列表
	 * 
	 * @author sjz
	 * @param condition
	 * @date 2018年1月20日上午9:24:34
     * @version 1.0
	 * @return
	 */
	List<Map<String,Object>> list(@Param("condition") String condition);

}
