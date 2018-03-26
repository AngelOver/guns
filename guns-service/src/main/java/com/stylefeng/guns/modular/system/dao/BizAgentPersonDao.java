package com.stylefeng.guns.modular.system.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * 代理人列表的dao
 *
 * @author liuqunnnn
 * @date 2018年1月18日 11:27:55
 */
public interface BizAgentPersonDao {

	
	/**
     * 查询代理人列表
     * 
     * @author liuqun
     * @Date 2018年1月18日 11:31:07
     */
	List<Map<String, Object>> list(@Param("condition")String condition);

}
