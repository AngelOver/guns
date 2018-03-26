package com.stylefeng.guns.modular.system.service;

import java.util.List;
import java.util.Map;

import com.stylefeng.guns.common.persistence.model.BizEvaluate;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author stylefeng
 * @since 2018-02-08
 */
public interface IBizEvaluateService extends IService<BizEvaluate> {

	

	/**
	 * 根据代理商id获得评价列表
	 * data:2018年3月9日 10:30:19
	 * 
	 */
	List<Map<String, Object>> getEvaluateList(Map<String, Object> map);
	
}
