package com.stylefeng.guns.common.persistence.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.stylefeng.guns.common.persistence.model.BizEvaluate;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author stylefeng
 * @since 2018-02-08
 */
public interface BizEvaluateMapper extends BaseMapper<BizEvaluate> {

	
	/**
	 * 根据代理商id获得评价列表
	 * data:2018年3月9日 14:19:29
	 * 
	 */
	List<Map<String, Object>> getEvaluateList(@Param("map")Map<String, Object> map);

}