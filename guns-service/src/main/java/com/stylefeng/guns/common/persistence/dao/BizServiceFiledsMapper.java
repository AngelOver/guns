package com.stylefeng.guns.common.persistence.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.stylefeng.guns.common.persistence.model.BizServiceFileds;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author stylefeng
 * @since 2018-01-22
 */
public interface BizServiceFiledsMapper extends BaseMapper<BizServiceFileds> {

	/**
	 *发布商品属性	 
	 * @author dengshuang
	 * @Date 2018年3月1日 09:52:20
	 */
	List<Map<String, Object>> getGoodFiled(@Param("map")Map<String, Object> map);

}