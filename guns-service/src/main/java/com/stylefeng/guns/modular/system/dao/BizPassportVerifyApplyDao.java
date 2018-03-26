package com.stylefeng.guns.modular.system.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * 实名认证的dao层
 * 
 * @author sjz
 * @date 2018年3月14日下午4:25:45
 * @version 1.0
 */
public interface BizPassportVerifyApplyDao {
	
	
	/**
     * 查询认证列表
     * 
     * @author sjz
     * @date 2018年3月14日下午4:25:45
     * @version 1.0
     */
	List<Map<String, Object>> list(@Param("condition")String condition);
}
