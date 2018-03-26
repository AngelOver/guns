package com.stylefeng.guns.modular.system.service;

import java.util.List;
import java.util.Map;

import com.stylefeng.guns.common.persistence.model.BizService;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author stylefeng
 * @since 2018-01-20
 */
public interface IBizServiceService extends IService<BizService> {

	/**
	 * 商品管理 商品列表
	 * data:2018年2月8日 09:43:17
	 * @param map 
	 * 
	 */
	List<Map<String, Object>> getServiceList(Map<String, Object> map);
	
	/**
	 * 商品管理 商品列表
	 * data:2018年3月16日 15:29:54
	 * @param map 
	 * 
	 */
	List<Map<String, Object>> getServiceList2(Map<String, Object> map);

	/**
	 * 商家管理基本信息
	 * data:2018年2月12日 15:03:27
	 * agent_id
	 */
	Map<String, Object> getServiceInfo(String agent_id);

	
	/**
	 * 商品详情插入
	 * data:2018年3月5日 14:22:33
	 * map
	 */
	void publish_service(Map<String, Object> map);

	/**
	 * 通过分类id 查询 服务
	 * data:2018年3月17日 15:01:03
	 * categoryId
	 */
	List<Map<String, Object>> getServiceByCate_id(Integer categoryId);

	/**
	 * 服务编辑修改
	 * data:2018年3月21日 21:52:23
	 * request
	 */
	void update_service(Map<String, Object> map);
	
}
