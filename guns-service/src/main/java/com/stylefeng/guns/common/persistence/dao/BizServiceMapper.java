package com.stylefeng.guns.common.persistence.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

import com.stylefeng.guns.common.persistence.model.BizService;
import com.stylefeng.guns.common.persistence.model.BizServiceFileds;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author stylefeng
 * @since 2018-01-20
 */
public interface BizServiceMapper extends BaseMapper<BizService> {

	/**
	 * 商品管理 商品列表
	 * data:2018年2月8日 09:43:17
	 * 
	 */
	List<Map<String, Object>> getServiceList(@Param("map")Map<String, Object> map);

	/**
	 * 商家管理基本信息
	 * data:2018年2月12日 15:03:27
	 * agent_id
	 */
	Map<String, Object> getServiceInfo(@Param("agent_id")String agent_id);
	
	/**
	 * 发布商品
	 * data:2018年3月5日 16:46:15
	 * agent_id
	 */
	@Options(useGeneratedKeys = true, keyProperty = "serviceId")
	int insertService(BizService bizService);

	/**
	 * 通过分类id 查询 服务
	 * data:2018年3月17日 15:01:03
	 * categoryId
	 */
	List<Map<String, Object>> getServiceByCate_id(@Param("categoryId")Integer categoryId);

}