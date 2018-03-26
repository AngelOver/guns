package com.stylefeng.guns.modular.system.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * 分公司dao层
 * 
 * @author sjz
 * @date 2018年1月30日上午9:36:31
 * @version 1.0
 */
public interface BizBranchDao {
	
	/**
	 * 获取分公司列表
	 * 
	 * @author sjz
	 * @date 2018年1月30日上午9:36:31
	 * @version 1.0
	 */	
	List<Map<String,Object>> list(@Param("condition") String condition);

}
