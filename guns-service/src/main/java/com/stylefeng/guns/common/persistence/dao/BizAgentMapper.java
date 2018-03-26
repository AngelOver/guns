package com.stylefeng.guns.common.persistence.dao;


import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.stylefeng.guns.common.persistence.model.BizAgent;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author stylefeng
 * @since 2018-01-20
 */
public interface BizAgentMapper extends BaseMapper<BizAgent> {
	
	
	public Map<String, Object> selectAgentInfo(Integer bizAgentId);
	
}