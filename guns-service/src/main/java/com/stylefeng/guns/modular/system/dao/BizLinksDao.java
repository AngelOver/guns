package com.stylefeng.guns.modular.system.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * 友情链接Dao层
 * 
 * @author sjz
 * @date 2018年1月29日下午2:21:16
 * @version 1.0
 */
public interface BizLinksDao {
	
	/**
	 * 获取友情链接列表
	 * 
	 * @author sjz
	 * @date 2018年1月29日下午2:21:16
	 * @version 1.0
	 */
	List<Map<String,Object>> list(@Param("condition") String condition);

}
