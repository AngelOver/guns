package com.stylefeng.guns.modular.system.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * 规格中的dao
 *
 * @author dengshuang
 * @date 2018年1月18日 11:27:55
 */
public interface BizServiceDao {

	
	/**
     * 规格列表
     * 
     * @author denghsuang
     * @Date 2018年1月18日 11:31:07
     */
	List<Map<String, Object>> list(@Param("condition")String condition);

}
