package com.stylefeng.guns.modular.system.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * 成功案例dao层
 * 
 * @author sjz
 * @date 2018年2月9日下午3:13:42
 * @version 1.0
 */
public interface BizAgentCaseDao {
	
	/**
	 * 成功案例列表
	 * 
	 * @author sjz
	 * @date 2018年2月9日下午3:15:40
	 * @version 1.0
	 */
	List<Map<String,Object>> list(@Param("condition") String condition);

}
