package com.stylefeng.guns.modular.system.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * 合作伙伴dao层
 * 
 * @author sjz
 * @date 2018年1月26日上午10:42:23
 * @version 1.0
 */
public interface BizPaternerDao {
	
	/**
	 * 获取合作伙伴列表
	 * 
	 * @author sjz
	 * @date 2018年1月26日上午10:42:23
	 * @version 1.0
	 */
	List<Map<String,Object>> list(@Param("condition") String condition);

}
