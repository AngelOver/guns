package com.stylefeng.guns.modular.system.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * 新闻分类dao层
 * 
 * @author sjz
 * @date 2018年1月22日上午9:33:26
 * @version 1.0
 */
public interface BizNewsCategoryDao {
	
	/**
	 * 
	 * 获取新闻分类列表
	 * * @author sjz
     * @date 2018年1月22日上午9:33:26
	 * @param condition
	 * @return
	 */
	List<Map<String,Object>> list(@Param("condition") String condition);

}
