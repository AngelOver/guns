package com.stylefeng.guns.modular.system.service;

import java.util.Map;

import com.stylefeng.guns.common.persistence.model.BizAgent;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author stylefeng
 * @since 2018-01-19
 */
public interface IBizAgentService extends IService<BizAgent> {
	
	public Map<String, Object>  selectAgentInfoById(Integer bizAgentId);
	
}
