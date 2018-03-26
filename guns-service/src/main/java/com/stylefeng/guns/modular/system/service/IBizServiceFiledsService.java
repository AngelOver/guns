package com.stylefeng.guns.modular.system.service;

import java.util.List;
import java.util.Map;

import com.stylefeng.guns.common.persistence.model.BizServiceFileds;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author stylefeng
 * @since 2018-01-22
 */
public interface IBizServiceFiledsService extends IService<BizServiceFileds> {

	/**
	 * 创建分类规格	 
	 * @author dengshuang
	 * @Date 2018年1月25日 11:00:18
	 */
	void createServiceFiled(BizServiceFileds bizServiceFileds, String dictValues);

	/**
	 * 修改分类规格	 
	 * @author dengshuang
	 * @Date 2018年1月26日 10:05:29
	 */
	void updateField(BizServiceFileds bizServiceFileds, String dictValues);

	/**
	 * 发布商品属性	 
	 * @author dengshuang
	 * @Date 2018年3月1日 09:43:18
	 */
	Map<String, Object> getGoodFiled(Map<String, Object> result);
	
}
