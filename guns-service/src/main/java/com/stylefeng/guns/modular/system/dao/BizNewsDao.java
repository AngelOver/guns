package com.stylefeng.guns.modular.system.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * 新闻资讯dao层
 * 
 * @author sjz
 * @date 2018年1月22日下午2:34:37
 * @version 1.0
 */
public interface BizNewsDao {
	
	/**
	 * 
	 * 获取新闻列表
	 * @author sjz
     * @date 2018年1月22日下午2:34:37
	 * @param condition
	 * @return
	 */
	List<Map<String,Object>> list(@Param("condition") String condition);

}
