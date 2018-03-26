package com.stylefeng.guns.modular.system.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

import com.stylefeng.guns.common.persistence.model.BizServiceFileds;

/**
 * 服务分类中的dao
 *
 * @author dengshuang
 * @date 2018年1月18日 11:27:55
 */
public interface BizServiceFieldDao {

	
	/**
     * 查询字段列表
     * 
     * @author denghsuang
     * @Date 2018年1月18日 11:31:07
     */
	List<Map<String, Object>> list(@Param("condition")String condition);

	/**
     * 插入返回对象
     * 
     * @author denghsuang
     * @Date 2018年1月25日 14:21:35
     */
	@Options(useGeneratedKeys = true, keyProperty = "filedId")
	int insertField(BizServiceFileds bizServiceFileds);
	
	
	

}
