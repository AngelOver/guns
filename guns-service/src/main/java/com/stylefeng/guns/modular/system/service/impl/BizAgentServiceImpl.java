package com.stylefeng.guns.modular.system.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import com.stylefeng.guns.common.persistence.model.BizAgent;
import com.stylefeng.guns.common.persistence.dao.BizAgentMapper;
import com.stylefeng.guns.modular.system.service.IBizAgentService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author stylefeng
 * @since 2018-01-19
 */
@Service
public class BizAgentServiceImpl extends ServiceImpl<BizAgentMapper, BizAgent> implements IBizAgentService {
     
	@Resource
    BizAgentMapper bizAgentMapper;
	
	@Override
	public Map<String, Object> selectAgentInfoById(Integer bizAgentId) {
		
		return bizAgentMapper.selectAgentInfo(bizAgentId);
	}
	
}
